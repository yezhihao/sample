package org.sample.commons.mybatis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.sample.commons.lang.StringUtils;
import org.sample.model.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** MyBatis分页拦截器 */
@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class, Integer.class }) })
public class PageInterceptor implements Interceptor {

    private static final Logger log = LoggerFactory.getLogger(PageInterceptor.class.getSimpleName());

    private static String prefix = null;
    private static String suffix = "select";

    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        MetaObject metaStatementHandler = SystemMetaObject.forObject(statementHandler);

        MappedStatement mappedStatement = (MappedStatement) metaStatementHandler.getValue("delegate.mappedStatement");

        if (isPagination(mappedStatement.getId())) {
            PageInfo pageInfo = (PageInfo) metaStatementHandler.getValue("delegate.boundSql.parameterObject.pageInfo");

            if (pageInfo != null) {
                BoundSql boundSql = statementHandler.getBoundSql();

                if (pageInfo.isShowTotalPages()) {
                    Connection connection = (Connection) invocation.getArgs()[0];
                    int totalRows = getTotalRows(mappedStatement, connection, boundSql);
                    pageInfo.setTotal(totalRows);
                }
                String pageSql = buildPageSql(boundSql.getSql(), pageInfo);
                metaStatementHandler.setValue("delegate.boundSql.sql", pageSql);
            }
        }
        return invocation.proceed();
    }

    /** 获取总行数 */
    private int getTotalRows(MappedStatement mappedStatement, Connection connection, BoundSql boundSql) {
        StringBuilder countSql = new StringBuilder(boundSql.getSql());
        int begin = countSql.indexOf("select") + 6;
        int end = countSql.indexOf("from");
        countSql.replace(begin, end, " count(1) ");

        PreparedStatement statement = null;
        ResultSet rs = null;
        int totalCount = 0;
        try {
            statement = connection.prepareStatement(countSql.toString());
            ParameterHandler handler = new DefaultParameterHandler(mappedStatement, boundSql.getParameterObject(), boundSql);
            handler.setParameters(statement);
            rs = statement.executeQuery();
            if (rs.next())
                totalCount = rs.getInt(1);
        } catch (SQLException e) {
            log.error("SELECT COUNT(1) ERROR", e);
        } finally {
            close(rs);
            close(statement);
        }
        return totalCount;
    }

    private static void close(AutoCloseable closeable) {
        if (closeable != null)
            try {
                closeable.close();
            } catch (Exception e) {
                log.error("close(AutoCloseable) error", e);
            }
    }

    /** 构建分页SQL */
    private static String buildPageSql(String sql, PageInfo page) {
        StringBuilder pageSql = new StringBuilder(sql);
        pageSql.append(" limit ");
        pageSql.append(page.getOffset());
        pageSql.append(", ");
        pageSql.append(page.getSize());
        return pageSql.toString();
    }

    /** 根据sqlId判断是否需要分页 */
    private static boolean isPagination(String sqlId) {
        return startsWith(sqlId) || endsWith(sqlId);
    }

    private static boolean startsWith(String sqlId) {
        if (prefix == null)
            return false;
        return sqlId.contains(prefix);
    }

    private static boolean endsWith(String sqlId) {
        if (suffix == null)
            return false;
        return sqlId.endsWith(suffix);
    }

    @Override
    public void setProperties(Properties properties) {
        String str = (String) properties.get("prefix");
        if (StringUtils.isNotBlank(str))
            prefix = str;
        str = (String) properties.get("suffix");
        if (StringUtils.isNotBlank(str))
            suffix = str;
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof StatementHandler)
            return Plugin.wrap(target, this);
        return target;
    }

}
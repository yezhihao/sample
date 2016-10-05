package org.sample.commons.mybatis;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.sample.enums.BaseEnum;

/**
 * MyBatis枚举类型转换器
 * 1.将枚举类型的value值存入数据库
 * 2.根据枚举类型的value值获得枚举对象
 * 需要在sqlMapper.xml的resultMap中添加 typeHandler
 */
public class EnumHandler extends BaseTypeHandler<BaseEnum> {

    private final BaseEnum em;

    public EnumHandler(Class<BaseEnum> type) {
        if (type == null)
            throw new IllegalArgumentException("Type argument cannot be null");
        this.em = type.getEnumConstants()[0];
    }

    /** 将枚举类型的value值存入数据库 */
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, BaseEnum em, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, em.getValue());
    }

    /** 根据枚举类型的value值获得枚举对象 */
    @Override
    public BaseEnum getNullableResult(ResultSet rs, String columnName) throws SQLException {
        int value = rs.getInt(columnName);
        return em.getEnum(value);
    }

    /** 根据枚举类型的value值获得枚举对象 */
    @Override
    public BaseEnum getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        int value = rs.getInt(columnIndex);
        return em.getEnum(value);
    }

    /** 根据枚举类型的value值获得枚举对象 */
    @Override
    public BaseEnum getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        int value = cs.getInt(columnIndex);
        return em.getEnum(value);
    }

}
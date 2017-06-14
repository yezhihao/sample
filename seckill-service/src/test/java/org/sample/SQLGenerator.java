package org.sample;

import java.lang.reflect.Method;

import org.sample.commons.lang.StringUtils;
import org.sample.seckill.model.Seckill;

public class SQLGenerator {

    public static void main(String[] args) {
        Class<?> clazz = Seckill.class;
        Method[] methods = clazz.getDeclaredMethods();

        String tableName = clazz.getSimpleName();
        StringBuilder select = new StringBuilder();
        select.append("<select id=\"select\" resultType=\"").append(tableName);
        select.append("\" parameterType=\"").append(tableName);
        select.append("\">\n");
        select.append("select \n");
        StringBuilder where = new StringBuilder();

        for (Method method : methods) {
            String name = method.getName();
            if (name.startsWith("get") && !name.equals("getClass")) {
                name = name.replace("get", "");
                name = StringUtils.uncapitalize(name);
                select.append(name).append(", ");

                where.append("<if test=\"").append(name).append(" != null\">\n");
                where.append("\tand ").append(name).append("=#{").append(name).append("}\n");
                where.append("</if>\n");
            }
        }
        select.deleteCharAt(select.length() - 2);
        select.append("\nfrom ").append(tableName).append("\n");
        select.append("<where>\n").append(where).append("</where>");
        select.append("\n</select>");
        System.out.println(select.toString());
    }
}
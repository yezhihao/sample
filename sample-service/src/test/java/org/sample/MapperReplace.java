package org.sample;

import java.io.File;

import org.sample.commons.io.FileUtils;

public class MapperReplace {

    private static String mapper = "src/test/resources/sqlMap";
    private static String dao = "src/test/resources/org/sample/mapper";

    public static void main(String[] args) throws Exception {
        replace(mapper, dao);
    }

    private static void replace(String... paths) throws Exception {
        int count = 0;
        for (String path : paths) {
            File[] daoDirFiles = new File(path).listFiles();
            for (File file : daoDirFiles) {
                replace(file);
                count++;
            }
        }
        System.out.println("替换成功，共计[" + count + "]个文件。");
    }

    private static void replace(File file) throws Exception {
        String text = FileUtils.read(file);
        text = text.replaceAll("ByPrimaryKey", "ById");
        text = text.replaceAll("Selective", "");
        text = text.replaceAll("updateById", "update");
        FileUtils.write(file, text);
    }
}
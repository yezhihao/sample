package org.sample;

import org.sample.commons.io.FileUtils;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DocGenerator {

    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("[/][*]{2,}[ ]([^ ]*)[ ][*][/][\r\n]*[ ]*private[ ]([^ ]*)[ ]([^;]*)");
        Matcher matcher = pattern.matcher(FileUtils.read(new File("E:/work/workspace/sample/sample-api/src/main/java/org/sample/model/User.java")));
        while (matcher.find())
            System.out.println(matcher.group(3) + "\t" + matcher.group(2) + "\t" + matcher.group(1));
    }
}
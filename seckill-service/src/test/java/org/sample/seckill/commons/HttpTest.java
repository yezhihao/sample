package org.sample.seckill.commons;

import org.sample.commons.io.FileUtils;
import org.sample.commons.net.HttpUtils;

import java.io.File;

public class HttpTest extends HttpUtils {

    public final static String src = Thread.class.getResource("/").getPath();

    public static String postFile(String url, String filename) throws Exception {
        String request = FileUtils.read(new File(src + filename));
        String response = doPost(url, request);
        System.out.println(response);
        return response;
    }

    public static String getFile(String url, String filename) throws Exception {
        String request = FileUtils.read(new File(src + filename));
        String response = doGet(url, request);
        System.out.println(response);
        return response;
    }
}
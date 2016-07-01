package org.sample.commons;

import org.sample.commons.io.FileUtils;
import org.sample.commons.net.HttpUtils;

public class HttpTest extends HttpUtils {

    public final static String src = Thread.class.getResource("/").getPath();

    public static String postFile(String url, String filename) throws Exception {
        String request = FileUtils.read(src + filename);
        String response = post(url, request);
        System.out.println(response);
        return response;
    }

    public static String getFile(String url, String filename) throws Exception {
        String request = FileUtils.read(src + filename);
        String response = get(url, request);
        System.out.println(response);
        return response;
    }
}
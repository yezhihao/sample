package org.sample.seckill.controller;

import org.sample.seckill.commons.HttpTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SeckillServiceTest extends HttpTest {

    private static final Logger log = LoggerFactory.getLogger(SeckillServiceTest.class.getSimpleName());

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                exposer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> execution()).start();
    }

    public static void exposer() throws Exception {
        final String url = "http://127.0.0.1/sample/seckill/3/exposer";
        doPost(url);
        int count = 10000;
        while (count-- > 0) {
            new Thread(() -> {
                try {
                    doPost(url);
                    log.info("oo");
                } catch (Exception e) {
                    log.info("xx");
                }
            }).start();
        }
    }

    public static void execution() {
        String url = "http://127.0.0.1/sample/seckill/1/7a707ebbe70bc9a70621bde959232867/%s/execution";
        int count = 10000;
        while (count-- > 0) {
            final String response = String.format(url, String.valueOf(count));
            new Thread(() -> {
                String post = doPost(response);
                System.out.println(post);
            }).start();
        }
    }
}
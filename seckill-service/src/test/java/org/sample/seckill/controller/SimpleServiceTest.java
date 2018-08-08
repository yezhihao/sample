package org.sample.seckill.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.Test;
import org.sample.commons.io.JsonUtils;
import org.sample.model.Pagination;
import org.sample.seckill.commons.HttpTest;
import org.sample.seckill.commons.MultiThreadTest;
import org.sample.seckill.model.entity.User;

import java.util.List;

public class SimpleServiceTest extends HttpTest {

    @Test
    public void create() throws Exception {
        doPost("http://127.0.0.1:8080/controller/user/create", //
                "{\"updateDate\":\"2016-05-25 12:49:51\",\"username\":\"正信希有\",\"group\":{\"name\":\"能断金刚般若波罗蜜\"}}");
    }

    public static void main(String[] args) {
        doGet("http://www.baidu.com", "name", "asdasd");
    }

    @Test
    public void multiThreadTest() throws Exception {
        final String response = postFile("http://127.0.0.1/sample/user/search", "search.txt");
        Runnable runnable = () -> {
            try {
                Pagination<User> result = JsonUtils.toObj(response, new TypeReference<Pagination<User>>() {
                });
                List<User> list = result.getList();
                User user = list.get(0);
                user.getUsername();
            } catch (Exception e) {
                e.printStackTrace();
            }
            MultiThreadTest.calc();
        };

        MultiThreadTest.run(100, runnable);

    }

    @Test
    public void search() throws Exception {
        Runnable runnable = () -> {
            try {
                final String response = postFile("http://127.0.0.1/sample/user/search", "search.txt");

                Pagination<User> result = JsonUtils.toObj(response, new TypeReference<Pagination<User>>() {
                });
                List<User> list = result.getList();
                User user = list.get(0);
                user.getUsername();
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        MultiThreadTest.run(2, runnable);
        MultiThreadTest.calc();
    }

    @Test
    public void testThrow() throws Exception {
        doPost("http://127.0.0.1/sample/throw");
    }
}
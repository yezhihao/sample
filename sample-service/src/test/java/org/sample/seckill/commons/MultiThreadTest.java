package org.sample.seckill.commons;

import java.util.Date;

import org.sample.model.PageInfo;
import org.sample.seckill.model.Group;
import org.sample.seckill.model.User;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MultiThreadTest {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static long start;

    public static void main(String[] args) {

        PageInfo pageInfo = new PageInfo(10, 10);
        final Group group = new Group();
        group.setId(54351353);
        group.setName("adsads564asd安德森");
        group.setCreateTime(new Date());
        group.setPageInfo(pageInfo);

        for (int i = 0; i < 30; i++) {
            User user = new User();
            user.setId(65468456 + i);
            user.setGroupId(654565 + i);
            user.setPageInfo(pageInfo);
            user.setCreateTime(new Date());
            user.setUsername("第三方" + i);
            group.addUser(user);
        }
        Runnable run = new Runnable() {

            public void run() {
                try {
                    mapper.writeValueAsString(group);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            };
        };

        run(10000, run);
    }

    public static void run(int count, Runnable run) {
        start = System.currentTimeMillis();
        while (count-- > 0) {
            new Thread(run).start();
        }
    }

    public static void calc() {
        long totalTime = System.currentTimeMillis() - start;
        System.out.println("耗时:" + totalTime);
    }
}

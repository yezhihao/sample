package org.sample.seckill.commons.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * 消息消费者
 */
@Component
public class KafkaConsumer {

    @KafkaListener(topics = {"test1", "test2"})
    public void processMessage(Object content) {
        System.out.println(content);
    }

}
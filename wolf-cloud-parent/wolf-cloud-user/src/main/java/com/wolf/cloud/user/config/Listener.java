package com.wolf.cloud.user.config;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;

/**
 * Created on 17/12/26 14:20.
 *
 * @author wolf
 */

public class Listener {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());


    @KafkaListener(topics = {"wolf-test-zk-kafka"})
    public void listen(ConsumerRecord<?, ?> record) {
        logger.info("kafka的key: " + record.key());
        logger.info("kafka的value: " + record.value().toString());
    }
}
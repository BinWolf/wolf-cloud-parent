package com.wolf.cloud.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 17/12/26 14:14.
 *
 * @author wolf
 */
@RestController
@RequestMapping("/kafka")
public class CollectController {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private KafkaTemplate kafkaTemplate;

    @RequestMapping(value = "/send/{message}", method = RequestMethod.GET)
    public String sendKafka(@PathVariable("message") String message) {
        try {
            logger.info("kafka的消息={}", message);
            kafkaTemplate.send("wolf-test-zk-kafka","key", message);
            logger.info("发送kafka成功.");
        } catch (Exception e) {
            logger.error("发送kafka失败", e);
        }
        return message;
    }

}
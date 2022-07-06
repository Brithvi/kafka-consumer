package com.kafka;

import com.dao.KafkaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/*
 * @created 06/07/2022 - 11:00 AM
 * @project kafka-consumer
 * @author Brithivi
 */
@Component
public class Consumer {
    @Autowired
    private KafkaDAO kafkaDAO;

    @KafkaListener(topics = "topic", groupId = "test-consumer-group")
    public void processMessage(String content) {
        System.out.println("Message received by consumer 1: " + content);
        kafkaDAO.insertRecord(content);
    }
}

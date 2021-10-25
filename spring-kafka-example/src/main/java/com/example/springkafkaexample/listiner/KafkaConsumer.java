package com.example.springkafkaexample.listiner;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
/*    @KafkaListener(topics = "${message.topic.name}", groupId = "group_id", containerFactory = "kafkaListenerContainerFactory")
    public void consume(String message) {
        System.out.println("Consumed message: ==================>>>>" + message);
    }*/
}

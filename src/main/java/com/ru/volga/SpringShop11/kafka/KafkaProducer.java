//package com.ru.volga.SpringShop11.kafka;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Component;
//
//@Component
//public class KafkaProducer {
//    private final KafkaTemplate<String, String> kafkaTemplate;
//
//    @Autowired
//    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate){
//        this.kafkaTemplate = kafkaTemplate;
//    }
//
//    public void sendMessage(String message){
//        kafkaTemplate.send("test-topic", message);
//    }
//
//}

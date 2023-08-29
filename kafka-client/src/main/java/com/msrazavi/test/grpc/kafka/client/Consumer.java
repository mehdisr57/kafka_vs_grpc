package com.msrazavi.test.grpc.kafka.client;

import com.msrazavi.test.grpc.common.Topics;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class Consumer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    //    @SendTo(Topics.RESPONSE)
    @KafkaListener(topics = Topics.REQUEST)
    public void greetingListener(ConsumerRecord<String, String> consumerRecord) {
        System.out.println(consumerRecord);
        ProducerRecord<String, String> record = new ProducerRecord<>(Topics.RESPONSE, consumerRecord.value() + " received");
        record.headers().add(KafkaHeaders.CORRELATION_ID, consumerRecord.headers().headers(KafkaHeaders.CORRELATION_ID).iterator().next().value());
        kafkaTemplate.send(record);
    }
}

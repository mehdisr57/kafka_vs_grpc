package com.msrazavi.test.grpc.kafka.server;

import com.msrazavi.test.grpc.common.Topics;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class RequestReply {

    private final ReplyingKafkaTemplate<String, String, String> kafkaTemplate;

    public Mono<String> publishMessage(String message) {
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>(Topics.REQUEST, message);
        producerRecord.headers().add(KafkaHeaders.REPLY_TOPIC, Topics.RESPONSE.getBytes(StandardCharsets.UTF_8));
        RequestReplyFuture<String, String, String> future = kafkaTemplate.sendAndReceive(producerRecord);
        return Mono.fromFuture(future)
                .map(ConsumerRecord::value);
    }
}

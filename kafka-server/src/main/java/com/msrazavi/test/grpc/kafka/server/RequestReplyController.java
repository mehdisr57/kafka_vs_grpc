package com.msrazavi.test.grpc.kafka.server;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class RequestReplyController {

    private final RequestReply requestReply;

    @GetMapping(value = "/request", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<String> requestReply(@RequestBody String s) {
        return requestReply.publishMessage(s);
    }
}

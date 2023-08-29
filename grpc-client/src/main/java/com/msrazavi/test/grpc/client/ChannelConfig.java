package com.msrazavi.test.grpc.client;

import io.grpc.Grpc;
import io.grpc.InsecureChannelCredentials;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChannelConfig {

    @Bean
    public ManagedChannel channel() {
        return Grpc.newChannelBuilder("localhost:5050", InsecureChannelCredentials.create())
                .build();
    }

}

package com.msrazavi.test.grpc.client;

import io.grpc.*;
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChannelConfig {

    @Bean
    public ManagedChannel channel() {
        NameResolverRegistry.getDefaultRegistry().register(new ExchangeNameResolverProvider());
        return NettyChannelBuilder.forAddress("localhost", 1234)
                .defaultLoadBalancingPolicy("round-robin")
                .usePlaintext()
                .build();
    }
}

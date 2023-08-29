package com.msrazavi.test.grpc.server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class GrpcServerConfig {

    @Bean
    public GrpcServer grpcServer() throws IOException {
        GrpcServer grpcServer = new GrpcServer(5050);
        grpcServer.start();
        return grpcServer;
    }
}

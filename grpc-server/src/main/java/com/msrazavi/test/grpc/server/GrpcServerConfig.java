package com.msrazavi.test.grpc.server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.security.SecureRandom;

@Configuration
public class GrpcServerConfig {

    private SecureRandom random = new SecureRandom();

    @Bean
    public GrpcServer grpcServer() throws IOException {
        GrpcServer grpcServer = new GrpcServer(5000 + random.nextInt(20));
        grpcServer.start();
        return grpcServer;
    }
}

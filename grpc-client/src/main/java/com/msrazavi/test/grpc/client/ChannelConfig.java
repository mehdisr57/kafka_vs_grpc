package com.msrazavi.test.grpc.client;

import io.grpc.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChannelConfig {

    @Bean
    public ManagedChannel channel() {
        return Grpc.newChannelBuilder("localhost:5050", InsecureChannelCredentials.create())
                .intercept(new ClientInterceptor() {
                    @Override
                    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(MethodDescriptor<ReqT, RespT> method, CallOptions callOptions, Channel next) {
                        return new ForwardingClientCall.SimpleForwardingClientCall<ReqT, RespT>(next.newCall(method, callOptions)) {

                            @Override
                            public void sendMessage(ReqT message) {
                                super.sendMessage(message);
                            }

                            @Override
                            public void start(Listener<RespT> responseListener, Metadata headers) {
                                super.start(new ForwardingClientCallListener.SimpleForwardingClientCallListener<RespT>(responseListener) {
                                    @Override
                                    public void onMessage(RespT message) {
                                        super.onMessage(message);
                                    }
                                }, headers);
                            }
                        };
                    }
                })
                .build();
    }

}

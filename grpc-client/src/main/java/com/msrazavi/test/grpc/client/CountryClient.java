package com.msrazavi.test.grpc.client;

import com.google.common.util.concurrent.ListenableFuture;
import com.msrazavi.test.grpc.common.model.Country;
import com.msrazavi.test.grpc.common.model.CountryServiceGrpc;
import io.grpc.Channel;
import io.grpc.Context;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Service
public class CountryClient {

    private final CountryServiceGrpc.CountryServiceFutureStub futureStub;

    public CountryClient(Channel channel) {
        this.futureStub = CountryServiceGrpc.newFutureStub(channel);
    }

    public Country save(String name) {
        ListenableFuture<Country> future = futureStub.save(Country.newBuilder()
                .setName(name)
                .build());
        try {
            return future.get(20, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }

        return Country.getDefaultInstance();
    }
}

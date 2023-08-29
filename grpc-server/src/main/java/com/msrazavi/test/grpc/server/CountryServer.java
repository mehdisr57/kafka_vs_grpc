package com.msrazavi.test.grpc.server;

import com.msrazavi.test.grpc.common.model.Country;
import com.msrazavi.test.grpc.common.model.CountryServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class CountryServer extends CountryServiceGrpc.CountryServiceImplBase {

    @Override
    public void save(Country request, StreamObserver<Country> responseObserver) {

        System.out.println(request);
        try {
            TimeUnit.SECONDS.sleep(45);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }

        responseObserver.onNext(Country.newBuilder()
                .setName(request.getName() + " - received")
                .setSize(new Random().nextInt())
                .build());
        responseObserver.onCompleted();
    }
}

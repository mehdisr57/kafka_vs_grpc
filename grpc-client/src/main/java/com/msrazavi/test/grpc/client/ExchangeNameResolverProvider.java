package com.msrazavi.test.grpc.client;

import io.grpc.NameResolver;
import io.grpc.NameResolverProvider;

import java.net.URI;

public class ExchangeNameResolverProvider extends NameResolverProvider {

    @Override
    public NameResolver newNameResolver(URI targetUri, NameResolver.Args args) {
        return new ExchangeNameResolver(targetUri);
    }

    @Override
    protected boolean isAvailable() {
        return true;
    }

    @Override
    protected int priority() {
        return 5;
    }

    @Override
    // gRPC choose the first NameResolverProvider that supports the target URI scheme.
    public String getDefaultScheme() {
        return "exchange";
    }
}

package org.gib.service;

import io.quarkus.grpc.GrpcService;

import io.smallrye.mutiny.Uni;
import org.gib.HelloGrpc;
import org.gib.HelloReply;
import org.gib.HelloRequest;

@GrpcService
public class HelloGrpcService implements HelloGrpc {

    @Override
    public Uni<HelloReply> sayHello(HelloRequest request) {
        return Uni.createFrom().item("Hello " + request.getName() + "!")
                .map(msg -> HelloReply.newBuilder().setMessage(msg).build());
    }

}

package org.gib.service;

import io.grpc.stub.StreamObserver;
import io.quarkus.grpc.GrpcService;
import jakarta.inject.Singleton;
import org.gib.Square;
import org.gib.SquareServiceGrpc;

@GrpcService
public class SquareServiceImpl extends SquareServiceGrpc.SquareServiceImplBase {

    @Override
    public void calculateSquares(Square.SquareRequest request, StreamObserver<Square.SquareResponse> responseObserver) {
        int number = request.getNumber();
        long total = 0;
        Square.SquareResponse.Builder responseBuilder = Square.SquareResponse.newBuilder();

        for (int i = 1; i <= number; i++) {
            int square = i * i;
            total += square;
            Square.SquareResult result = Square.SquareResult.newBuilder().setNumber(i).setSquare(square).build();
            responseBuilder.addResults(result);
        }
        responseBuilder.setTotal(total);
        responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
    }
}

package org.gib.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.gib.model.SquareRequest;
import org.gib.model.SquareResponse;
import org.gib.model.SquareResult;
import io.smallrye.mutiny.Uni;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class SquareCalculator {

    public Uni<SquareResponse> calculate(SquareRequest request) {
        return Uni.createFrom().item(() -> {
            List<SquareResult> results = new ArrayList<>();
            long total = 0;

            int number = request.getNumber();

            for (int i = 1; i <= number; i++) {
                int square = i * i;
                total += square;
                results.add(new SquareResult(i, square));
            }

            return new SquareResponse(results, total);
        });
    }
}

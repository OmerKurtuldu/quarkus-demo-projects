package org.gelirler.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.gelirler.model.SquareRequest;
import org.gelirler.model.SquareResponse;
import org.gelirler.model.SquareResult;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class SquareCalculator {

    public SquareResponse calculate(SquareRequest request) {
        List<SquareResult> results = new ArrayList<>();
        long total = 0;

        int number = request.getNumber();

        for (int i = 1; i <= number; i++) {
            int square = i * i;
            total += square;
            results.add(new SquareResult(i, square));
        }

        return new SquareResponse(results, total);
    }
} 
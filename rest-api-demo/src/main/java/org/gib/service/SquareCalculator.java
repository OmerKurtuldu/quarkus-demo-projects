package org.gib.service;

import org.gib.model.SquareRequest;
import org.gib.model.SquareResponse;
import org.gib.model.SquareResult;

import java.util.ArrayList;
import java.util.List;

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

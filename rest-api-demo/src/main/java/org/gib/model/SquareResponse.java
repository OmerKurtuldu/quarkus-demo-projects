package org.gib.model;

import java.util.List;

public class SquareResponse {
    private List<SquareResult> results;
    private long total;

    public SquareResponse(List<SquareResult> results, long total) {
        this.results = results;
        this.total = total;
    }

    public List<SquareResult> getResults() {
        return results;
    }

    public long getTotal() {
        return total;
    }
}

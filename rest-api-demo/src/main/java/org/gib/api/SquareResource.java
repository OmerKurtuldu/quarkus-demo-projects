package org.gib.api;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.gib.model.SquareRequest;
import org.gib.model.SquareResponse;
import org.gib.service.SquareCalculator;

@Path("/square")
public class SquareResource {

    private final SquareCalculator squareCalculator = new SquareCalculator();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public SquareResponse calculateSquares(SquareRequest request) {
        return squareCalculator.calculate(request);
    }
}

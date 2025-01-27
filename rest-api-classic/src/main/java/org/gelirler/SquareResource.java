package org.gelirler;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.gelirler.model.SquareRequest;
import org.gelirler.model.SquareResponse;
import org.gelirler.service.SquareCalculator;

@Path("/square")
public class SquareResource {

    @Inject
    SquareCalculator squareCalculator;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public SquareResponse calculateSquares(SquareRequest request) {
        return squareCalculator.calculate(request);
    }
} 
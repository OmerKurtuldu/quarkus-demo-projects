package org.gib.api;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.gib.model.SquareRequest;
import org.gib.model.SquareResponse;
import org.gib.service.SquareCalculator;
import io.smallrye.mutiny.Uni;

@Path("/square")
public class SquareResource {

    @Inject
    SquareCalculator squareCalculator;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<SquareResponse> calculateSquares(SquareRequest request) {
        return squareCalculator.calculate(request);
    }
}

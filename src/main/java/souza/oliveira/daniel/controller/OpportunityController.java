package souza.oliveira.daniel.controller;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import souza.oliveira.daniel.service.OpportunityService;

import java.time.LocalDateTime;

@Path("/api/opportunities")
public class OpportunityController {

    private final OpportunityService opportunityService;

    @Inject
    public OpportunityController(OpportunityService opportunityService) {
        this.opportunityService = opportunityService;
    }

    @GET
    @Path("/report")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response generateReport(){
        final var csv = this.opportunityService.generateCSVOpportunityReport();
        final var fileNameHeader = String
                .format("attachment; filename=%s--oportunidades-vendas.csv", LocalDateTime.now().toString());

        return Response.ok(csv, MediaType.APPLICATION_OCTET_STREAM)
                .header("content-disposition", fileNameHeader)
                .build();
    }
}

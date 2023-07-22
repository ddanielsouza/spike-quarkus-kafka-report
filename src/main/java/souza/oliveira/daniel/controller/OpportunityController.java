package souza.oliveira.daniel.controller;

import io.quarkus.security.Authenticated;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.JsonWebToken;
import souza.oliveira.daniel.service.OpportunityService;

import java.time.LocalDateTime;

@Path("/api/opportunities")
@Authenticated
public class OpportunityController {

    private final OpportunityService opportunityService;
    private final JsonWebToken jsonWebToken;

    @Inject
    public OpportunityController(OpportunityService opportunityService, JsonWebToken jsonWebToken) {
        this.opportunityService = opportunityService;
        this.jsonWebToken = jsonWebToken;
    }

    @GET
    @Path("/report/csv")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    @RolesAllowed({"manager", "user"})
    public Response generateReportCsv(){
        final var csv = this.opportunityService.generateCSVOpportunityReport();
        final var fileNameHeader = String
                .format("attachment; filename=%s--oportunidades-vendas.csv", LocalDateTime.now().toString());

        return Response.ok(csv, MediaType.APPLICATION_OCTET_STREAM)
                .header("content-disposition", fileNameHeader)
                .build();
    }

    @GET
    @Path("/report")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"manager", "user"})
    public Response generateReport(){
        final var opportunities = this.opportunityService.generateOpportunityData();

        return Response.ok(opportunities)
                .build();
    }
}

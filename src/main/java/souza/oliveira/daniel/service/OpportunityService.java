package souza.oliveira.daniel.service;

import jakarta.enterprise.context.ApplicationScoped;
import souza.oliveira.daniel.dto.OpportunityDTO;
import souza.oliveira.daniel.dto.ProposalDTO;
import souza.oliveira.daniel.dto.QuotationDTO;

import java.io.ByteArrayInputStream;
import java.util.List;

@ApplicationScoped
public interface OpportunityService {
    void buildOpportunity(ProposalDTO proposal);
    void saveQuotation(QuotationDTO quotation);
    List<OpportunityDTO> generateOpportunityData();
}

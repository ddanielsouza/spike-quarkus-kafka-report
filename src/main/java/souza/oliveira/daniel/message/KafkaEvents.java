package souza.oliveira.daniel.message;

import io.smallrye.common.annotation.Blocking;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import souza.oliveira.daniel.dto.ProposalDTO;
import souza.oliveira.daniel.dto.QuotationDTO;
import souza.oliveira.daniel.service.OpportunityService;

@ApplicationScoped
public class KafkaEvents {
    private final Logger LOG = LoggerFactory.getLogger(KafkaEvents.class);
    private final OpportunityService opportunityService;

    @Inject
    public KafkaEvents(OpportunityService opportunityService) {
        this.opportunityService = opportunityService;
    }


    @Incoming("proposal")
    @Transactional
    public void receiveProposal(ProposalDTO proposal){
        LOG.info("-- Recebendo uma nova proposta --");
        opportunityService.buildOpportunity(proposal);
    }

    @Incoming("quotation")
    @Transactional
    @Blocking
    public void receiveQuotation(QuotationDTO quotation){
        LOG.info("-- Recebendo uma nova cotação --");
        opportunityService.saveQuotation(quotation);
    }

}

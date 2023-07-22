package souza.oliveira.daniel.service.impl;

import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.opentracing.Traced;
import souza.oliveira.daniel.dto.OpportunityDTO;
import souza.oliveira.daniel.dto.ProposalDTO;
import souza.oliveira.daniel.dto.QuotationDTO;
import souza.oliveira.daniel.entity.OpportunityEntity;
import souza.oliveira.daniel.entity.QuotationEntity;
import souza.oliveira.daniel.repository.OpportunityRepository;
import souza.oliveira.daniel.repository.QuotationRepository;
import souza.oliveira.daniel.service.OpportunityService;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
@Traced
public class OpportunityServiceImpl implements OpportunityService {

    private final QuotationRepository quotationRepository;
    private final OpportunityRepository opportunityRepository;

    @Inject
    public OpportunityServiceImpl(
            QuotationRepository quotationRepository,
            OpportunityRepository opportunityRepository) {

        this.quotationRepository = quotationRepository;
        this.opportunityRepository = opportunityRepository;
    }

    @Override
    @Transactional
    public void buildOpportunity(ProposalDTO proposal) {
        final var lastQuotation = this.quotationRepository.getLastQuotation();

        final BigDecimal lastQuotationDollar = lastQuotation.isPresent()
                ? lastQuotation.get().getCurrencyPrice()
                : BigDecimal.ZERO;

        final var opportunity = OpportunityEntity.builder()
                .date(LocalDateTime.now())
                .proposalId(proposal.getProposalId())
                .customer(proposal.getCustomer())
                .priceTonne(proposal.getPriceTonne())
                .lastDollarQuotation(lastQuotationDollar)
                .build();

        this.opportunityRepository.persist(opportunity);
    }

    @Override
    @Transactional
    public void saveQuotation(QuotationDTO quotation) {
        final var entity = QuotationEntity.builder()
                .date(LocalDateTime.now())
                .currencyPrice(quotation.getCurrencyPrice())
                .build();

        this.quotationRepository.persist(entity);
    }

    @Override
    public List<OpportunityDTO> generateOpportunityData() {
        return this.opportunityRepository
                .findAll(Sort.descending("date"))
                .list()
                .stream()
                .map( item -> OpportunityDTO.builder()
                        .proposalId(item.getId())
                        .priceTonne(item.getPriceTonne())
                        .customer(item.getCustomer())
                        .lastDollarQuotation(item.getLastDollarQuotation())
                        .build())
                .collect(Collectors.toList());
    }
}

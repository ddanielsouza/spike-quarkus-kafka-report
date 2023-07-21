package souza.oliveira.daniel.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

@Data
@Builder
@Jacksonized
@AllArgsConstructor
public class ProposalDTO {
    private Long proposalId;
    private String customer;
    private BigDecimal priceTonne;
    private BigDecimal lastDollarQuotation;
}

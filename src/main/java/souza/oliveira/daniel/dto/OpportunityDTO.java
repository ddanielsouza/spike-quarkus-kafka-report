package souza.oliveira.daniel.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@Jacksonized
public class OpportunityDTO {
    private Long proposalId;
    private String customer;
    private BigDecimal priceTonne;
    private BigDecimal lastDollarQuotation;
}

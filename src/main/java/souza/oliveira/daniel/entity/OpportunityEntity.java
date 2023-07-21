package souza.oliveira.daniel.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Table(name="opportunities")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OpportunityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime date;
    @Column(name = "proposal_id")
    private Long proposalId;
    private String customer;
    @Column(name = "price_tonne", precision = 32, scale = 6)
    private BigDecimal priceTonne;
    @Column(name = "last_dollar_quotation", precision = 32, scale = 6)
    private BigDecimal lastDollarQuotation;

}

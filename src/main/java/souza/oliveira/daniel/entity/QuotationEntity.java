package souza.oliveira.daniel.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Table(name="quotations")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuotationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime date;
    @Column(name = "currency_price", precision = 32, scale = 6)
    private BigDecimal currencyPrice;
}

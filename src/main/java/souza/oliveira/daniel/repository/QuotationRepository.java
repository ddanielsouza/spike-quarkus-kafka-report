package souza.oliveira.daniel.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import souza.oliveira.daniel.entity.QuotationEntity;

import java.util.Optional;

@ApplicationScoped
public class QuotationRepository implements PanacheRepository<QuotationEntity> {
    public Optional<QuotationEntity> getLastQuotation() {
        final String sql = "SELECT q FROM QuotationEntity q WHERE currencyPrice IS NOT NULL";

        return this.find(sql, Sort.descending("date"))
                .firstResultOptional();
    }
}

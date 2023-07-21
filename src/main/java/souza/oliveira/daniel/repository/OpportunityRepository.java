package souza.oliveira.daniel.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import souza.oliveira.daniel.entity.OpportunityEntity;

@ApplicationScoped
public class OpportunityRepository implements PanacheRepository<OpportunityEntity> {
}

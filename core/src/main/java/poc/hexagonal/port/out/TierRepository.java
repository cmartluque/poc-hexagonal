package poc.hexagonal.port.out;

import poc.hexagonal.domain.Tier;

import java.util.Optional;

public interface TierRepository {
    Optional<Tier> findById(Long id);

    void update(Tier tier);
}

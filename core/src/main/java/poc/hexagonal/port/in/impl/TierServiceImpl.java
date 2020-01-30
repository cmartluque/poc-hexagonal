package poc.hexagonal.port.in.impl;

import lombok.RequiredArgsConstructor;
import poc.hexagonal.domain.Event;
import poc.hexagonal.domain.Tier;
import poc.hexagonal.error.BusinessError;
import poc.hexagonal.port.in.TierService;
import poc.hexagonal.port.out.EventDataAccess;
import poc.hexagonal.port.out.TierDataAccess;

import javax.validation.constraints.NotNull;

@RequiredArgsConstructor
public class TierServiceImpl implements TierService {
    @NotNull
    private TierDataAccess tierDataAccess;
    @NotNull
    private EventDataAccess eventDataAccess;

    @Override
    public void addEvent(Long tierId, Long eventId) {
        final Tier tier = findTierById(tierId);
        final Event event = findEventById(eventId);
        tier.add(event);
        tierDataAccess.update(tier);
    }

    private Tier findTierById(Long id){
        return tierDataAccess.findById(id).orElseThrow(BusinessError::new);
    }

    private Event findEventById(Long id){
        return eventDataAccess.findById(id).orElseThrow(BusinessError::new);
    }
}

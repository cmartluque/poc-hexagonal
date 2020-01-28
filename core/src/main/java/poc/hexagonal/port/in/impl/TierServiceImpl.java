package poc.hexagonal.port.in.impl;

import lombok.RequiredArgsConstructor;
import poc.hexagonal.domain.Event;
import poc.hexagonal.domain.Tier;
import poc.hexagonal.error.BusinessError;
import poc.hexagonal.port.in.TierService;
import poc.hexagonal.port.out.EventRepository;
import poc.hexagonal.port.out.TierRepository;

import javax.validation.constraints.NotNull;

@RequiredArgsConstructor
public class TierServiceImpl implements TierService {
    @NotNull
    private TierRepository tierRepository;
    @NotNull
    private EventRepository eventRepository;

    @Override
    public void addEvent(Long tierId, Long eventId) {
        final Tier tier = findTierById(tierId);
        final Event event = findEventById(eventId);
        tier.add(event);
        tierRepository.update(tier);
    }

    private Tier findTierById(Long id){
        return tierRepository.findById(id).orElseThrow(BusinessError::new);
    }

    private Event findEventById(Long id){
        return eventRepository.findById(id).orElseThrow(BusinessError::new);
    }
}

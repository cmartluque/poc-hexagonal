package poc.hexagonal.port.out;

import poc.hexagonal.domain.Event;

import java.util.Optional;

public interface EventDataAccess {
    Optional<Event> findById(Long eventId);
}

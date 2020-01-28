package poc.hexagonal.port.in;

public interface TierService {
    void addEvent(Long tierId, Long eventId);
}

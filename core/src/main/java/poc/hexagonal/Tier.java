package poc.hexagonal;

import poc.hexagonal.error.BusinessError;

import java.util.ArrayList;
import java.util.List;

public class Tier {
    private Integer lengthInMinutes;
    private List<Event> events;

    public Tier(Integer lengthInMinutes) {
        this.lengthInMinutes = lengthInMinutes;
        events = new ArrayList<>();
    }


    public void add(Event event) {
        if (!fits(event)) {
            throwDoesNotFitException();
        }
        events.add(event);
    }

    private boolean fits(Event event) {
        return remainingTime() >= event.getLength();
    }

    private void throwDoesNotFitException() {
        throw new BusinessError();
    }

    public Integer remainingTime() {
        return lengthInMinutes - currentOccupiedTime();
    }

    private Integer currentOccupiedTime() {
        return events.stream()
                .map(Event::getLength)
                .reduce(0, (subtotal, currentConference) ->
                        subtotal + currentConference);
    }

    public List<Event> events(){
        return new ArrayList<>(events);
    }
}

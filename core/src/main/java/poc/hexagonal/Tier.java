package poc.hexagonal;

import lombok.Value;

import java.util.ArrayList;
import java.util.List;

@Value
public class Tier {
    private Integer lengthInMinutes;
    private List<Conference> conferences;

    public Tier(Integer lengthInMinutes){
        this.lengthInMinutes = lengthInMinutes;
        conferences = new ArrayList<>();
    }


    public void add(Conference conference) {
        if (!fits(conference)) {
            throwDoesNotFitException();
        }
        conferences.add(conference);
    }

    private boolean fits(Conference conference) {
        return remainingTime() >= conference.getLength();
    }

    private void throwDoesNotFitException() {
        throw new RuntimeException();
    }

    public Integer remainingTime() {
        return lengthInMinutes - currentOccupiedTime();
    }

    private Integer currentOccupiedTime() {
        return conferences.stream()
                .map(Conference::getLength)
                .reduce(0, (subtotal, currentConference) ->
                        subtotal + currentConference);
    }
}

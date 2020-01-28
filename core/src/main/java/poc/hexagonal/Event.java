package poc.hexagonal;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.Value;

@AllArgsConstructor
@Value
public class Event {
    @NonNull
    private Integer length;
}

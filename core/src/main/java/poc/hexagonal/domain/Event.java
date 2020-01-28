package poc.hexagonal.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.Value;

@AllArgsConstructor
@Data
public class Event {
    @NonNull
    private Integer length;
}

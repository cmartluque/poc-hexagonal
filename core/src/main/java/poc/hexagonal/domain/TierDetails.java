package poc.hexagonal.domain;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class TierDetails {
    private Long id;
    private Integer lengthInMinutes;
}

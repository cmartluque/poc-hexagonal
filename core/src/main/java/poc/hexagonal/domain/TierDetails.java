package poc.hexagonal.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TierDetails {
    private Long id;
    private Integer lengthInMinutes;
}

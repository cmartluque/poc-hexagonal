package poc.hexagonal.domain;

import lombok.AllArgsConstructor;
import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
@AllArgsConstructor
public class AgendaDetails {
    @NotNull
    private Long id;
    @NotNull
    private Integer maxNumberOfTiers;
}

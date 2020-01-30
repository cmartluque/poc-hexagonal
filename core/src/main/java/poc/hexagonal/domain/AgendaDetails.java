package poc.hexagonal.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class AgendaDetails {
    @NotNull
    private Long id;
    @NotNull
    private Integer maxNumberOfTiers;
}

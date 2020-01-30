package poc.hexagonal.port.in.impl;

import lombok.RequiredArgsConstructor;
import poc.hexagonal.domain.Agenda;
import poc.hexagonal.domain.Tier;
import poc.hexagonal.error.BusinessError;
import poc.hexagonal.port.in.AgendaService;
import poc.hexagonal.port.out.AgendaDataAccess;
import poc.hexagonal.port.out.TierDataAccess;

import javax.validation.constraints.NotNull;

@RequiredArgsConstructor
public class AgendaServiceImpl implements AgendaService {
    @NotNull
    private AgendaDataAccess agendaDataAccess;
    @NotNull
    private TierDataAccess tierDataAccess;

    @Override
    public void addTier(Long agendaId, Long tierId) {
        final Agenda agenda = findAgendaById(agendaId);
        final Tier tier = findTierById(tierId);

        agenda.add(tier);
        agendaDataAccess.update(agenda);
    }

    private Agenda findAgendaById(Long id) {
        return agendaDataAccess.findById(id).orElseThrow(BusinessError::new);
    }

    private Tier findTierById(Long id) {
        return tierDataAccess.findById(id).orElseThrow(BusinessError::new);
    }
}

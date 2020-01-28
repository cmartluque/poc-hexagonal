package poc.hexagonal.port.in.impl;

import lombok.RequiredArgsConstructor;
import poc.hexagonal.domain.Agenda;
import poc.hexagonal.domain.Tier;
import poc.hexagonal.error.BusinessError;
import poc.hexagonal.port.in.AgendaService;
import poc.hexagonal.port.out.AgendaRepository;
import poc.hexagonal.port.out.TierRepository;

import javax.validation.constraints.NotNull;

@RequiredArgsConstructor
public class AgendaServiceImpl implements AgendaService {
    @NotNull
    private AgendaRepository agendaRepository;
    @NotNull
    private TierRepository tierRepository;

    @Override
    public void addTier(Long agendaId, Long tierId) {
        final Agenda agenda = findAgendaById(agendaId);
        final Tier tier = findTierById(tierId);

        agenda.add(tier);
        agendaRepository.update(agenda);
    }

    private Agenda findAgendaById(Long id) {
        return agendaRepository.findById(id).orElseThrow(BusinessError::new);
    }

    private Tier findTierById(Long id) {
        return tierRepository.findById(id).orElseThrow(BusinessError::new);
    }
}

package com.hexagonal.adapter.dataaccess.service;

import com.hexagonal.entity.AgendaEntity;
import com.hexagonal.entity.TierEntity;
import com.hexagonal.error.PersistenceError;
import com.hexagonal.mapper.ToDomainDetailsMapper;
import com.hexagonal.repository.AgendaEntityRepository;
import com.hexagonal.repository.TierEntityRepository;
import poc.hexagonal.domain.Agenda;
import poc.hexagonal.domain.AgendaDetails;
import poc.hexagonal.domain.Tier;
import poc.hexagonal.domain.TierDetails;
import poc.hexagonal.port.out.AgendaDataAccess;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AgendaPersistenceService implements AgendaDataAccess {
    @NotNull
    private AgendaEntityRepository agendaRepository;
    @NotNull
    private TierEntityRepository tierRepository;
    @NotNull
    private ToDomainDetailsMapper mapper;

    @Override
    public Optional<Agenda> findById(Long id) {
        final AgendaEntity agendaEntity = findAgendaById(id);
        final AgendaDetails agendaDetails = toAgendaDetails(agendaEntity);
        final List<Tier> tiers = findAllAgendaTiers(id)
                .stream()
                .map(this::toTier)
                .collect(Collectors.toList());
        return Optional.of(new Agenda(agendaDetails, tiers));
    }

    private AgendaEntity findAgendaById(Long id) {
        return agendaRepository.findById(id).orElseThrow(PersistenceError::new);
    }

    private AgendaDetails toAgendaDetails(AgendaEntity agendaEntity) {
        return mapper.toAgendaDetails(agendaEntity);
    }

    private List<TierEntity> findAllAgendaTiers(Long id) {
        return tierRepository.findByAgendaId(id);
    }

    private Tier toTier(TierEntity tierEntity) {
        final TierDetails tierDetails = mapper.toTierDetails(tierEntity);
        return new Tier(tierDetails);
    }


    @Override
    public void update(Agenda agenda) {

    }
}

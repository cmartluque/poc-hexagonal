package com.hexagonal.mapper;

import com.hexagonal.entity.AgendaEntity;
import com.hexagonal.entity.TierEntity;
import poc.hexagonal.domain.AgendaDetails;
import poc.hexagonal.domain.TierDetails;

public interface ToDomainDetailsMapper {
    AgendaDetails toAgendaDetails(AgendaEntity agendaEntity);
    TierDetails toTierDetails(TierEntity tierEntity);
}

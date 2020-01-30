package com.hexagonal.repository;

import com.hexagonal.entity.TierEntity;

import java.util.List;

public interface TierEntityRepository {
    List<TierEntity> findByAgendaId(Long id);
}

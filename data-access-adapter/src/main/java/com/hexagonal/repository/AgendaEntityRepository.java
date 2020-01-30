package com.hexagonal.repository;

import com.hexagonal.entity.AgendaEntity;

import java.util.Optional;

public interface AgendaEntityRepository {
    Optional<AgendaEntity> findById(Long id);
}

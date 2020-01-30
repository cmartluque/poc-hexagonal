package com.hexagonal.adapter.dataaccess;

import com.hexagonal.adapter.dataaccess.service.AgendaPersistenceService;
import com.hexagonal.entity.AgendaEntity;
import com.hexagonal.entity.TierEntity;
import com.hexagonal.mapper.ToDomainDetailsMapper;
import com.hexagonal.repository.AgendaEntityRepository;
import com.hexagonal.repository.TierEntityRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import poc.hexagonal.domain.Agenda;
import poc.hexagonal.domain.AgendaDetails;
import poc.hexagonal.domain.TierDetails;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AgendaPersistenceServiceTest {
    private static final Long AGENDA_ID = 1L;
    @InjectMocks
    private AgendaPersistenceService agendaPersistenceService;
    @Mock
    private AgendaEntityRepository agendaRepository;
    @Mock
    private TierEntityRepository tierRepository;
    @Mock
    private ToDomainDetailsMapper mapper;

    @Test
    public void findById_shouldReturnAgendaAndTiers_whenExistAnAgendaForGivenId() {
        //given
        final AgendaEntity agendaEntity = mock(AgendaEntity.class);
        when(agendaRepository.findById(anyLong())).thenReturn(Optional.of(agendaEntity));
        final TierEntity tierEntity = mock(TierEntity.class);
        when(tierRepository.findByAgendaId(anyLong())).thenReturn(Arrays.asList(tierEntity));
        final TierDetails tierDetails = mock(TierDetails.class);
        when(mapper.toTierDetails(any(TierEntity.class))).thenReturn(tierDetails);
        final AgendaDetails agendaDetails = mock(AgendaDetails.class);
        when((mapper.toAgendaDetails(any(AgendaEntity.class)))).thenReturn(agendaDetails);

        //when
        final Optional<Agenda> foundAgenda = agendaPersistenceService.findById(AGENDA_ID);
        //then
        assertTrue(foundAgenda.isPresent());
        verify(agendaRepository).findById(AGENDA_ID);
        verify(tierRepository).findByAgendaId(AGENDA_ID);
        verify(mapper).toAgendaDetails(agendaEntity);
        verify(mapper).toTierDetails(tierEntity);

        verifyNoMoreInteractions(agendaRepository);
        verifyNoMoreInteractions(tierRepository);
        verifyNoMoreInteractions(mapper);

    }


}
package poc.hexagonal.port.in.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import poc.hexagonal.domain.Agenda;
import poc.hexagonal.domain.Tier;
import poc.hexagonal.error.BusinessError;
import poc.hexagonal.port.out.AgendaRepository;
import poc.hexagonal.port.out.TierRepository;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AgendaServiceImplTest {
    private static final Long AGENDA_ID = 1L;
    private static final Long TIER_ID = 1L;

    @InjectMocks
    private AgendaServiceImpl agendaService;
    @Mock
    private AgendaRepository agendaRepository;
    @Mock
    private TierRepository tierRepository;
    @Mock
    private Agenda agenda;
    @Mock
    private Tier tier;

    @Test
    public void addTier_souldAddTierOfGivenIdToAgendaOfGivenId_whenBothExists() {
        //given
        when(agendaRepository.findById(anyLong())).thenReturn(Optional.of(agenda));
        when(tierRepository.findById(anyLong())).thenReturn(Optional.of(tier));
        //when
        agendaService.addTier(AGENDA_ID, TIER_ID);
        //then
        verify(agendaRepository).findById(AGENDA_ID);
        verify(tierRepository).findById(TIER_ID);
        verify(agenda).add(tier);
        verify(agendaRepository).update(agenda);

        verifyNoMoreInteractions(agendaRepository);
        verifyNoMoreInteractions(tierRepository);
        verifyNoMoreInteractions(agenda);
        verifyNoMoreInteractions(tier);
    }

    @Test(expected = BusinessError.class)
    public void addTier_shouldThrowAnException_whenNoAgendaIsFoundForGivenId(){
        //given
        when(agendaRepository.findById(anyLong())).thenReturn(Optional.empty());
        //when
        agendaService.addTier(AGENDA_ID, TIER_ID);
    }

    @Test(expected = BusinessError.class)
    public void addTier_shouldThrowAnException_whenNoTierIsFoundForGivenId(){
        //given
        when(agendaRepository.findById(anyLong())).thenReturn(Optional.of(agenda));
        when(tierRepository.findById(anyLong())).thenReturn(Optional.empty());
        //when
        agendaService.addTier(AGENDA_ID, TIER_ID);
    }
}

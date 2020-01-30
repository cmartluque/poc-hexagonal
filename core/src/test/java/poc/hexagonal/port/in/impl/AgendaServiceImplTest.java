package poc.hexagonal.port.in.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import poc.hexagonal.domain.Agenda;
import poc.hexagonal.domain.Tier;
import poc.hexagonal.error.BusinessError;
import poc.hexagonal.port.out.AgendaDataAccess;
import poc.hexagonal.port.out.TierDataAccess;

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
    private AgendaDataAccess agendaDataAccess;
    @Mock
    private TierDataAccess tierDataAccess;
    @Mock
    private Agenda agenda;
    @Mock
    private Tier tier;

    @Test
    public void addTier_souldAddTierOfGivenIdToAgendaOfGivenId_whenBothExists() {
        //given
        when(agendaDataAccess.findById(anyLong())).thenReturn(Optional.of(agenda));
        when(tierDataAccess.findById(anyLong())).thenReturn(Optional.of(tier));
        //when
        agendaService.addTier(AGENDA_ID, TIER_ID);
        //then
        verify(agendaDataAccess).findById(AGENDA_ID);
        verify(tierDataAccess).findById(TIER_ID);
        verify(agenda).add(tier);
        verify(agendaDataAccess).update(agenda);

        verifyNoMoreInteractions(agendaDataAccess);
        verifyNoMoreInteractions(tierDataAccess);
        verifyNoMoreInteractions(agenda);
        verifyNoMoreInteractions(tier);
    }

    @Test(expected = BusinessError.class)
    public void addTier_shouldThrowAnException_whenNoAgendaIsFoundForGivenId(){
        //given
        when(agendaDataAccess.findById(anyLong())).thenReturn(Optional.empty());
        //when
        agendaService.addTier(AGENDA_ID, TIER_ID);
    }

    @Test(expected = BusinessError.class)
    public void addTier_shouldThrowAnException_whenNoTierIsFoundForGivenId(){
        //given
        when(agendaDataAccess.findById(anyLong())).thenReturn(Optional.of(agenda));
        when(tierDataAccess.findById(anyLong())).thenReturn(Optional.empty());
        //when
        agendaService.addTier(AGENDA_ID, TIER_ID);
    }
}

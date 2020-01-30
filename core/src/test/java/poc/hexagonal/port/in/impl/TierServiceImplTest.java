package poc.hexagonal.port.in.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import poc.hexagonal.domain.Event;
import poc.hexagonal.domain.Tier;
import poc.hexagonal.error.BusinessError;
import poc.hexagonal.port.out.EventDataAccess;
import poc.hexagonal.port.out.TierDataAccess;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TierServiceImplTest {
    private static final Long TIER_ID = 1L;
    private static final Long EVENT_ID = 3L;
    @InjectMocks
    private TierServiceImpl tierService;
    @Mock
    private TierDataAccess tierDataAccess;
    @Mock
    private EventDataAccess eventDataAccess;
    @Mock
    private Tier tier;
    @Mock
    private Event event;

    @Test
    public void addEvent_shouldAddEventToTier_whenBothExistsForGivenIds(){
        //given
        when(tierDataAccess.findById(anyLong())).thenReturn(Optional.of(tier));
        when(eventDataAccess.findById(anyLong())).thenReturn(Optional.of(event));
        //when
        tierService.addEvent(TIER_ID, EVENT_ID);
        //then
        verify(tierDataAccess).findById(TIER_ID);
        verify(eventDataAccess).findById(EVENT_ID);
        verify(tier).add(event);
        verify(tierDataAccess).update(tier);

        verifyNoMoreInteractions(tierDataAccess);
        verifyNoMoreInteractions(eventDataAccess);
        verifyNoMoreInteractions(tier);
        verifyNoMoreInteractions(event);
    }

    @Test(expected = BusinessError.class)
    public void addEvent_shouldThrowAnException_whenCouldNotFindTierWithGivenId(){
        //given
        when(tierDataAccess.findById(anyLong())).thenReturn(Optional.empty());
        //when
        tierService.addEvent(TIER_ID, EVENT_ID);
    }

    @Test(expected = BusinessError.class)
    public void addEvent_shouldThrowAnException_whenCouldNotFindEventWithGivenId(){
        //given
        when(tierDataAccess.findById(anyLong())).thenReturn(Optional.of(tier));
        when(eventDataAccess.findById(anyLong())).thenReturn(Optional.empty());
        //when
        tierService.addEvent(TIER_ID, EVENT_ID);
    }
}

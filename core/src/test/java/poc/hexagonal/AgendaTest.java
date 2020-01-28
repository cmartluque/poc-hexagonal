

package poc.hexagonal;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import poc.hexagonal.error.BusinessError;

import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class AgendaTest {
    private static final Long TIER_ID = 1L;
    private static final Long AGENDA_ID = 1L;
    private static final Integer LENGTH_IN_MINUTES = 60;
    private static final Integer MIN_NUMBER_OF_TIERS = 0;
    private static final Integer ONE_TIER = 1;

    @Test(expected = BusinessError.class)
    public void addTier_shouldThrowAnException_whenMaxNumberOfTiersIsReached() {
        //given
        final Tier tier = buildTierForTesting();
        final AgendaDetails agendaDetails = new AgendaDetails(AGENDA_ID, MIN_NUMBER_OF_TIERS);
        final Agenda agenda = new Agenda(agendaDetails);
        //when
        agenda.add(tier);
    }

    @Test
    public void addTier_shouldAddGivenTier_whenMaxNumberOfTiersIsNotReached() {
        //given
        final Tier tier = buildTierForTesting();
        final AgendaDetails agendaDetails = new AgendaDetails(AGENDA_ID, ONE_TIER);
        final Agenda agenda = new Agenda(agendaDetails);
        //when
        agenda.add(tier);
        //then
        assertTrue(ONE_TIER == agenda.tiers().size());
        assertTrue(agenda.tiers().contains(tier));
    }

    @Test
    public void tiers_shouldReturnAnEmptyList_whenNoTiersWhereAdded() {
        //given
        final AgendaDetails agendaDetails = new AgendaDetails(AGENDA_ID, ONE_TIER);
        final Agenda agenda = new Agenda(agendaDetails);
        //when
        List<Tier> tiers = agenda.tiers();
        //then
        assertTrue(tiers.isEmpty());
    }

    @Test
    public void tiers_shouldReturnAllTiers_whenTiersWhereAdded() {
        //given
        final Tier tier = buildTierForTesting();
        final AgendaDetails agendaDetails = new AgendaDetails(AGENDA_ID, ONE_TIER);
        final Agenda agenda = new Agenda(agendaDetails);
        agenda.add(tier);
        //when
        List<Tier> tiers = agenda.tiers();
        //then
        assertTrue(ONE_TIER == tiers.size());
    }

    private Tier buildTierForTesting(){
        final TierDetails tierDetails = new TierDetails(TIER_ID, LENGTH_IN_MINUTES);
        return new Tier(tierDetails);
    }
}

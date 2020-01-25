package poc.hexagonal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class TierTest {
    private static Integer ZERO_HOURS = 0;
    private static Integer ONE_HOUR = 60;
    private static Integer TWO_HOURS = 120;
    private static Integer THREE_HOURS = 180;
    private static Integer FOUR_HOURS = 220;
    private static Integer SIZE_WITH_ONE_ELEMENT = 1;
    private static Integer SIZE_WITH_TWO_ELEMENT = 2;

    @Test(expected = RuntimeException.class)
    public void addConference_shouldThrowException_whenConferenceIsEmptyDoesNotFits() {
        //given
        final Conference conference = new Conference(THREE_HOURS);
        final Tier tier = new Tier(TWO_HOURS);
        //when
        tier.add(conference);
    }


    @Test(expected = RuntimeException.class)
    public void addConference_shouldThrowException_whenConferenceIsNotEmptyDoesNotFits() {
        //given
        final Conference firstConference = new Conference(ONE_HOUR);
        final Conference secondConference = new Conference(TWO_HOURS);
        final Tier tier = new Tier(TWO_HOURS);
        tier.add(firstConference);
        //when
        tier.add(secondConference);
    }

    @Test
    public void addConference_shouldAddGivenConference_whenTierIsEmptyAndConferenceFits() {
        //given
        final Conference conference = new Conference(ONE_HOUR);
        final Tier tier = new Tier(TWO_HOURS);
        //when
        tier.add(conference);
        //then
        assertTrue(SIZE_WITH_ONE_ELEMENT == tier.getConferences().size());
        assertEquals(conference, tier.getConferences().get(0));
    }

    @Test
    public void addConference_shouldAddGivenConference_whenTierIsNotEmptyAndConferenceFits() {
        //given
        final Conference firstConference = new Conference(ONE_HOUR);
        final Conference secondConference = new Conference(TWO_HOURS);
        final Tier tier = new Tier(FOUR_HOURS);
        tier.add(firstConference);
        //when
        tier.add(secondConference);
        //then
        assertTrue(SIZE_WITH_TWO_ELEMENT == tier.getConferences().size());
        assertTrue(tier.getConferences().contains(firstConference));
        assertTrue(tier.getConferences().contains(secondConference));
    }

    @Test
    public void remainingTime_shouldReturnTiersLenght_whenTierIsEmpty() {
        //given
        final Tier tier = new Tier(TWO_HOURS);
        //when
        final Integer tiersRemainingTime = tier.remainingTime();
        //then
        assertTrue(TWO_HOURS == tiersRemainingTime);
    }

    @Test
    public void remainingTime_shouldReturnTiersLenghtWithoutConferenceLenght_whenTierHasOneConference() {
        //given
        final Conference conference = new Conference(ONE_HOUR);
        final Tier tier = new Tier(THREE_HOURS);
        tier.add(conference);
        //when
        final Integer tiersRemainingTime = tier.remainingTime();
        //then
        assertTrue(TWO_HOURS == tiersRemainingTime);
    }

    @Test
    public void remainingTime_shouldReturnTiersLenghtWithoutConferenceLenght_whenTierHasMoreThanOneConference() {
        //given
        final Conference firstConference = new Conference(ONE_HOUR);
        final Conference secondConference = new Conference(TWO_HOURS);
        final Tier tier = new Tier(THREE_HOURS);
        tier.add(firstConference);
        tier.add(secondConference);
        //when
        final Integer tiersRemainingTime = tier.remainingTime();
        //then
        assertTrue(ZERO_HOURS == tiersRemainingTime);
    }
}

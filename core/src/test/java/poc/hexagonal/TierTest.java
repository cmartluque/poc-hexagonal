package poc.hexagonal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import poc.hexagonal.error.BusinessError;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class TierTest {
    private static Long TIER_ID = 1L;
    private static Integer ZERO_HOURS = 0;
    private static Integer ONE_HOUR = 60;
    private static Integer TWO_HOURS = 120;
    private static Integer THREE_HOURS = 180;
    private static Integer FOUR_HOURS = 220;
    private static Integer SIZE_WITH_ONE_ELEMENT = 1;
    private static Integer SIZE_WITH_TWO_ELEMENT = 2;

    @Test(expected = BusinessError.class)
    public void addConference_shouldThrowException_whenConferenceIsEmptyDoesNotFits() {
        //given
        final Event event = new Event(THREE_HOURS);
        final TierDetails tierDetails = new TierDetails(TIER_ID, TWO_HOURS);
        final Tier tier = new Tier(tierDetails);
        //when
        tier.add(event);
    }


    @Test(expected = BusinessError.class)
    public void addConference_shouldThrowException_whenConferenceIsNotEmptyDoesNotFits() {
        //given
        final Event firstEvent = new Event(ONE_HOUR);
        final Event secondEvent = new Event(TWO_HOURS);
        final TierDetails tierDetails = new TierDetails(TIER_ID, TWO_HOURS);
        final Tier tier = new Tier(tierDetails);
        tier.add(firstEvent);
        //when
        tier.add(secondEvent);
    }

    @Test
    public void addConference_shouldAddGivenConference_whenTierIsEmptyAndConferenceFits() {
        //given
        final Event event = new Event(ONE_HOUR);
        final TierDetails tierDetails = new TierDetails(TIER_ID, TWO_HOURS);
        final Tier tier = new Tier(tierDetails);
        //when
        tier.add(event);
        //then
        assertTrue(SIZE_WITH_ONE_ELEMENT == tier.events().size());
        assertEquals(event, tier.events().get(0));
    }

    @Test
    public void addConference_shouldAddGivenConference_whenTierIsNotEmptyAndConferenceFits() {
        //given
        final Event firstEvent = new Event(ONE_HOUR);
        final Event secondEvent = new Event(TWO_HOURS);
        final TierDetails tierDetails = new TierDetails(TIER_ID, FOUR_HOURS);
        final Tier tier = new Tier(tierDetails);
        tier.add(firstEvent);
        //when
        tier.add(secondEvent);
        //then
        assertTrue(SIZE_WITH_TWO_ELEMENT == tier.events().size());
        assertTrue(tier.events().contains(firstEvent));
        assertTrue(tier.events().contains(secondEvent));
    }

    @Test
    public void remainingTime_shouldReturnTiersLenght_whenTierIsEmpty() {
        //given
        final TierDetails tierDetails = new TierDetails(TIER_ID, TWO_HOURS);
        final Tier tier = new Tier(tierDetails);
        //when
        final Integer tiersRemainingTime = tier.remainingTime();
        //then
        assertTrue(TWO_HOURS == tiersRemainingTime);
    }

    @Test
    public void remainingTime_shouldReturnTiersLenghtWithoutConferenceLenght_whenTierHasOneConference() {
        //given
        final Event event = new Event(ONE_HOUR);
        final TierDetails tierDetails = new TierDetails(TIER_ID, THREE_HOURS);
        final Tier tier = new Tier(tierDetails);
        tier.add(event);
        //when
        final Integer tiersRemainingTime = tier.remainingTime();
        //then
        assertTrue(TWO_HOURS == tiersRemainingTime);
    }

    @Test
    public void remainingTime_shouldReturnTiersLenghtWithoutConferenceLenght_whenTierHasMoreThanOneConference() {
        //given
        final Event firstEvent = new Event(ONE_HOUR);
        final Event secondEvent = new Event(TWO_HOURS);
        final TierDetails tierDetails = new TierDetails(TIER_ID, THREE_HOURS);
        final Tier tier = new Tier(tierDetails);
        tier.add(firstEvent);
        tier.add(secondEvent);
        //when
        final Integer tiersRemainingTime = tier.remainingTime();
        //then
        assertTrue(ZERO_HOURS == tiersRemainingTime);
    }
}

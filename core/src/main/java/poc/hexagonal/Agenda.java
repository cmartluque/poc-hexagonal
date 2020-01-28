
package poc.hexagonal;


import poc.hexagonal.error.BusinessError;

import java.util.ArrayList;
import java.util.List;

public class Agenda {
    private AgendaDetails agendaDetails;
    private List<Tier> tiers;

    public Agenda(AgendaDetails agendaDetails) {
        this.agendaDetails = agendaDetails;
        this.tiers = new ArrayList<>();
    }

    public void add(Tier tier) {
        if (isMaxNumberOfTiersReached()) {
            throw new BusinessError();
        }
        tiers.add(tier);
    }

    private boolean isMaxNumberOfTiersReached() {
        return agendaDetails.getMaxNumberOfTiers() == tiers.size();
    }

    public List<Tier> tiers() {
        return new ArrayList<>(tiers);
    }
}


package poc.hexagonal;


import poc.hexagonal.error.BusinessError;

import java.util.ArrayList;
import java.util.List;

public class Agenda {
    private Integer maxNumberOfTiers;
    private List<Tier> tiers;

    public Agenda(Integer maxNumberOfTiers) {
        this.maxNumberOfTiers = maxNumberOfTiers;
        this.tiers = new ArrayList<>();
    }

    public void add(Tier tier) {
        if (isMaxNumberOfTiersReached()) {
            throw new BusinessError();
        }
        tiers.add(tier);
    }

    private boolean isMaxNumberOfTiersReached() {
        return maxNumberOfTiers == tiers.size();
    }

    public List<Tier> tiers() {
        return new ArrayList<>(tiers);
    }
}

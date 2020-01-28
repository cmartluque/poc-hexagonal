package poc.hexagonal.port.in;

import poc.hexagonal.Agenda;
import poc.hexagonal.Tier;

public interface AgendaService {
    void create(Agenda agenda);
    void addTier(Agenda agenda, Tier tier);
}

package poc.hexagonal.port.in;

import poc.hexagonal.Agenda;
import poc.hexagonal.AgendaDetails;
import poc.hexagonal.Tier;

public interface AgendaService {
    void create(AgendaDetails agendaDetails);
    void addTier(Long agendaId, Tier tier);
}

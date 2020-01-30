package poc.hexagonal.domain.factory;

import poc.hexagonal.domain.Agenda;
import poc.hexagonal.domain.AgendaDetails;

public interface Agendas {
    Agenda with(AgendaDetails agendaDetails);
}

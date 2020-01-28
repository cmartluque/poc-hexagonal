package poc.hexagonal.port.out;

import poc.hexagonal.Agenda;

public interface AgendaRepository {
    Long create(Agenda agenda);
    void update(Agenda agenda);
    void delete(Agenda agenda);
}

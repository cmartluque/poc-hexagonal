package poc.hexagonal.port.out;

import poc.hexagonal.domain.Agenda;

import java.util.Optional;

public interface AgendaRepository {
    Optional<Agenda> findById(Long id);
    void update(Agenda agenda);
}

package br.com.esplanada.repository;

import br.com.esplanada.domain.Turma;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Turma entity.
 */
@SuppressWarnings("unused")
public interface TurmaRepository extends JpaRepository<Turma,Long> {

}

package br.com.esplanada.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.esplanada.domain.TurmaAluno;

/**
 * Spring Data JPA repository for the Turma entity.
 */
@SuppressWarnings("unused")
public interface TurmaAlunoRepository extends JpaRepository<TurmaAluno,Long> {

}

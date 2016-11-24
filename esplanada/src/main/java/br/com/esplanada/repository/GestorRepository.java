package br.com.esplanada.repository;

import br.com.esplanada.domain.Gestor;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Gestor entity.
 */
@SuppressWarnings("unused")
public interface GestorRepository extends JpaRepository<Gestor,Long> {

}

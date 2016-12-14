package br.com.esplanada.repository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.esplanada.domain.User;

/**
 * Spring Data JPA repository for the User entity.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findOneByActivationKey(String activationKey);

    List<User> findAllByActivatedIsFalseAndCreatedDateBefore(ZonedDateTime dateTime);

    Optional<User> findOneByResetKey(String resetKey);

    Optional<User> findOneByEmail(String email);

    Optional<User> findOneByLogin(String login);

    @Query(value = "select distinct user from User user left join fetch user.authorities",
        countQuery = "select count(user) from User user")
    Page<User> findAllWithAuthorities(Pageable pageable);
    
    @Query(value = "select * from colegioesplanada.usuario where turma_id = ?1", nativeQuery = true)
    List<User> getUsuarioPorTurma(Long idTurma);
    
    @Query(value = "select user from User user inner join user.turma turma where turma.disciplina = (select usuario.disciplina from User usuario where usuario.login = ?1)")
    List<User> getAlunosPorTurma(String login);

    @Override
    void delete(User t);

}

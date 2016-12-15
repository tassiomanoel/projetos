package br.com.esplanada.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import br.com.esplanada.domain.Turma;
import br.com.esplanada.domain.User;
import br.com.esplanada.repository.TurmaRepository;
import br.com.esplanada.repository.UserRepository;
import br.com.esplanada.web.rest.util.HeaderUtil;

/**
 * REST controller for managing Turma.
 */
@RestController
@RequestMapping("/api")
public class TurmaResource {

    private final Logger log = LoggerFactory.getLogger(TurmaResource.class);
        
    @Inject
    private TurmaRepository turmaRepository;
    
    @Inject
    private UserRepository userRepository;

    /**
     * POST  /turmas : Create a new turma.
     *
     * @param turma the turma to create
     * @return the ResponseEntity with status 201 (Created) and with body the new turma, or with status 400 (Bad Request) if the turma has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/turmas")
    @Timed
    public ResponseEntity<Turma> createTurma(@RequestBody Turma turma) throws URISyntaxException {
        log.debug("REST request to save Turma : {}", turma);
        if (turma.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("turma", "idexists", "A new turma cannot already have an ID")).body(null);
        }
        Turma result = turmaRepository.save(turma);
        salvarAluno(turma, false);
        return ResponseEntity.created(new URI("/api/turmas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("turma", result.getId().toString()))
            .body(result);
    }

    private void salvarAluno(Turma turma, boolean isUpdate) {
    	List<User> users = userRepository.getUsuarioPorTurma(turma.getId());
    	
    	if(users.size() > turma.getUsuarios().size()){
    		for(User usuario : users){
    			if(!turma.getUsuarios().contains(usuario)){
    				usuario.setTurma(null);
    				userRepository.save(usuario);
    			}
    		}
    	} else {
    		for(User usuario : turma.getUsuarios()){
    			User user = userRepository.findOne(usuario.getId());
    			user.setTurma(turma);
    			userRepository.save(user);
    		}
    	}
	}

	/**
     * PUT  /turmas : Updates an existing turma.
     *
     * @param turma the turma to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated turma,
     * or with status 400 (Bad Request) if the turma is not valid,
     * or with status 500 (Internal Server Error) if the turma couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/turmas")
    @Timed
    public ResponseEntity<Turma> updateTurma(@RequestBody Turma turma) throws URISyntaxException {
        log.debug("REST request to update Turma : {}", turma);
        if (turma.getId() == null) {
            return createTurma(turma);
        }
        Turma result = turmaRepository.save(turma);
        salvarAluno(turma, true);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("turma", turma.getId().toString()))
            .body(result);
    }

    /**
     * GET  /turmas : get all the turmas.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of turmas in body
     */
    @GetMapping("/turmas")
    @Timed
    public List<Turma> getAllTurmas() {
        log.debug("REST request to get all Turmas");
        List<Turma> turmas = turmaRepository.findAll();
        return turmas;
    }

    /**
     * GET  /turmas/:id : get the "id" turma.
     *
     * @param id the id of the turma to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the turma, or with status 404 (Not Found)
     */
    @GetMapping("/turmas/{id}")
    @Timed
    public ResponseEntity<Turma> getTurma(@PathVariable Long id) {
        log.debug("REST request to get Turma : {}", id);
        Turma turma = turmaRepository.findOne(id);
        return Optional.ofNullable(turma)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /turmas/:id : delete the "id" turma.
     *
     * @param id the id of the turma to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/turmas/{id}")
    @Timed
    public ResponseEntity<Void> deleteTurma(@PathVariable Long id) {
        log.debug("REST request to delete Turma : {}", id);
        List<User> users = userRepository.getUsuarioPorTurma(id);
        for(User usuario : users){
        	usuario.setTurma(null);
        	usuario.setNota1(null);
        	usuario.setNota2(null);
        	usuario.setNota3(null);
        	usuario.setNota4(null);
        	usuario.setFaltas(null);
        	usuario.setSituacao(null);
        	userRepository.save(usuario);
        }
        turmaRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("turma", id.toString())).build();
    }

}

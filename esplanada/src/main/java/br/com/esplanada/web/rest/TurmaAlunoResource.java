package br.com.esplanada.web.rest;

import java.net.URI;
import java.net.URISyntaxException;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import br.com.esplanada.domain.TurmaAluno;
import br.com.esplanada.repository.TurmaAlunoRepository;
import br.com.esplanada.web.rest.util.HeaderUtil;

/**
 */
@RestController
@RequestMapping("/api")
public class TurmaAlunoResource {

    private final Logger log = LoggerFactory.getLogger(TurmaAlunoResource.class);
        
    @Inject
    private TurmaAlunoRepository turmaAlunoRepository;

    @PostMapping("/turmaAluno")
    @Timed
    public ResponseEntity<TurmaAluno> createTurma(@RequestBody TurmaAluno turma) throws URISyntaxException {
        log.debug("REST request to save Turma : {}", turma);
        if (turma.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("turma", "idexists", "A new turma cannot already have an ID")).body(null);
        }
        TurmaAluno result = turmaAlunoRepository.save(turma);
        return ResponseEntity.created(new URI("/api/turmas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("turma", result.getId().toString()))
            .body(result);
    }

}

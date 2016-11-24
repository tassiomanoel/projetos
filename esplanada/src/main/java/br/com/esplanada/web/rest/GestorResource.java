package br.com.esplanada.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.esplanada.domain.Gestor;

import br.com.esplanada.repository.GestorRepository;
import br.com.esplanada.web.rest.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Gestor.
 */
@RestController
@RequestMapping("/api")
public class GestorResource {

    private final Logger log = LoggerFactory.getLogger(GestorResource.class);
        
    @Inject
    private GestorRepository gestorRepository;

    /**
     * POST  /gestors : Create a new gestor.
     *
     * @param gestor the gestor to create
     * @return the ResponseEntity with status 201 (Created) and with body the new gestor, or with status 400 (Bad Request) if the gestor has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/gestors")
    @Timed
    public ResponseEntity<Gestor> createGestor(@RequestBody Gestor gestor) throws URISyntaxException {
        log.debug("REST request to save Gestor : {}", gestor);
        if (gestor.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("gestor", "idexists", "A new gestor cannot already have an ID")).body(null);
        }
        Gestor result = gestorRepository.save(gestor);
        return ResponseEntity.created(new URI("/api/gestors/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("gestor", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /gestors : Updates an existing gestor.
     *
     * @param gestor the gestor to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated gestor,
     * or with status 400 (Bad Request) if the gestor is not valid,
     * or with status 500 (Internal Server Error) if the gestor couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/gestors")
    @Timed
    public ResponseEntity<Gestor> updateGestor(@RequestBody Gestor gestor) throws URISyntaxException {
        log.debug("REST request to update Gestor : {}", gestor);
        if (gestor.getId() == null) {
            return createGestor(gestor);
        }
        Gestor result = gestorRepository.save(gestor);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("gestor", gestor.getId().toString()))
            .body(result);
    }

    /**
     * GET  /gestors : get all the gestors.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of gestors in body
     */
    @GetMapping("/gestors")
    @Timed
    public List<Gestor> getAllGestors() {
        log.debug("REST request to get all Gestors");
        List<Gestor> gestors = gestorRepository.findAll();
        return gestors;
    }

    /**
     * GET  /gestors/:id : get the "id" gestor.
     *
     * @param id the id of the gestor to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the gestor, or with status 404 (Not Found)
     */
    @GetMapping("/gestors/{id}")
    @Timed
    public ResponseEntity<Gestor> getGestor(@PathVariable Long id) {
        log.debug("REST request to get Gestor : {}", id);
        Gestor gestor = gestorRepository.findOne(id);
        return Optional.ofNullable(gestor)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /gestors/:id : delete the "id" gestor.
     *
     * @param id the id of the gestor to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/gestors/{id}")
    @Timed
    public ResponseEntity<Void> deleteGestor(@PathVariable Long id) {
        log.debug("REST request to delete Gestor : {}", id);
        gestorRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("gestor", id.toString())).build();
    }

}

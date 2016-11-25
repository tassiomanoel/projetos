package br.com.esplanada.web.rest;

import br.com.esplanada.ColegioEsplanadaApp;

import br.com.esplanada.domain.Gestor;
import br.com.esplanada.repository.GestorRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.hasItem;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the GestorResource REST controller.
 *
 * @see GestorResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ColegioEsplanadaApp.class)
public class GestorResourceIntTest {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_IDADE = new BigDecimal(1);
    private static final BigDecimal UPDATED_IDADE = new BigDecimal(2);

    @Inject
    private GestorRepository gestorRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Inject
    private EntityManager em;

    private MockMvc restGestorMockMvc;

    private Gestor gestor;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        GestorResource gestorResource = new GestorResource();
        ReflectionTestUtils.setField(gestorResource, "gestorRepository", gestorRepository);
        this.restGestorMockMvc = MockMvcBuilders.standaloneSetup(gestorResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Gestor createEntity(EntityManager em) {
        Gestor gestor = new Gestor();
//                .nome(DEFAULT_NOME)
//                .email(DEFAULT_EMAIL)
//                .idade(DEFAULT_IDADE);
        return gestor;
    }

    @Before
    public void initTest() {
        gestor = createEntity(em);
    }

    @Test
    @Transactional
    public void createGestor() throws Exception {
        int databaseSizeBeforeCreate = gestorRepository.findAll().size();

        // Create the Gestor

        restGestorMockMvc.perform(post("/api/gestors")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(gestor)))
                .andExpect(status().isCreated());

        // Validate the Gestor in the database
        List<Gestor> gestors = gestorRepository.findAll();
        assertThat(gestors).hasSize(databaseSizeBeforeCreate + 1);
        Gestor testGestor = gestors.get(gestors.size() - 1);
        assertThat(testGestor.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testGestor.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testGestor.getCadastro()).isEqualTo(DEFAULT_IDADE);
    }

    @Test
    @Transactional
    public void getAllGestors() throws Exception {
        // Initialize the database
        gestorRepository.saveAndFlush(gestor);

        // Get all the gestors
        restGestorMockMvc.perform(get("/api/gestors?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.[*].id").value(hasItem(gestor.getId().intValue())))
                .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())))
                .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
                .andExpect(jsonPath("$.[*].idade").value(hasItem(DEFAULT_IDADE.intValue())));
    }

    @Test
    @Transactional
    public void getGestor() throws Exception {
        // Initialize the database
        gestorRepository.saveAndFlush(gestor);

        // Get the gestor
        restGestorMockMvc.perform(get("/api/gestors/{id}", gestor.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(gestor.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
            .andExpect(jsonPath("$.idade").value(DEFAULT_IDADE.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingGestor() throws Exception {
        // Get the gestor
        restGestorMockMvc.perform(get("/api/gestors/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGestor() throws Exception {
        // Initialize the database
        gestorRepository.saveAndFlush(gestor);
        int databaseSizeBeforeUpdate = gestorRepository.findAll().size();

        // Update the gestor
        Gestor updatedGestor = gestorRepository.findOne(gestor.getId());
//        updatedGestor
//                .nome(UPDATED_NOME)
//                .email(UPDATED_EMAIL)
//                .idade(UPDATED_IDADE);

        restGestorMockMvc.perform(put("/api/gestors")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(updatedGestor)))
                .andExpect(status().isOk());

        // Validate the Gestor in the database
        List<Gestor> gestors = gestorRepository.findAll();
        assertThat(gestors).hasSize(databaseSizeBeforeUpdate);
        Gestor testGestor = gestors.get(gestors.size() - 1);
        assertThat(testGestor.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testGestor.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testGestor.getCadastro()).isEqualTo(UPDATED_IDADE);
    }

    @Test
    @Transactional
    public void deleteGestor() throws Exception {
        // Initialize the database
        gestorRepository.saveAndFlush(gestor);
        int databaseSizeBeforeDelete = gestorRepository.findAll().size();

        // Get the gestor
        restGestorMockMvc.perform(delete("/api/gestors/{id}", gestor.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Gestor> gestors = gestorRepository.findAll();
        assertThat(gestors).hasSize(databaseSizeBeforeDelete - 1);
    }
}

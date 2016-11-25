package br.com.esplanada.web.rest;

import br.com.esplanada.ColegioEsplanadaApp;

import br.com.esplanada.domain.Turma;
import br.com.esplanada.repository.TurmaRepository;

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
 * Test class for the TurmaResource REST controller.
 *
 * @see TurmaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ColegioEsplanadaApp.class)
public class TurmaResourceIntTest {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_DATA_CADASTRO = new BigDecimal(1);
    private static final BigDecimal UPDATED_DATA_CADASTRO = new BigDecimal(2);

    private static final String DEFAULT_NOTA = "AAAAAAAAAA";
    private static final String UPDATED_NOTA = "BBBBBBBBBB";

    private static final String DEFAULT_FREQUENCIA = "AAAAAAAAAA";
    private static final String UPDATED_FREQUENCIA = "BBBBBBBBBB";

    private static final String DEFAULT_ATIVIDADES = "AAAAAAAAAA";
    private static final String UPDATED_ATIVIDADES = "BBBBBBBBBB";

    @Inject
    private TurmaRepository turmaRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Inject
    private EntityManager em;

    private MockMvc restTurmaMockMvc;

    private Turma turma;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        TurmaResource turmaResource = new TurmaResource();
        ReflectionTestUtils.setField(turmaResource, "turmaRepository", turmaRepository);
        this.restTurmaMockMvc = MockMvcBuilders.standaloneSetup(turmaResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Turma createEntity(EntityManager em) {
        Turma turma = new Turma();
//                .nome(DEFAULT_NOME)
//                .dataCadastro(DEFAULT_DATA_CADASTRO)
//                .nota(DEFAULT_NOTA)
//                .frequencia(DEFAULT_FREQUENCIA)
//                .atividades(DEFAULT_ATIVIDADES);
        return turma;
    }

    @Before
    public void initTest() {
        turma = createEntity(em);
    }

    @Test
    @Transactional
    public void createTurma() throws Exception {
        int databaseSizeBeforeCreate = turmaRepository.findAll().size();

        // Create the Turma

        restTurmaMockMvc.perform(post("/api/turmas")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(turma)))
                .andExpect(status().isCreated());

        // Validate the Turma in the database
        List<Turma> turmas = turmaRepository.findAll();
        assertThat(turmas).hasSize(databaseSizeBeforeCreate + 1);
        Turma testTurma = turmas.get(turmas.size() - 1);
        assertThat(testTurma.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testTurma.getCadastro()).isEqualTo(DEFAULT_DATA_CADASTRO);
        assertThat(testTurma.getNotaTurma()).isEqualTo(DEFAULT_NOTA);
        assertThat(testTurma.getFrequenciaTurma()).isEqualTo(DEFAULT_FREQUENCIA);
    }

    @Test
    @Transactional
    public void getAllTurmas() throws Exception {
        // Initialize the database
        turmaRepository.saveAndFlush(turma);

        // Get all the turmas
        restTurmaMockMvc.perform(get("/api/turmas?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.[*].id").value(hasItem(turma.getId().intValue())))
                .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())))
                .andExpect(jsonPath("$.[*].dataCadastro").value(hasItem(DEFAULT_DATA_CADASTRO.intValue())))
                .andExpect(jsonPath("$.[*].nota").value(hasItem(DEFAULT_NOTA.toString())))
                .andExpect(jsonPath("$.[*].frequencia").value(hasItem(DEFAULT_FREQUENCIA.toString())))
                .andExpect(jsonPath("$.[*].atividades").value(hasItem(DEFAULT_ATIVIDADES.toString())));
    }

    @Test
    @Transactional
    public void getTurma() throws Exception {
        // Initialize the database
        turmaRepository.saveAndFlush(turma);

        // Get the turma
        restTurmaMockMvc.perform(get("/api/turmas/{id}", turma.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(turma.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()))
            .andExpect(jsonPath("$.dataCadastro").value(DEFAULT_DATA_CADASTRO.intValue()))
            .andExpect(jsonPath("$.nota").value(DEFAULT_NOTA.toString()))
            .andExpect(jsonPath("$.frequencia").value(DEFAULT_FREQUENCIA.toString()))
            .andExpect(jsonPath("$.atividades").value(DEFAULT_ATIVIDADES.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTurma() throws Exception {
        // Get the turma
        restTurmaMockMvc.perform(get("/api/turmas/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTurma() throws Exception {
        // Initialize the database
        turmaRepository.saveAndFlush(turma);
        int databaseSizeBeforeUpdate = turmaRepository.findAll().size();

        // Update the turma
        Turma updatedTurma = turmaRepository.findOne(turma.getId());
//        updatedTurma
//                .nome(UPDATED_NOME)
//                .dataCadastro(UPDATED_DATA_CADASTRO)
//                .nota(UPDATED_NOTA)
//                .frequencia(UPDATED_FREQUENCIA)
//                .atividades(UPDATED_ATIVIDADES);

        restTurmaMockMvc.perform(put("/api/turmas")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(updatedTurma)))
                .andExpect(status().isOk());

        // Validate the Turma in the database
        List<Turma> turmas = turmaRepository.findAll();
        assertThat(turmas).hasSize(databaseSizeBeforeUpdate);
        Turma testTurma = turmas.get(turmas.size() - 1);
        assertThat(testTurma.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testTurma.getCadastro()).isEqualTo(UPDATED_DATA_CADASTRO);
        assertThat(testTurma.getNotaTurma()).isEqualTo(UPDATED_NOTA);
        assertThat(testTurma.getFrequenciaTurma()).isEqualTo(UPDATED_FREQUENCIA);
    }

    @Test
    @Transactional
    public void deleteTurma() throws Exception {
        // Initialize the database
        turmaRepository.saveAndFlush(turma);
        int databaseSizeBeforeDelete = turmaRepository.findAll().size();

        // Get the turma
        restTurmaMockMvc.perform(delete("/api/turmas/{id}", turma.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Turma> turmas = turmaRepository.findAll();
        assertThat(turmas).hasSize(databaseSizeBeforeDelete - 1);
    }
}

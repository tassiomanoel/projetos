package br.com.esplanada.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Aluno.
 */
@Entity
@Table(name = "aluno")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Aluno implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "frequencia")
    private String frequencia;

    @Column(name = "nota")
    private String nota;

    @Column(name = "atividades")
    private String atividades;

    @Column(name = "data_cadastro")
    private String dataCadastro;

    @OneToMany(mappedBy = "gestor")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Turma> alunoTurmas = new HashSet<>();

    @ManyToOne
    private Gestor alunoGestor;

    @OneToMany(mappedBy = "professor")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Professor> alunoProfessors = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public Aluno nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFrequencia() {
        return frequencia;
    }

    public Aluno frequencia(String frequencia) {
        this.frequencia = frequencia;
        return this;
    }

    public void setFrequencia(String frequencia) {
        this.frequencia = frequencia;
    }

    public String getNota() {
        return nota;
    }

    public Aluno nota(String nota) {
        this.nota = nota;
        return this;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public String getAtividades() {
        return atividades;
    }

    public Aluno atividades(String atividades) {
        this.atividades = atividades;
        return this;
    }

    public void setAtividades(String atividades) {
        this.atividades = atividades;
    }

    public String getDataCadastro() {
        return dataCadastro;
    }

    public Aluno dataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
        return this;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Set<Turma> getAlunoTurmas() {
        return alunoTurmas;
    }

    public Aluno alunoTurmas(Set<Turma> turmas) {
        this.alunoTurmas = turmas;
        return this;
    }

    public void setAlunoTurmas(Set<Turma> turmas) {
        this.alunoTurmas = turmas;
    }

    public Gestor getAlunoGestor() {
        return alunoGestor;
    }

    public Aluno alunoGestor(Gestor gestor) {
        this.alunoGestor = gestor;
        return this;
    }

    public void setAlunoGestor(Gestor gestor) {
        this.alunoGestor = gestor;
    }

    public Set<Professor> getAlunoProfessors() {
        return alunoProfessors;
    }

    public Aluno alunoProfessors(Set<Professor> professors) {
        this.alunoProfessors = professors;
        return this;
    }

    public void setAlunoProfessors(Set<Professor> professors) {
        this.alunoProfessors = professors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Aluno aluno = (Aluno) o;
        if(aluno.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, aluno.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Aluno{" +
            "id=" + id +
            ", nome='" + nome + "'" +
            ", frequencia='" + frequencia + "'" +
            ", nota='" + nota + "'" +
            ", atividades='" + atividades + "'" +
            ", dataCadastro='" + dataCadastro + "'" +
            '}';
    }
}

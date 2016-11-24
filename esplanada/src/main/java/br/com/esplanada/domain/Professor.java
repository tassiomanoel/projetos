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
 * A Professor.
 */
@Entity
@Table(name = "professor")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Professor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "anotacao")
    private String anotacao;

    @Column(name = "atividade")
    private String atividade;

    @Column(name = "upload")
    private String upload;

    @ManyToOne
    private Gestor professorGestor;

    @ManyToOne
    private Turma professorTurma;

    @OneToMany(mappedBy = "aluno")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Aluno> professorAlunos = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public Professor nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAnotacao() {
        return anotacao;
    }

    public Professor anotacao(String anotacao) {
        this.anotacao = anotacao;
        return this;
    }

    public void setAnotacao(String anotacao) {
        this.anotacao = anotacao;
    }

    public String getAtividade() {
        return atividade;
    }

    public Professor atividade(String atividade) {
        this.atividade = atividade;
        return this;
    }

    public void setAtividade(String atividade) {
        this.atividade = atividade;
    }

    public String getUpload() {
        return upload;
    }

    public Professor upload(String upload) {
        this.upload = upload;
        return this;
    }

    public void setUpload(String upload) {
        this.upload = upload;
    }

    public Gestor getProfessorGestor() {
        return professorGestor;
    }

    public Professor professorGestor(Gestor gestor) {
        this.professorGestor = gestor;
        return this;
    }

    public void setProfessorGestor(Gestor gestor) {
        this.professorGestor = gestor;
    }

    public Turma getProfessorTurma() {
        return professorTurma;
    }

    public Professor professorTurma(Turma turma) {
        this.professorTurma = turma;
        return this;
    }

    public void setProfessorTurma(Turma turma) {
        this.professorTurma = turma;
    }

    public Set<Aluno> getProfessorAlunos() {
        return professorAlunos;
    }

    public Professor professorAlunos(Set<Aluno> alunos) {
        this.professorAlunos = alunos;
        return this;
    }

    public void setProfessorAlunos(Set<Aluno> alunos) {
        this.professorAlunos = alunos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Professor professor = (Professor) o;
        if(professor.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, professor.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Professor{" +
            "id=" + id +
            ", nome='" + nome + "'" +
            ", anotacao='" + anotacao + "'" +
            ", atividade='" + atividade + "'" +
            ", upload='" + upload + "'" +
            '}';
    }
}

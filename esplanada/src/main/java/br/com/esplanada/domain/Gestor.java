package br.com.esplanada.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Gestor.
 */
@Entity
@Table(name = "gestor")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Gestor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email")
    private String email;

    @Column(name = "idade", precision=10, scale=2)
    private BigDecimal idade;

    @OneToMany(mappedBy = "gestorTurma")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Turma> turmas = new HashSet<>();

    @OneToMany(mappedBy = "gestorProfessor")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Professor> professors = new HashSet<>();

    @OneToMany(mappedBy = "gestorAluno")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Aluno> alunos = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public Gestor nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public Gestor email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getIdade() {
        return idade;
    }

    public Gestor idade(BigDecimal idade) {
        this.idade = idade;
        return this;
    }

    public void setIdade(BigDecimal idade) {
        this.idade = idade;
    }

    public Set<Turma> getTurmas() {
        return turmas;
    }

    public Gestor turmas(Set<Turma> turmas) {
        this.turmas = turmas;
        return this;
    }

    public void setTurmas(Set<Turma> turmas) {
        this.turmas = turmas;
    }

    public Set<Professor> getProfessors() {
        return professors;
    }

    public Gestor professors(Set<Professor> professors) {
        this.professors = professors;
        return this;
    }

    public void setProfessors(Set<Professor> professors) {
        this.professors = professors;
    }

    public Set<Aluno> getAlunos() {
        return alunos;
    }

    public Gestor alunos(Set<Aluno> alunos) {
        this.alunos = alunos;
        return this;
    }

    public void setAlunos(Set<Aluno> alunos) {
        this.alunos = alunos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Gestor gestor = (Gestor) o;
        if(gestor.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, gestor.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Gestor{" +
            "id=" + id +
            ", nome='" + nome + "'" +
            ", email='" + email + "'" +
            ", idade='" + idade + "'" +
            '}';
    }
}

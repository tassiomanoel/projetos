package br.com.esplanada.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A Turma.
 */
@Entity
@Table(name = "turma")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Turma implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "data_cadastro", precision=10, scale=2)
    private BigDecimal dataCadastro;

    @Column(name = "nota")
    private String nota;

    @Column(name = "frequencia")
    private String frequencia;

    @Column(name = "atividades")
    private String atividades;

    @ManyToOne
    private Gestor gestor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public Turma nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getDataCadastro() {
        return dataCadastro;
    }

    public Turma dataCadastro(BigDecimal dataCadastro) {
        this.dataCadastro = dataCadastro;
        return this;
    }

    public void setDataCadastro(BigDecimal dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getNota() {
        return nota;
    }

    public Turma nota(String nota) {
        this.nota = nota;
        return this;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public String getFrequencia() {
        return frequencia;
    }

    public Turma frequencia(String frequencia) {
        this.frequencia = frequencia;
        return this;
    }

    public void setFrequencia(String frequencia) {
        this.frequencia = frequencia;
    }

    public String getAtividades() {
        return atividades;
    }

    public Turma atividades(String atividades) {
        this.atividades = atividades;
        return this;
    }

    public void setAtividades(String atividades) {
        this.atividades = atividades;
    }

    public Gestor getTurma() {
        return gestor;
    }

    public Turma turma(Gestor gestor) {
        this.gestor = gestor;
        return this;
    }

    public void setTurma(Gestor gestor) {
        this.gestor = gestor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Turma turma = (Turma) o;
        if(turma.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, turma.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Turma{" +
            "id=" + id +
            ", nome='" + nome + "'" +
            ", dataCadastro='" + dataCadastro + "'" +
            ", nota='" + nota + "'" +
            ", frequencia='" + frequencia + "'" +
            ", atividades='" + atividades + "'" +
            '}';
    }
}

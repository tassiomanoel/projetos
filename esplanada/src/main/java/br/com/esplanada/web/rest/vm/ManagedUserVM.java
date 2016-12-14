package br.com.esplanada.web.rest.vm;

import java.time.ZonedDateTime;
import java.util.Set;

import javax.validation.constraints.Size;

import br.com.esplanada.domain.Turma;
import br.com.esplanada.domain.User;
import br.com.esplanada.service.dto.UserDTO;

/**
 * View Model extending the UserDTO, which is meant to be used in the user management UI.
 */
public class ManagedUserVM extends UserDTO {

    public static final int PASSWORD_MIN_LENGTH = 4;
    public static final int PASSWORD_MAX_LENGTH = 100;

    private Long id;

    private String createdBy;

    private ZonedDateTime createdDate;

    private String lastModifiedBy;

    private ZonedDateTime lastModifiedDate;

    @Size(min = PASSWORD_MIN_LENGTH, max = PASSWORD_MAX_LENGTH)
    private String password;
    
    private Turma turma;
    
    private Integer nota1;
    
    private Integer nota2;
    
    private Integer nota3;
    
    private Integer nota4;
    
    private Integer faltas;
    
    private String anotacao;
    
    private String disciplina;

    private String situacao;

    public ManagedUserVM() {
    }

    public ManagedUserVM(User user) {
        super(user);
        this.id = user.getId();
        this.createdBy = user.getCreatedBy();
        this.createdDate = user.getCreatedDate();
        this.lastModifiedBy = user.getLastModifiedBy();
        this.lastModifiedDate = user.getLastModifiedDate();
        this.turma = user.getTurma();
        this.password = null;
        this.nota1 = user.getNota1();
        this.nota2 = user.getNota2();
        this.nota3 = user.getNota3();
        this.nota4 = user.getNota4();
        this.faltas = user.getFaltas();
        this.anotacao = user.getAnotacao();
        this.disciplina = user.getDisciplina();
        this.situacao = user.getSituacao();
    }

    public ManagedUserVM(Long id, String login, String password, String firstName, String lastName,
                         String email, boolean activated, String langKey, Set<String> authorities,
                         String createdBy, ZonedDateTime createdDate, String lastModifiedBy, ZonedDateTime lastModifiedDate, Turma turma,
                         Integer nota1, Integer nota2, Integer nota3, Integer nota4, Integer faltas, String anotacao, String disciplina, String situacao) {
        super(login, firstName, lastName, email, activated, langKey, authorities);
        this.id = id;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.lastModifiedBy = lastModifiedBy;
        this.lastModifiedDate = lastModifiedDate;
        this.password = password;
        this.turma = turma;
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.nota3 = nota3;
        this.nota4 = nota4;
        this.faltas = faltas;
        this.anotacao = anotacao;
        this.disciplina = disciplina;
        this.situacao = situacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public ZonedDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(ZonedDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public ZonedDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(ZonedDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getPassword() {
        return password;
    }
    
    public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}
	
	public Integer getNota1() {
		return nota1;
	}

	public void setNota1(Integer nota1) {
		this.nota1 = nota1;
	}

	public Integer getNota2() {
		return nota2;
	}

	public void setNota2(Integer nota2) {
		this.nota2 = nota2;
	}

	public Integer getNota3() {
		return nota3;
	}

	public void setNota3(Integer nota3) {
		this.nota3 = nota3;
	}

	public Integer getNota4() {
		return nota4;
	}

	public void setNota4(Integer nota4) {
		this.nota4 = nota4;
	}

	public Integer getFaltas() {
		return faltas;
	}

	public void setFaltas(Integer faltas) {
		this.faltas = faltas;
	}

	public String getAnotacao() {
		return anotacao;
	}

	public void setAnotacao(String anotacao) {
		this.anotacao = anotacao;
	}

	public String getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	@Override
    public String toString() {
        return "ManagedUserVM{" +
            "id=" + id +
            ", createdBy=" + createdBy +
            ", createdDate=" + createdDate +
            ", lastModifiedBy='" + lastModifiedBy + '\'' +
            ", lastModifiedDate=" + lastModifiedDate +
            "} " + super.toString();
    }
}

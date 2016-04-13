package br.com.belponto.dto;

public class UsuarioDTO {
	
	private String usuario;
	
	private String senha;

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public UsuarioDTO(String usuario, String senha) {
		super();
		this.usuario = usuario;
		this.senha = senha;
	}
}

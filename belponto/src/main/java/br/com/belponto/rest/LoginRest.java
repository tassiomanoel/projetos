package br.com.belponto.rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import br.com.belponto.dto.UserDTO;

@Path("autenticar")
public class LoginRest {
	
	@POST
	@Path("login")	
	@Produces(MediaType.APPLICATION_JSON)
	public String logar(@Context HttpHeaders httpHeaders, UserDTO usuario){
		return "cliente salvo com sucesso!";
	}
	
	@GET
	@Path("teste")
	@Produces(MediaType.TEXT_HTML)
	public String logars(@PathParam("usuario")String usuario, @PathParam("senha")String senha){
		return "cliente salvo com sucesso!";
	}

}
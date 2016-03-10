package br.com.systemGames.usuario.resource.impl;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.systemGames.excecao.BOException;
import br.com.systemGames.usuario.cambista.bo.impl.ConfiguracaoCambistaBO;
import br.com.systemGames.usuario.cambista.model.ConfiguracaoCambistaVO;
import br.com.systemGames.usuario.resource.IConfiguracaoCambistaResource;

@Path("cambista")
public class ConfiguracaoCambistaResource implements IConfiguracaoCambistaResource {
	
	ConfiguracaoCambistaBO configuracaoCambistaBO;
	
	public ConfiguracaoCambistaResource() {
		
		configuracaoCambistaBO = new ConfiguracaoCambistaBO();
		
	}


	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("listarTodosCambista")
	public ArrayList<ConfiguracaoCambistaVO> listarTodos() throws SQLException,
			BOException {
		ConfiguracaoCambistaVO configuracaoCambistaVO = new ConfiguracaoCambistaVO();
		try {

			return configuracaoCambistaBO.listarTodos(configuracaoCambistaVO);


		} catch (Exception ex) {
			throw new BOException(ex);
		}finally {

			configuracaoCambistaBO = null;


		}
	}

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("salvarCambista")
	public String inserir(ConfiguracaoCambistaVO configuracaoCambistaVO)
			throws SQLException, BOException {
		try {

			return configuracaoCambistaBO.inserir(configuracaoCambistaVO);


		} catch (Exception ex) {
			throw new BOException(ex);
		}finally {

			configuracaoCambistaBO = null;


		}
	}

	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	@Path("removerUsuario")
	public String remover(ConfiguracaoCambistaVO configuracaoCambistaVO)
			throws SQLException, BOException {
		try {
			
			
			return configuracaoCambistaBO.remover(configuracaoCambistaVO);


		} catch (Exception ex) {
			throw new BOException(ex);
		}finally {

			configuracaoCambistaBO = null;


		}
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("alterar")
	public String alterar(ConfiguracaoCambistaVO configuracaoCambistaVO)
			throws SQLException, BOException {
		
		return configuracaoCambistaBO.alterar(configuracaoCambistaVO);
	}

}

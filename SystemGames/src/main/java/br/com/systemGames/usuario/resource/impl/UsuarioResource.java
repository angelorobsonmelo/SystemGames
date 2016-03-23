package br.com.systemGames.usuario.resource.impl;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.systemGames.excecao.BOException;
import br.com.systemGames.usuario.bo.impl.UsuarioBO;
import br.com.systemGames.usuario.model.UsuarioVO;
import br.com.systemGames.usuario.resource.IUsuarioResource;


@Path("usuario")
public class UsuarioResource implements IUsuarioResource {

	private UsuarioBO usuarioBO;
	private UsuarioVO usuarioVO;
	
	public UsuarioResource() {
		usuarioBO = new UsuarioBO();
		usuarioVO = new UsuarioVO();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("autenticar")
	public UsuarioVO autenticar(UsuarioVO usuarioVO) throws BOException,
			SQLException {
		try {

			return usuarioBO.autenticar(usuarioVO);


		} catch (Exception ex) {
			throw new BOException(ex);
		}finally {

			usuarioBO = null;


		}
	}

}

package br.com.systemGames.usuario.resource.impl;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.systemGames.excecao.BOException;
import br.com.systemGames.usuario.bo.impl.CambistaBO;
import br.com.systemGames.usuario.model.CambistaVO;
import br.com.systemGames.usuario.resource.ICambistaResource;

@Path("cambista")
public class CambistaResource implements ICambistaResource {

	private CambistaVO cambistaVO;
	private CambistaBO cambistaBO;

	public CambistaResource() {
		cambistaVO = new CambistaVO();
		cambistaBO = new CambistaBO();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("salvar")
	public String salvar(CambistaVO cambistaVO) throws BOException,
	SQLException {
		try {

			return cambistaBO.salvar(cambistaVO);


		} catch (Exception ex) {
			throw new BOException(ex);
		}finally {

			cambistaBO = null;


		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("pesquisarPorSeqUsuario/{sequencialUsuario}")
	public ArrayList<CambistaVO> pesquisarPorSeqUsuario(
			@PathParam("sequencialUsuario") Integer sequencialUsuario) throws BOException, SQLException {
		try {

			cambistaVO.getUsuarioVO().setSequencial(sequencialUsuario);
			
			return cambistaBO.pesquisarPorSeqUsuario(cambistaVO);


		} catch (Exception ex) {
			throw new BOException(ex);
		}finally {

			cambistaBO = null;


		}
	}

}

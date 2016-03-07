package br.com.systemGames.jogo.resource.impl;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.systemGames.excecao.BOException;
import br.com.systemGames.jogo.bo.impl.CampeonatoBO;
import br.com.systemGames.jogo.model.CampeonatoVO;
import br.com.systemGames.jogo.resource.ICampeonatoResource;


@Path("campeonato")
public class CampeonatoResource implements ICampeonatoResource {

	
	
	CampeonatoVO campeonatoVO = new CampeonatoVO();
	CampeonatoBO campeonatoBO = new CampeonatoBO();
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("salvar")
	public String salvar(CampeonatoVO campeonatoVO) throws SQLException, BOException {
		try {

			return campeonatoBO.salvar(campeonatoVO);


		} catch (Exception ex) {
			throw new BOException(ex);
		}finally {

			campeonatoBO = null;


		}
	}

	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("listarTodos")
	public ArrayList<CampeonatoVO> listarTodos() throws SQLException, BOException {
		try {

			return campeonatoBO.listarTodos();


		} catch (Exception ex) {
			throw new BOException(ex);
		}finally {

			campeonatoBO = null;


		}
	}


}

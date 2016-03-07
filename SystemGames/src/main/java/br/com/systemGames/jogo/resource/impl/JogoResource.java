package br.com.systemGames.jogo.resource.impl;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.systemGames.excecao.BOException;
import br.com.systemGames.jogo.bo.impl.JogoBO;
import br.com.systemGames.jogo.model.JogoVO;
import br.com.systemGames.jogo.resource.IJogoResource;

@Path("jogo")
public class JogoResource implements IJogoResource {

	private JogoVO jogoVO;
	private JogoBO jogoBO;

	public JogoResource() {
		jogoVO = new JogoVO();
		jogoBO = new JogoBO();
	}


	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("salvar")
	public String salvar(JogoVO jogoVO) throws SQLException, BOException {
		try {

			return jogoBO.salvar(jogoVO);


		} catch (Exception ex) {
			throw new BOException(ex);
		}finally {

			jogoBO = null;


		}
	}


	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	@Path("remover/{sequencial}")
	public String remover(@PathParam("sequencial") Integer sequencial) throws SQLException, BOException {
		try {

			jogoVO.setSequencial(sequencial);
			return jogoBO.remover(jogoVO);


		} catch (Exception ex) {
			throw new BOException(ex);
		}finally {

			jogoBO = null;


		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("listarTodos")
	public ArrayList<JogoVO> listarTodos() throws SQLException, BOException {
		try {

			return jogoBO.listarTodos();


		} catch (Exception ex) {
			throw new BOException(ex);
		}finally {

			jogoBO = null;


		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("retornarUltimoSequencial")
	public JogoVO retornarUltimoSequencial() throws SQLException, BOException {
		try {

			return jogoBO.retornarUltimoSequencial();


		} catch (Exception ex) {
			throw new BOException(ex);
		}finally {

			jogoBO = null;


		}
	}

}

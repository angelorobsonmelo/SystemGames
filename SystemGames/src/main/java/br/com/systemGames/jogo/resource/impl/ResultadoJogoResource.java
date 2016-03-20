package br.com.systemGames.jogo.resource.impl;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.systemGames.excecao.BOException;
import br.com.systemGames.jogo.bo.impl.ResultadoJogoBO;
import br.com.systemGames.jogo.model.JogoVO;
import br.com.systemGames.jogo.resource.IResultadoJogoResource;


@Path("resultado_jogo")
public class ResultadoJogoResource implements IResultadoJogoResource {

	private JogoVO jogoVO;
	private ResultadoJogoBO resultadoJogoBO;
	
	
	public ResultadoJogoResource() {
		jogoVO = new JogoVO();
		resultadoJogoBO = new ResultadoJogoBO();
	}
	
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("salvar")
	public String salvar(JogoVO jogoVO) throws BOException, SQLException {
		try {

			return resultadoJogoBO.salvar(jogoVO);


		} catch (Exception ex) {
			throw new BOException(ex);
		}finally {

			resultadoJogoBO = null;


		}
	}
	
	
	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("atualizar")
	public String atualizar(JogoVO jogoVO) throws BOException, SQLException {
		try {

			return resultadoJogoBO.atualizar(jogoVO);


		} catch (Exception ex) {
			throw new BOException(ex);
		}finally {

			resultadoJogoBO = null;


		}
	}

	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	@Path("remover/{sequencial}")
	public String remover(Integer sequencial) throws BOException, SQLException {
		try {

			jogoVO.getResultadoJogoVO().setSequencial(sequencial);
			
			return resultadoJogoBO.remover(jogoVO);


		} catch (Exception ex) {
			throw new BOException(ex);
		}finally {

			resultadoJogoBO = null;


		}
	}

}

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
import br.com.systemGames.jogo.bo.impl.EsporteBO;
import br.com.systemGames.jogo.model.EsporteVO;
import br.com.systemGames.jogo.resource.IEsporteResource;


@Path("esporte")
public class EsporteResource implements IEsporteResource {


	EsporteVO esporteVO = new EsporteVO();
	EsporteBO esporteBO = new EsporteBO();


	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("salvar")
	public String salvar(EsporteVO esporteVO) throws SQLException, BOException {
		try {

			return esporteBO.salvar(esporteVO);


		} catch (Exception ex) {
			throw new BOException(ex);
		}finally {

			esporteBO = null;


		}
	}


	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("listarTodos")
	public ArrayList<EsporteVO> listarTodos() throws SQLException, BOException {
		try {

			return esporteBO.listarTodos();


		} catch (Exception ex) {
			throw new BOException(ex);
		}finally {

			esporteBO = null;


		}
	}


	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	@Path("remover/{sequencial}")
	public String remover(@PathParam("sequencial") Integer sequencial) throws SQLException, BOException {
		try {


			esporteVO.setSequencial(sequencial);
			return esporteBO.remover(esporteVO);


		} catch (Exception ex) {
			throw new BOException(ex);
		}finally {

			esporteBO = null;


		}
	}


}

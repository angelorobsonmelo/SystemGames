package br.com.systemGames.aposta.resource.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.systemGames.aposta.bo.impl.ApostaBO;
import br.com.systemGames.aposta.model.ApostaVO;
import br.com.systemGames.aposta.model.JogoApostadoVO;
import br.com.systemGames.aposta.resource.IApostaResource;
import br.com.systemGames.excecao.BOException;
import br.com.systemGames.jogo.bo.impl.JogoBO;
import br.com.systemGames.jogo.model.JogoVO;
import br.com.systemGames.util.DataUtil;

@Path("aposta")
public class ApostaResource implements IApostaResource {

	ApostaBO apostaBO;
	ApostaVO apostaVO;
	JogoVO jogoVO;
	JogoBO jogoBO;




	public ApostaResource() {
		apostaBO = new ApostaBO();
		apostaVO = new ApostaVO();
		jogoVO = new JogoVO();
		jogoBO = new JogoBO();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("salvar")
	public String salvar(ApostaVO apostaVO)throws BOException, SQLException {
		try {
			String ok = null;
			apostaBO.salvar(apostaVO);
			for (JogoApostadoVO jogoApostadoVO : apostaVO.getJogoApostadoVO()) {


				apostaVO.getJogoApostadoVO2().setDataJogo(jogoApostadoVO.getDataJogo());
				apostaVO.getJogoApostadoVO2().setHoraJogo(jogoApostadoVO.getHoraJogo());
				apostaVO.getJogoApostadoVO2().setSeq(jogoApostadoVO.getSeq());
				apostaVO.getJogoApostadoVO2().setTipoAposta(jogoApostadoVO.getTipoAposta());
				apostaVO.getJogoApostadoVO2().setValTaxa(jogoApostadoVO.getValTaxa());
				ok =  apostaBO.salvarJogo(apostaVO);
			}

			return ok;


		} catch (Exception ex) {
			throw new BOException(ex);
		}finally {

			apostaBO = null;


		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("listarPorParams/{sequencial}")
	public ArrayList<JogoVO> listarPorParams(@PathParam("sequencial") Integer sequencial) throws BOException {
		try {

			jogoVO.getCampeonatoVO().setSequencial(sequencial);			
			return jogoBO.listarPorParams(jogoVO);


		} catch (Exception ex) {
			throw new BOException(ex);
		}finally {

			jogoBO = null;


		}
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("apostaPorParams")
	public ArrayList<ApostaVO> consultarAposta(ApostaVO apostaVO)
			throws BOException, SQLException {
		try {
			System.out.println(apostaVO.getDataInicial());
			return apostaBO.consultarApostaPorParametros(apostaVO);
		} catch (Exception ex) {
			throw new BOException(ex);

		}
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("somaValorAposta")
	public ArrayList<ApostaVO> listarSomaPorParams(ApostaVO apostaVO) throws BOException {
		try {
			Date data = new Date(System.currentTimeMillis());
			//apostaVO.setDataInicial(data);
			//apostaVO.getCambistaVO().setSequencial(sequencial);	
			
			System.out.println(apostaVO.getCambistaVO().getSequencial());
			System.out.println(apostaVO.getDataInicial());
			return apostaBO.consultarSomaApostaPorParametros(apostaVO);


		} catch (Exception ex) {
			throw new BOException(ex);
		}finally {

			jogoBO = null;


		}
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("apostaPorSeq")
	public ArrayList<ApostaVO> consultarApostaPorSequencial(ApostaVO apostaVO)
			throws BOException, SQLException {
		try {
			return apostaBO.apostaPorSequencial(apostaVO);
		} catch (Exception ex) {
			throw new BOException(ex);

		}
	}

	public String remover(Integer sequencial) throws BOException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<ApostaVO> pesquisarPorSeqUsuario(Integer sequencialUsuario)
			throws BOException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}


	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("inserirResultadoAposta")
	public String inserirResultadoAposta(ApostaVO apostaVO) throws BOException,
	SQLException {
		try {

			apostaVO.setResultadoAposta("SIM");
			return apostaBO.inserirResultadoAposta(apostaVO);

		} catch (Exception ex) {
			throw new BOException(ex);
		}finally {

			apostaBO = null;


		}
	}

}

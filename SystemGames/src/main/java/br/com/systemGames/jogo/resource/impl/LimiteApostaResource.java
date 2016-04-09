package br.com.systemGames.jogo.resource.impl;

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
import br.com.systemGames.jogo.bo.impl.LimiteApostaBO;
import br.com.systemGames.jogo.model.LimiteApostaVO;
import br.com.systemGames.jogo.resource.ILimiteApostaResource;
import br.com.systemGames.usuario.bo.impl.UsuarioBO;
import br.com.systemGames.usuario.model.UsuarioVO;


@Path("taxa_limite")
public class LimiteApostaResource implements ILimiteApostaResource {

	private LimiteApostaVO limiteApostaVO;
	private LimiteApostaBO limiteApostaBO;
	private UsuarioBO usuarioBO;

	public LimiteApostaResource() {
		limiteApostaVO = new LimiteApostaVO();
		limiteApostaBO = new LimiteApostaBO();
		usuarioBO = new UsuarioBO();
	}

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("salvar")
	public String salvar(LimiteApostaVO limiteApostaVO) throws BOException,
	SQLException {

		try {

			return limiteApostaBO.salvar(limiteApostaVO);

		} catch (Exception ex) {
			throw new BOException(ex);
		}finally {

			limiteApostaBO = null;


		}
	}


	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("salvarAdmin")
	public String salvarAdmin(LimiteApostaVO limiteApostaVO) throws BOException,
	SQLException {


		String ok = null;


		try {
			
			ArrayList<UsuarioVO> retornoBanco = usuarioBO.listarTodosUsuarios();

			for (UsuarioVO usuarioVO : retornoBanco) {
				
				limiteApostaVO.getUsuarioVO().setSequencial(usuarioVO.getSequencial());

				ok = limiteApostaBO.salvar(limiteApostaVO);

			}

			return ok;


		} catch (Exception ex) {
			throw new BOException(ex);
		}finally {

			limiteApostaBO = null;


		}
	}


	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("listarPorSeqJogoEUsuario/{sequencialJogo}/{sequencialUsuario}")
	public LimiteApostaVO buscarTodosPorSeqJogoEUsuario(
			@PathParam("sequencialJogo") Integer sequencialJogo, @PathParam("sequencialUsuario") Integer sequencialUsuario)
					throws BOException, SQLException {

		try {

			System.out.println(sequencialJogo);
			System.out.println(sequencialUsuario);

			limiteApostaVO.getJogoVO().setSequencial(sequencialJogo);
			limiteApostaVO.getUsuarioVO().setSequencial(sequencialUsuario);

			return limiteApostaBO.buscarTodosPorSeqJogoEUsuario(limiteApostaVO);

		}  catch (Exception ex) {
			throw new BOException(ex);
		}finally {

			limiteApostaBO = null;


		}
	}

	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("listarPorSeqJogo/{sequencialJogo}")
	public ArrayList<LimiteApostaVO> buscarTodosPorSeqJogo(@PathParam("sequencialJogo") Integer seqJogo)
			throws SQLException, BOException {
		try {


			limiteApostaVO.getJogoVO().setSequencial(seqJogo);

			return limiteApostaBO.buscarTodosPorSeqJogo(limiteApostaVO);

		}  catch (Exception ex) {
			throw new BOException(ex);
		}finally {

			limiteApostaBO = null;


		}
	}

	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("listarPorSeqUsuario/{sequencialAdmin}")
	public ArrayList<LimiteApostaVO> buscarPorSeqUsuario(@PathParam("sequencialAdmin") Integer sequencialAdmin)
			throws SQLException, BOException {
		try {


			limiteApostaVO.getUsuarioVO().setSequencial(sequencialAdmin);

			return limiteApostaBO.buscarPorSeqUsuario(limiteApostaVO);

		}  catch (Exception ex) {
			throw new BOException(ex);
		}finally {

			limiteApostaBO = null;


		}
	}

}

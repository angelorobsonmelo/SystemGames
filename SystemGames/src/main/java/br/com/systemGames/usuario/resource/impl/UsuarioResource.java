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
import br.com.systemGames.usuario.bo.impl.UsuarioBO;
import br.com.systemGames.usuario.model.UsuarioVO;

@Path("usuario")
public class UsuarioResource {
	
	
	UsuarioBO usuarioBO;

	public UsuarioResource() {
		
		usuarioBO = new UsuarioBO();
	}

	@POST		
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("salvarUsuario")
	public String inserir(UsuarioVO usuarioVO) throws SQLException, BOException {
		try {
			
			System.out.println("nome = "+usuarioVO.getNomeUsuario());
			System.out.println("getEmail = "+usuarioVO.getEmail());
			System.out.println("login = "+usuarioVO.getLogin());
			System.out.println("getSenha = "+usuarioVO.getSenha());
			System.out.println("getCpf = "+usuarioVO.getCpf());
			System.out.println("getNumeroRg = "+usuarioVO.getNumeroRg());
			System.out.println("getNumeroCCelular = "+usuarioVO.getContato());
			System.out.println("getCodigoTipoUsuario = "+usuarioVO.getCodigoTipoUsuario().getSequencial());
			System.out.println("getEndereco = "+usuarioVO.getEndereco());
			System.out.println("getNumeroEndereco = "+usuarioVO.getNumeroEndereco());
			System.out.println("getComplemento = "+usuarioVO.getComplemento());
			System.out.println("getBairro = "+usuarioVO.getBairro());
			System.out.println("getCidade = "+usuarioVO.getCidade());
			System.out.println("getCep = "+usuarioVO.getCep());
			System.out.println("getUf = "+usuarioVO.getUf());
			System.out.println("getLimiteMaximoVendaDiario = "+usuarioVO.getConfiguracaoCambistaVO().getLimiteMaximoVendaDiario());
			System.out.println("getLimiteMaximoVendaIndividual = "+usuarioVO.getConfiguracaoCambistaVO().getLimiteMaximoVendaIndividual());
			System.out.println("getObservacao = "+usuarioVO.getConfiguracaoCambistaVO().getObservacao());
			System.out.println("getCodigoUsuario = "+usuarioVO.getConfiguracaoCambistaVO().getSequencial());
			System.out.println("getComissao1 = "+usuarioVO.getConfiguracaoCambistaVO().getComissao1());
			System.out.println("getComissao2 = "+usuarioVO.getConfiguracaoCambistaVO().getComissao2());
			System.out.println("getComissao3 = "+usuarioVO.getConfiguracaoCambistaVO().getComissao3());

			return usuarioBO.inserir(usuarioVO);


		} catch (Exception ex) {
			System.out.println(ex);
			throw new BOException(ex);
			
		}finally {

			usuarioBO = null;


		}
	}

	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	@Path("removerUsuario")
	public String remover(UsuarioVO usuarioVO) throws SQLException, BOException {
		try {

			
			return usuarioBO.remover(usuarioVO);


		} catch (Exception ex) {
			throw new BOException(ex);
		}finally {

			usuarioBO = null;


		}
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("alterarUsuario")
	public String alterar(UsuarioVO usuarioVO) throws SQLException, BOException {
		
		return usuarioBO.alterar(usuarioVO);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("listarTodosUsuarios")
	public ArrayList<UsuarioVO> consultarPorParams()
			throws SQLException, BOException {
		UsuarioVO usuarioVO = new UsuarioVO();
		try {

			return usuarioBO.consultarPorParams(usuarioVO);


		} catch (Exception ex) {
			throw new BOException(ex);
		}finally {

			usuarioBO = null;


		}
	}

}

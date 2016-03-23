package br.com.systemGames.usuario.bo.impl;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.systemGames.database.Conexao;
import br.com.systemGames.excecao.BOException;
import br.com.systemGames.usuario.bo.IUsuarioBO;
import br.com.systemGames.usuario.dao.impl.UsuarioDAO;
import br.com.systemGames.usuario.model.UsuarioVO;

public class UsuarioBO implements IUsuarioBO {

	
	UsuarioDAO usuarioDAO = new UsuarioDAO();

	ArrayList<String> resultadoExecucaoProcedures= new ArrayList<String>();
	
	
	public ArrayList<?> listarTodos() throws BOException {
		// TODO Auto-generated method stub
		return null;
	}

	public String inserir(UsuarioVO usuarioVO) throws BOException {
		// TODO Auto-generated method stub
		return null;
	}

	public String remover(UsuarioVO usuarioVO) throws BOException {
		// TODO Auto-generated method stub
		return null;
	}

	public UsuarioVO autenticar(UsuarioVO usuarioVO) throws BOException,
			SQLException {
		try {

			/*Setar o AutoCommit para False, validar toda a transação antes do Commit*/
			Conexao.setarAutoCommitParaFalse();

			return usuarioDAO.autenticar(usuarioVO);
		} catch (Exception e) {
			throw new BOException(e);
		}
	}

	public String alterar(UsuarioVO usuarioVO) throws BOException {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<UsuarioVO> consultarPorParams(UsuarioVO usuarioVO)
			throws BOException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}

package br.com.systemGames.usuario.dao;

import java.util.ArrayList;

import br.com.systemGames.excecao.DAOException;
import br.com.systemGames.usuario.model.UsuarioVO;

public interface IUsuarioDAO {
	
	public ArrayList<?> listarTodos() throws DAOException;
	
	public String inserir(UsuarioVO usuarioVO) throws DAOException;

	public String remover(UsuarioVO usuarioVO) throws DAOException;

	public String alterar(UsuarioVO usuarioVO) throws DAOException;

	public ArrayList<UsuarioVO> consultarPorParams() throws DAOException;

}

package br.com.systemGames.usuario.resource;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.systemGames.excecao.BOException;
import br.com.systemGames.usuario.model.UsuarioVO;

public interface IUsuarioResource {
	
	public String inserir(UsuarioVO usuarioVO) throws SQLException, BOException;

	public String remover(UsuarioVO usuarioVO) throws SQLException, BOException;

	public String alterar(UsuarioVO usuarioVO) throws SQLException, BOException;

	public ArrayList<UsuarioVO> consultarPorParams() throws SQLException, BOException;


}

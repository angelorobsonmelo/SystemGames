package br.com.systemGames.usuario.bo;

import java.util.ArrayList;

import br.com.systemGames.excecao.BOException;
import br.com.systemGames.usuario.model.UsuarioVO;

public interface IUsuarioBO {
	
	public ArrayList<?> listarTodos() throws BOException;
	
	public String inserir(UsuarioVO usuarioVO) throws BOException;

	public String remover(UsuarioVO usuarioVO) throws BOException;

	public String alterar(UsuarioVO usuarioVO) throws BOException;

	public ArrayList<UsuarioVO> consultarPorParams(UsuarioVO usuarioVO) throws BOException;

}

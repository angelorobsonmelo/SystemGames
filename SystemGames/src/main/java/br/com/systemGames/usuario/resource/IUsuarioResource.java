package br.com.systemGames.usuario.resource;

import java.sql.SQLException;

import br.com.systemGames.excecao.BOException;
import br.com.systemGames.usuario.model.UsuarioVO;

public interface IUsuarioResource {
	public UsuarioVO autenticar(UsuarioVO usuarioVO) throws BOException, SQLException;

	
}

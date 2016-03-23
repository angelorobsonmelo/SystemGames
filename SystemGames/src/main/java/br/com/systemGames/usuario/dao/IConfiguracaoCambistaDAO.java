package br.com.systemGames.usuario.dao;

import br.com.systemGames.excecao.DAOException;
import br.com.systemGames.usuario.model.CambistaVO;

public interface IConfiguracaoCambistaDAO {
	
	public String inserir(CambistaVO cambistaVO) throws DAOException;
	


}

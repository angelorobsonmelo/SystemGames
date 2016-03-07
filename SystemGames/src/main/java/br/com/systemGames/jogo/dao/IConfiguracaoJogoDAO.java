package br.com.systemGames.jogo.dao;

import br.com.systemGames.excecao.DAOException;
import br.com.systemGames.jogo.model.ConfiguracaoJogoVO;
import br.com.systemGames.jogo.model.JogoVO;

public interface IConfiguracaoJogoDAO {

	public String salvar(JogoVO jogoVO) throws DAOException;
	
	public ConfiguracaoJogoVO buscarConfiguracaoPorSeqJogo(JogoVO jogoVO) throws DAOException;
	
	
}

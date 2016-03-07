package br.com.systemGames.jogo.dao;

import br.com.systemGames.excecao.DAOException;
import br.com.systemGames.jogo.model.JogoVO;
import br.com.systemGames.jogo.model.LimiteApostaVO;

public interface ILimiteApostaDAO {

	public String salvar(JogoVO jogoVO) throws DAOException;
	
	public LimiteApostaVO buscarTodosPorSeqJogo(JogoVO jogoVO) throws DAOException; 
	
}

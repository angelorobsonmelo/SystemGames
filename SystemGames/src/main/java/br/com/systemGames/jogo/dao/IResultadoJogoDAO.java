package br.com.systemGames.jogo.dao;

import br.com.systemGames.excecao.DAOException;

import br.com.systemGames.jogo.model.JogoVO;

public interface IResultadoJogoDAO {
	
	public String salvar(JogoVO jogoVO) throws DAOException;
	
	public String atualizar(JogoVO jogoVO) throws DAOException;
	
	public String remover(JogoVO jogoVO) throws DAOException;
	
	

}

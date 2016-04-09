package br.com.systemGames.jogo.dao;

import java.util.ArrayList;

import br.com.systemGames.excecao.DAOException;
import br.com.systemGames.jogo.model.JogoVO;

public interface IJogoDAO {
	
	public String salvar(JogoVO jogoVO) throws DAOException;
	
	public String remover(JogoVO jogoVO) throws DAOException;
	
	public ArrayList<JogoVO> listarTodos() throws DAOException; 
	
	public ArrayList<JogoVO> listarTodosBasico() throws DAOException; 
	
	public JogoVO retornarUltimoSequencial() throws DAOException;
	
	public ArrayList<JogoVO> listarPorParams(JogoVO jogoVO) throws DAOException; 
	

	
}

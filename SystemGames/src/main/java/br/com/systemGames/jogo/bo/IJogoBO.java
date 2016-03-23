package br.com.systemGames.jogo.bo;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.systemGames.excecao.BOException;
import br.com.systemGames.jogo.model.JogoVO;

public interface IJogoBO {


	public String salvar(JogoVO jogoVO) throws SQLException, BOException;

	public String remover(JogoVO jogoVO) throws SQLException, BOException;

	public ArrayList<JogoVO> listarTodos() throws SQLException, BOException;

	public JogoVO retornarUltimoSequencial() throws SQLException, BOException;
	
	public ArrayList<JogoVO> listarTodosBasico()  throws SQLException, BOException; 
	
	public ArrayList<JogoVO> listarPorParams(JogoVO jogoVO) throws BOException;

}

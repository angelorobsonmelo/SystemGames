package br.com.systemGames.jogo.resource;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.systemGames.excecao.BOException;
import br.com.systemGames.jogo.model.JogoVO;

public interface IJogoResource {

	public String salvar(JogoVO jogoVO) throws SQLException, BOException;

	public String remover(Integer sequencial) throws SQLException, BOException;

	public ArrayList<JogoVO> listarTodos() throws SQLException, BOException;

	public JogoVO retornarUltimoSequencial() throws SQLException, BOException;

}

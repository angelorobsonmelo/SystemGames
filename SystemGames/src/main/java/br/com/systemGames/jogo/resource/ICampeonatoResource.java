package br.com.systemGames.jogo.resource;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.systemGames.excecao.BOException;
import br.com.systemGames.jogo.model.CampeonatoVO;

public interface ICampeonatoResource {

	
	public String salvar(CampeonatoVO campeonatoVO) throws SQLException, BOException;
	
	public String remover(Integer sequencial) throws SQLException, BOException;

	public ArrayList<CampeonatoVO> listarTodos() throws SQLException, BOException;

	
}

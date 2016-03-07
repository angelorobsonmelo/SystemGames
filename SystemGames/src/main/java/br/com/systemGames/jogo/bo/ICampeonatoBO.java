package br.com.systemGames.jogo.bo;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.systemGames.excecao.BOException;
import br.com.systemGames.jogo.model.CampeonatoVO;

public interface ICampeonatoBO {

	public String salvar(CampeonatoVO campeonatoVO) throws SQLException, BOException;

	public ArrayList<CampeonatoVO> listarTodos() throws SQLException, BOException;

	public CampeonatoVO listarPorSequencial(CampeonatoVO campeonatoVO) throws SQLException, BOException;
	
}

package br.com.systemGames.jogo.dao;

import java.util.ArrayList;

import br.com.systemGames.excecao.DAOException;
import br.com.systemGames.jogo.model.CampeonatoVO;

public interface ICampeonatoDAO {

	public String salvar(CampeonatoVO campeonatoVO) throws DAOException;
	
	public String remover(CampeonatoVO campeonatoVO) throws DAOException;

	public ArrayList<CampeonatoVO> listarTodos() throws DAOException;

	public CampeonatoVO listarPorSequencial(CampeonatoVO campeonatoVO) throws DAOException;

}

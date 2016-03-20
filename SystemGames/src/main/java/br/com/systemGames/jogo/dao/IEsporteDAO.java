package br.com.systemGames.jogo.dao;

import java.util.ArrayList;

import br.com.systemGames.excecao.DAOException;
import br.com.systemGames.jogo.model.EsporteVO;

public interface IEsporteDAO {

	public String salvar(EsporteVO esporteVO) throws DAOException;
	
	public String remover(EsporteVO esporteVO) throws DAOException;
	
	public ArrayList<EsporteVO> listarTodos() throws DAOException;
	
	public EsporteVO listarPorSequencial(EsporteVO esporteVO) throws DAOException;
	
}

package br.com.systemGames.jogo.resource;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.systemGames.excecao.BOException;
import br.com.systemGames.jogo.model.EsporteVO;

public interface IEsporteResource {

	public String salvar(EsporteVO esporteVO) throws SQLException, BOException;

	public ArrayList<EsporteVO> listarTodos() throws SQLException, BOException;

	
}

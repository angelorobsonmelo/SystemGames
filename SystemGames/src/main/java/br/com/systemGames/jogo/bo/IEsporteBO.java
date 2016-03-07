package br.com.systemGames.jogo.bo;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.systemGames.excecao.BOException;
import br.com.systemGames.jogo.model.EsporteVO;

public interface IEsporteBO {

	public String salvar(EsporteVO esporteVO) throws SQLException, BOException;

	public ArrayList<EsporteVO> listarTodos() throws SQLException, BOException;

	public EsporteVO listarPorSequencial(EsporteVO esporteVO) throws SQLException, BOException;

}

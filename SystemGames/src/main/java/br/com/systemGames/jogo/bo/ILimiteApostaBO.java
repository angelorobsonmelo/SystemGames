package br.com.systemGames.jogo.bo;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.systemGames.excecao.BOException;
import br.com.systemGames.jogo.model.LimiteApostaVO;

public interface ILimiteApostaBO {

	public String salvar(LimiteApostaVO limiteApostaVO) throws SQLException, BOException;

	public LimiteApostaVO buscarTodosPorSeqJogoEUsuario(LimiteApostaVO limiteApostaVO) throws SQLException, BOException;

	public ArrayList<LimiteApostaVO> buscarTodosPorSeqJogo(LimiteApostaVO limiteApostaVO) throws SQLException, BOException;
	
	public ArrayList<LimiteApostaVO> buscarPorSeqUsuario(
			LimiteApostaVO limiteApostaVO) throws SQLException, BOException;
}

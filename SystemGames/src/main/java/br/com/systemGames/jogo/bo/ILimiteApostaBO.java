package br.com.systemGames.jogo.bo;

import java.sql.SQLException;

import br.com.systemGames.excecao.BOException;
import br.com.systemGames.jogo.model.JogoVO;
import br.com.systemGames.jogo.model.LimiteApostaVO;

public interface ILimiteApostaBO {

	public String salvar(JogoVO jogoVO) throws SQLException, BOException;

	public LimiteApostaVO buscarTodosPorSeqJogo(JogoVO jogoVO) throws SQLException, BOException;

}

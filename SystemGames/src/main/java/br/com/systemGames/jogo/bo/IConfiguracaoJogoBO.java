package br.com.systemGames.jogo.bo;

import java.sql.SQLException;

import br.com.systemGames.excecao.BOException;
import br.com.systemGames.jogo.model.ConfiguracaoJogoVO;
import br.com.systemGames.jogo.model.JogoVO;

public interface IConfiguracaoJogoBO {

public String salvar(JogoVO jogoVO) throws SQLException, BOException;
	
	public ConfiguracaoJogoVO buscarConfiguracaoPorSeqJogo(JogoVO jogoVO) throws SQLException, BOException;
	
	
}

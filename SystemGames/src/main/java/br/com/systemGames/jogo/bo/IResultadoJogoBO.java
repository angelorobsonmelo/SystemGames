package br.com.systemGames.jogo.bo;

import java.sql.SQLException;

import br.com.systemGames.excecao.BOException;
import br.com.systemGames.jogo.model.JogoVO;

public interface IResultadoJogoBO {

	public String salvar(JogoVO jogoVO) throws BOException, SQLException;
	
	public String remover(JogoVO jogoVO) throws BOException, SQLException;
	
	public String atualizar(JogoVO jogoVO) throws BOException, SQLException;
	
}

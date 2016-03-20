package br.com.systemGames.jogo.resource;

import java.sql.SQLException;

import br.com.systemGames.excecao.BOException;
import br.com.systemGames.jogo.model.JogoVO;

public interface IResultadoJogoResource {

	public String salvar(JogoVO jogoVO) throws BOException, SQLException;
	
	public String atualizar(JogoVO jogoVO) throws BOException, SQLException;
	
	public String remover(Integer sequencial) throws BOException, SQLException;
}

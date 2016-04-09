package br.com.systemGames.jogo.resource;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.systemGames.excecao.BOException;
import br.com.systemGames.jogo.model.LimiteApostaVO;

public interface ILimiteApostaResource {

	public String salvar(LimiteApostaVO limiteApostaVO) throws BOException, SQLException;

	public LimiteApostaVO buscarTodosPorSeqJogoEUsuario(Integer sequencialJogo, Integer sequencialUsuario) throws BOException, SQLException;

	public String salvarAdmin(LimiteApostaVO limiteApostaVO) throws BOException,
	SQLException;
	
	
	public ArrayList<LimiteApostaVO> buscarTodosPorSeqJogo(
			Integer seqJogo) throws SQLException, BOException;

	public ArrayList<LimiteApostaVO> buscarPorSeqUsuario(
			Integer sequencialAdmin) throws SQLException, BOException;
	
}

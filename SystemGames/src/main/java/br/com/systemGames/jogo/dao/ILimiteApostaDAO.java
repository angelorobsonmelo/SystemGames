package br.com.systemGames.jogo.dao;

import java.util.ArrayList;

import br.com.systemGames.excecao.DAOException;
import br.com.systemGames.jogo.model.LimiteApostaVO;

public interface ILimiteApostaDAO {

	public String salvar(LimiteApostaVO limiteApostaVO) throws DAOException;
	
	public LimiteApostaVO buscarTodosPorSeqJogoEUsuario(LimiteApostaVO limiteApostaVO) throws DAOException; 
	
	public ArrayList<LimiteApostaVO> buscarTodosPorSeqJogo(LimiteApostaVO limiteApostaVO) throws DAOException;
	
	public ArrayList<LimiteApostaVO> buscarPorSeqUsuario(LimiteApostaVO limiteApostaVO) throws DAOException;
}

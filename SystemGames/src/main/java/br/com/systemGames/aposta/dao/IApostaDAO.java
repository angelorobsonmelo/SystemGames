package br.com.systemGames.aposta.dao;

import java.util.ArrayList;

import br.com.systemGames.aposta.model.ApostaVO;
import br.com.systemGames.excecao.DAOException;

public interface IApostaDAO {
	
	public String salvar(ApostaVO apostaVO) throws DAOException;
	
	public String salvarJogo(ApostaVO apostaVO) throws DAOException;
	
	public String remover(ApostaVO apostaVO) throws DAOException;
	
	public ArrayList<ApostaVO> pesquisarPorSeqUsuario(ApostaVO apostaVO) throws DAOException;
	
	
	public String inserirResultadoAposta(ApostaVO apostaVO) throws DAOException;
	
	

}

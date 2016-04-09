package br.com.systemGames.aposta.resource;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.systemGames.aposta.model.ApostaVO;
import br.com.systemGames.aposta.model.JogoApostadoVO;
import br.com.systemGames.excecao.BOException;

public interface IApostaResource {
	
	public String salvar(ApostaVO apostaVO) throws BOException, SQLException;
	
	public String remover(Integer sequencial) throws BOException, SQLException;
	
	public ArrayList<ApostaVO> pesquisarPorSeqUsuario(Integer sequencialUsuario) throws BOException, SQLException;
	
	public String inserirResultadoAposta(ApostaVO apostaVO) throws BOException,
	SQLException;


}

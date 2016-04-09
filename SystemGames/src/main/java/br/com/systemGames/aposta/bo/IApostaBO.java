package br.com.systemGames.aposta.bo;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.systemGames.aposta.model.ApostaVO;
import br.com.systemGames.excecao.BOException;

public interface IApostaBO {
	
    public String salvar(ApostaVO apostaVO) throws BOException;
    
    public String salvarJogo(ApostaVO apostaVO) throws BOException;
	
	public String remover(ApostaVO apostaVO) throws BOException;
	
	public ArrayList<ApostaVO> pesquisarPorSeqUsuario(ApostaVO apostaVO) throws BOException;
	
	
	public String inserirResultadoAposta(ApostaVO apostaVO) throws BOException, SQLException;

}

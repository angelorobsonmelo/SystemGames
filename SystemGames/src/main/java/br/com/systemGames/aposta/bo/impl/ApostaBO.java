package br.com.systemGames.aposta.bo.impl;

import java.util.ArrayList;

import br.com.systemGames.aposta.bo.IApostaBO;
import br.com.systemGames.aposta.dao.impl.ApostaDAO;
import br.com.systemGames.aposta.model.ApostaVO;
import br.com.systemGames.database.Conexao;
import br.com.systemGames.excecao.BOException;

public class ApostaBO implements IApostaBO{
	
	ApostaDAO apostaDAO = new ApostaDAO();

	ArrayList<String> resultadoExecucaoProcedures= new ArrayList<String>();

	public String salvar(ApostaVO apostaVO) throws BOException {
		String resultadoExecucaoInserirAposta = null;

		try{	
			/*Setar o AutoCommit para False, validar toda a transação antes do Commit*/
			Conexao.setarAutoCommitParaFalse();

			resultadoExecucaoProcedures.clear();

			resultadoExecucaoInserirAposta =  apostaDAO.salvar(apostaVO);
			resultadoExecucaoProcedures.add(resultadoExecucaoInserirAposta);

			if (!resultadoExecucaoInserirAposta.equals("OK")){
				throw new BOException("Erro ao inserir aposta. "+resultadoExecucaoInserirAposta);
			}

			return Conexao.verificarResultadosDaExecucaoDeProceduresValidandoCommit(resultadoExecucaoProcedures);

		}catch (Exception ex) { 
			/*Preferivel que seja dado um Rollback neste caso*/

			throw new BOException(ex);
		}
		finally{
			resultadoExecucaoInserirAposta = null;
			resultadoExecucaoProcedures.clear();

		}
	}

	public String remover(ApostaVO apostaVO) throws BOException {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<ApostaVO> pesquisarPorSeqUsuario(ApostaVO apostaVO)
			throws BOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String salvarJogo(ApostaVO apostaVO) throws BOException {
		String resultadoExecucaoInserirJogoAposta = null;

		try{	
			/*Setar o AutoCommit para False, validar toda a transação antes do Commit*/
			Conexao.setarAutoCommitParaFalse();

			resultadoExecucaoProcedures.clear();

			resultadoExecucaoInserirJogoAposta =  apostaDAO.salvarJogo(apostaVO);
			resultadoExecucaoProcedures.add(resultadoExecucaoInserirJogoAposta);

			if (!resultadoExecucaoInserirJogoAposta.equals("OK")){
				throw new BOException("Erro ao inserir aposta. "+resultadoExecucaoInserirJogoAposta);
			}

			return Conexao.verificarResultadosDaExecucaoDeProceduresValidandoCommit(resultadoExecucaoProcedures);

		}catch (Exception ex) { 
			/*Preferivel que seja dado um Rollback neste caso*/

			throw new BOException(ex);
		}
		finally{
			resultadoExecucaoInserirJogoAposta = null;
			resultadoExecucaoProcedures.clear();

		}
	}

}

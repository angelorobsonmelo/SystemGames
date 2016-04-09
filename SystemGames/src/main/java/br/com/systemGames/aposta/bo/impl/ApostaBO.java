package br.com.systemGames.aposta.bo.impl;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.systemGames.aposta.bo.IApostaBO;
import br.com.systemGames.aposta.dao.impl.ApostaDAO;
import br.com.systemGames.aposta.model.ApostaVO;
import br.com.systemGames.database.Conexao;
import br.com.systemGames.excecao.BOException;
import br.com.systemGames.jogo.model.JogoVO;

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
	
	public ArrayList<ApostaVO> consultarApostaPorParametros(
			ApostaVO apostaVO) throws BOException {
		try {
			/*Setar o AutoCommit para False*/
			
			return apostaDAO.consultarApostaPorParametros(apostaVO);	
			
		}catch (Exception ex) {
			throw new BOException(ex);
		}	
	}
	
	public ArrayList<ApostaVO> consultarSomaApostaPorParametros(
			ApostaVO apostaVO) throws BOException {
		try {
			/*Setar o AutoCommit para False*/
			
			return apostaDAO.consultarSomaApostaPorParametros(apostaVO);	
			
		}catch (Exception ex) {
			throw new BOException(ex);
		}	
	}
	
	public ArrayList<ApostaVO> apostaPorSequencial(ApostaVO apostaVO)
			throws BOException {
		try {
			/*Setar o AutoCommit para False*/
			
			
			ArrayList<ApostaVO> retornoBanco =  apostaDAO.apostaPorSequencial(apostaVO);
			ArrayList<ApostaVO> lista = new ArrayList<ApostaVO>();
			
			for (ApostaVO apostaVO1 : retornoBanco) {

				


				if (apostaVO1.getResultadoJogoVO().getResultadoCasa() == 0 && apostaVO1.getResultadoJogoVO().getResultadoFora() == 0 && apostaVO1.getConfiguracaoJogoVO().getJogoFinalizado() == false) {

					apostaVO1.getResultadoJogoVO().setResultadoCasa(null);
					apostaVO1.getResultadoJogoVO().setResultadoFora(null);

					lista.add(apostaVO1);

				}else {

					lista.add(apostaVO1);

				}
			}
			
			return lista;
			
		}catch (Exception ex) {
			throw new BOException(ex);
		}	
	}

	public String inserirResultadoAposta(ApostaVO apostaVO) throws BOException,
			SQLException {
		String resultadoExecucaoInserirAposta = null;

		try{	
			/*Setar o AutoCommit para False, validar toda a transação antes do Commit*/
			Conexao.setarAutoCommitParaFalse();

			resultadoExecucaoProcedures.clear();

			resultadoExecucaoInserirAposta =  apostaDAO.inserirResultadoAposta(apostaVO);
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

}

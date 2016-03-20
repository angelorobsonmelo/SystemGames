package br.com.systemGames.jogo.bo.impl;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.systemGames.database.Conexao;
import br.com.systemGames.excecao.BOException;
import br.com.systemGames.jogo.bo.IResultadoJogoBO;
import br.com.systemGames.jogo.dao.impl.ResultadoJogoDAO;
import br.com.systemGames.jogo.model.JogoVO;

public class ResultadoJogoBO implements IResultadoJogoBO {

	
	ResultadoJogoDAO ResultadoJogoDAO = new ResultadoJogoDAO();

	ArrayList<String> resultadoExecucaoProcedures= new ArrayList<String>();
	
	
	public String salvar(JogoVO jogoVO) throws BOException, SQLException {
		String resultadoExecucaoInserirUnidadeDeSaude = null;

		try{	
			/*Setar o AutoCommit para False, validar toda a transação antes do Commit*/
			Conexao.setarAutoCommitParaFalse();

			resultadoExecucaoProcedures.clear();

			
			
			
			resultadoExecucaoInserirUnidadeDeSaude =  ResultadoJogoDAO.salvar(jogoVO);
			resultadoExecucaoProcedures.add(resultadoExecucaoInserirUnidadeDeSaude);

			if (!resultadoExecucaoInserirUnidadeDeSaude.equals("OK")){
				throw new BOException("Erro ao inserir unidade de saúde. "+resultadoExecucaoInserirUnidadeDeSaude);
			}

			return Conexao.verificarResultadosDaExecucaoDeProceduresValidandoCommit(resultadoExecucaoProcedures);

		}catch (Exception ex) { 
			/*Preferivel que seja dado um Rollback neste caso*/

			throw new BOException(ex);
		}
		finally{
			resultadoExecucaoInserirUnidadeDeSaude = null;
			resultadoExecucaoProcedures.clear();

		}
	}

	public String remover(JogoVO jogoVO) throws BOException, SQLException {
		String resultadoExecucaoInserirUnidadeDeSaude = null;

		try{	
			/*Setar o AutoCommit para False, validar toda a transação antes do Commit*/
			Conexao.setarAutoCommitParaFalse();

			resultadoExecucaoProcedures.clear();

			resultadoExecucaoInserirUnidadeDeSaude =  ResultadoJogoDAO.remover(jogoVO);
			resultadoExecucaoProcedures.add(resultadoExecucaoInserirUnidadeDeSaude);

			if (!resultadoExecucaoInserirUnidadeDeSaude.equals("OK")){
				throw new BOException("Erro ao inserir unidade de saúde. "+resultadoExecucaoInserirUnidadeDeSaude);
			}

			return Conexao.verificarResultadosDaExecucaoDeProceduresValidandoCommit(resultadoExecucaoProcedures);

		}catch (Exception ex) { 
			/*Preferivel que seja dado um Rollback neste caso*/

			throw new BOException(ex);
		}
		finally{
			resultadoExecucaoInserirUnidadeDeSaude = null;
			resultadoExecucaoProcedures.clear();

		}
	}

	public String atualizar(JogoVO jogoVO) throws BOException, SQLException {
		String resultadoExecucaoInserirUnidadeDeSaude = null;

		try{	
			/*Setar o AutoCommit para False, validar toda a transação antes do Commit*/
			Conexao.setarAutoCommitParaFalse();

			resultadoExecucaoProcedures.clear();

			
			
			
			resultadoExecucaoInserirUnidadeDeSaude =  ResultadoJogoDAO.atualizar(jogoVO);
			resultadoExecucaoProcedures.add(resultadoExecucaoInserirUnidadeDeSaude);

			if (!resultadoExecucaoInserirUnidadeDeSaude.equals("OK")){
				throw new BOException("Erro ao inserir unidade de saúde. "+resultadoExecucaoInserirUnidadeDeSaude);
			}

			return Conexao.verificarResultadosDaExecucaoDeProceduresValidandoCommit(resultadoExecucaoProcedures);

		}catch (Exception ex) { 
			/*Preferivel que seja dado um Rollback neste caso*/

			throw new BOException(ex);
		}
		finally{
			resultadoExecucaoInserirUnidadeDeSaude = null;
			resultadoExecucaoProcedures.clear();

		}
	}

}

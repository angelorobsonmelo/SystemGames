package br.com.systemGames.jogo.bo.impl;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.systemGames.database.Conexao;
import br.com.systemGames.excecao.BOException;
import br.com.systemGames.jogo.bo.IEsporteBO;
import br.com.systemGames.jogo.dao.impl.EsporteDAO;
import br.com.systemGames.jogo.model.EsporteVO;

public class EsporteBO implements IEsporteBO {

	
	EsporteDAO esporteDAO = new EsporteDAO();

	ArrayList<String> resultadoExecucaoProcedures= new ArrayList<String>();
	
	public String salvar(EsporteVO esporteVO) throws SQLException, BOException {
		String resultadoExecucaoInserirUnidadeDeSaude = null;

		try{	
			/*Setar o AutoCommit para False, validar toda a transação antes do Commit*/
			Conexao.setarAutoCommitParaFalse();

			resultadoExecucaoProcedures.clear();

			resultadoExecucaoInserirUnidadeDeSaude =  esporteDAO.salvar(esporteVO);
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

	public ArrayList<EsporteVO> listarTodos() throws SQLException, BOException {
		try {

			/*Setar o AutoCommit para False, validar toda a transação antes do Commit*/
			Conexao.setarAutoCommitParaFalse();

			return esporteDAO.listarTodos();
		} catch (Exception e) {
			throw new BOException(e);
		}
	}

	public EsporteVO listarPorSequencial(EsporteVO esporteVO)
			throws SQLException, BOException {
		try {

			/*Setar o AutoCommit para False, validar toda a transação antes do Commit*/
			Conexao.setarAutoCommitParaFalse();

			return esporteDAO.listarPorSequencial(esporteVO);
		} catch (Exception e) {
			throw new BOException(e);
		}
	}

}

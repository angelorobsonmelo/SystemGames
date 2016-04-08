package br.com.systemGames.jogo.bo.impl;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.systemGames.database.Conexao;
import br.com.systemGames.excecao.BOException;
import br.com.systemGames.jogo.bo.ILimiteApostaBO;
import br.com.systemGames.jogo.dao.impl.LimiteApostaDAO;
import br.com.systemGames.jogo.model.LimiteApostaVO;

public class LimiteApostaBO implements ILimiteApostaBO {

	LimiteApostaDAO limiteApostaDAO = new LimiteApostaDAO();

	ArrayList<String> resultadoExecucaoProcedures= new ArrayList<String>();

	public String salvar(LimiteApostaVO limiteApostaVO) throws SQLException,
	BOException {
		String resultadoExecucaoInserirUnidadeDeSaude = null;

		try{	
			/*Setar o AutoCommit para False, validar toda a transação antes do Commit*/
			Conexao.setarAutoCommitParaFalse();

			resultadoExecucaoProcedures.clear();

			resultadoExecucaoInserirUnidadeDeSaude =  limiteApostaDAO.salvar(limiteApostaVO);
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

	public LimiteApostaVO buscarTodosPorSeqJogoEUsuario(LimiteApostaVO limiteApostaVO)
			throws SQLException, BOException {
		try {

			/*Setar o AutoCommit para False, validar toda a transação antes do Commit*/
			Conexao.setarAutoCommitParaFalse();

			return limiteApostaDAO.buscarTodosPorSeqJogoEUsuario(limiteApostaVO);
		} catch (Exception e) {
			throw new BOException(e);
		}
	}

	@Override
	public ArrayList<LimiteApostaVO> buscarTodosPorSeqJogo(
			LimiteApostaVO limiteApostaVO) throws SQLException, BOException {
		try {

			/*Setar o AutoCommit para False, validar toda a transação antes do Commit*/
			Conexao.setarAutoCommitParaFalse();

			return limiteApostaDAO.buscarTodosPorSeqJogo(limiteApostaVO);
		} catch (Exception e) {
			throw new BOException(e);
		}
	}

	@Override
	public ArrayList<LimiteApostaVO> buscarPorSeqUsuario(
			LimiteApostaVO limiteApostaVO) throws SQLException, BOException {
		try {

			/*Setar o AutoCommit para False, validar toda a transação antes do Commit*/
			Conexao.setarAutoCommitParaFalse();

			return limiteApostaDAO.buscarPorSeqUsuario(limiteApostaVO);
		} catch (Exception e) {
			throw new BOException(e);
		}
	}

}

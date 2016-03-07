package br.com.systemGames.jogo.bo.impl;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.systemGames.database.Conexao;
import br.com.systemGames.excecao.BOException;
import br.com.systemGames.jogo.bo.ILimiteApostaBO;
import br.com.systemGames.jogo.dao.impl.LimiteApostaDAO;
import br.com.systemGames.jogo.model.JogoVO;
import br.com.systemGames.jogo.model.LimiteApostaVO;

public class LimiteApostaBO implements ILimiteApostaBO {

	LimiteApostaDAO limiteApostaDAO = new LimiteApostaDAO();

	ArrayList<String> resultadoExecucaoProcedures= new ArrayList<String>();

	public String salvar(JogoVO jogoVO) throws SQLException,
	BOException {
		String resultadoExecucaoInserirUnidadeDeSaude = null;

		try{	
			/*Setar o AutoCommit para False, validar toda a transação antes do Commit*/
			Conexao.setarAutoCommitParaFalse();

			resultadoExecucaoProcedures.clear();

			resultadoExecucaoInserirUnidadeDeSaude =  limiteApostaDAO.salvar(jogoVO);
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

	public LimiteApostaVO buscarTodosPorSeqJogo(JogoVO jogoVO)
			throws SQLException, BOException {
		try {

			/*Setar o AutoCommit para False, validar toda a transação antes do Commit*/
			Conexao.setarAutoCommitParaFalse();

			return limiteApostaDAO.buscarTodosPorSeqJogo(jogoVO);
		} catch (Exception e) {
			throw new BOException(e);
		}
	}

}

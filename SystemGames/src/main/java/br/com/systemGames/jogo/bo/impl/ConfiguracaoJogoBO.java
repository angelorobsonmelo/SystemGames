package br.com.systemGames.jogo.bo.impl;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.systemGames.database.Conexao;
import br.com.systemGames.excecao.BOException;
import br.com.systemGames.jogo.bo.IConfiguracaoJogoBO;
import br.com.systemGames.jogo.dao.impl.ConfiguracaoJogoDAO;
import br.com.systemGames.jogo.model.ConfiguracaoJogoVO;
import br.com.systemGames.jogo.model.JogoVO;

public class ConfiguracaoJogoBO implements IConfiguracaoJogoBO {



	ConfiguracaoJogoDAO configuracaoJogoDAO = new ConfiguracaoJogoDAO();

	ArrayList<String> resultadoExecucaoProcedures= new ArrayList<String>();

	public String salvar(ConfiguracaoJogoVO configuracaoJogoVO)
			throws SQLException, BOException {

		String resultadoExecucaoInserirUnidadeDeSaude = null;
		try{	
			/*Setar o AutoCommit para False, validar toda a transação antes do Commit*/
			Conexao.setarAutoCommitParaFalse();

			resultadoExecucaoProcedures.clear();

			resultadoExecucaoInserirUnidadeDeSaude =  configuracaoJogoDAO.salvar(configuracaoJogoVO);
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

	public ConfiguracaoJogoVO buscarConfiguracaoPorSeqJogo(
			JogoVO jogoVO) throws SQLException, BOException {
		try {

			/*Setar o AutoCommit para False, validar toda a transação antes do Commit*/
			Conexao.setarAutoCommitParaFalse();

			return configuracaoJogoDAO.buscarConfiguracaoPorSeqJogo(jogoVO);
		} catch (Exception e) {
			throw new BOException(e);
		}
	}

}

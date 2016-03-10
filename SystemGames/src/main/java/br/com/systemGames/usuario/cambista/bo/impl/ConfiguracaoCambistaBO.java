package br.com.systemGames.usuario.cambista.bo.impl;

import java.util.ArrayList;

import br.com.systemGames.database.Conexao;
import br.com.systemGames.excecao.BOException;
import br.com.systemGames.usuario.cambista.bo.IConfiguracaoCambistaBO;
import br.com.systemGames.usuario.cambista.dao.impl.ConfiguracaoCambistaDAO;
import br.com.systemGames.usuario.cambista.model.ConfiguracaoCambistaVO;

public class ConfiguracaoCambistaBO implements IConfiguracaoCambistaBO {

	private ArrayList<String> resultadoExecucaoProcedures;	
	ConfiguracaoCambistaDAO configuracaoCambistaDAO;	
	
	public ConfiguracaoCambistaBO() {
		configuracaoCambistaDAO = new ConfiguracaoCambistaDAO();
		resultadoExecucaoProcedures = new ArrayList<String>();
	}
	
	
	public ArrayList<ConfiguracaoCambistaVO> listarTodos(ConfiguracaoCambistaVO configuracaoCambistaVO) throws BOException {
		try{
			/*Setar o AutoCommit para False*/
			Conexao.setarAutoCommitParaFalse();			

			return configuracaoCambistaDAO.listarTodos(configuracaoCambistaVO);

		}catch (Exception ex) { 
			
			throw new BOException(ex);
			
		}
	}

	
	public String inserir(ConfiguracaoCambistaVO configuracaoCambistaVO)
			throws BOException {
		String resultadoExecucaoInserirConfiguracaoCambista = null;		

		try{
			/*Setar o AutoCommit para False, validar toda a transação antes do Commit*/
			Conexao.setarAutoCommitParaFalse();
			
			

			resultadoExecucaoInserirConfiguracaoCambista =  configuracaoCambistaDAO.inserir(configuracaoCambistaVO);
			resultadoExecucaoProcedures.add(resultadoExecucaoInserirConfiguracaoCambista);

			if (!resultadoExecucaoInserirConfiguracaoCambista.equals("OK")){

				throw new BOException("Erro ao inserir Configuração Cambista. "+resultadoExecucaoInserirConfiguracaoCambista);
			}

			return Conexao.verificarResultadosDaExecucaoDeProceduresValidandoCommit(resultadoExecucaoProcedures);

		}catch (Exception ex) { 

			throw new BOException(ex);

		}finally{

			resultadoExecucaoInserirConfiguracaoCambista = null;
			resultadoExecucaoProcedures.clear();

		}
	}

	
	public String remover(ConfiguracaoCambistaVO configuracaoCambistaVO)
			throws BOException {
		String resultadoExecucaoRemoverConfiguracaoCambista = null;

		try{
			/*Setar o AutoCommit para False, validar toda a transação antes do Commit*/
			Conexao.setarAutoCommitParaFalse();
			resultadoExecucaoProcedures = new ArrayList<String>();

			resultadoExecucaoRemoverConfiguracaoCambista =  configuracaoCambistaDAO.remover(configuracaoCambistaVO);
			resultadoExecucaoProcedures.add(resultadoExecucaoRemoverConfiguracaoCambista);

			if (!resultadoExecucaoRemoverConfiguracaoCambista.equals("OK")){

				throw new BOException("Erro ao remover Configuracao Cambista. "+resultadoExecucaoRemoverConfiguracaoCambista);
			}

			return Conexao.verificarResultadosDaExecucaoDeProceduresValidandoCommit(resultadoExecucaoProcedures);

		}catch (Exception ex) { 

			throw new BOException(ex);

		}finally{

			resultadoExecucaoRemoverConfiguracaoCambista = null;
			resultadoExecucaoProcedures.clear();

		}
	}

	
	public String alterar(ConfiguracaoCambistaVO configuracaoCambistaVO)
			throws BOException {
		String resultadoExecucaoAlterarConfiguracaoCambista = null;

		try{
			/*Setar o AutoCommit para False, validar toda a transação antes do Commit*/
			Conexao.setarAutoCommitParaFalse();
			resultadoExecucaoProcedures = new ArrayList<String>();

			resultadoExecucaoAlterarConfiguracaoCambista =  configuracaoCambistaDAO.alterar(configuracaoCambistaVO);
			resultadoExecucaoProcedures.add(resultadoExecucaoAlterarConfiguracaoCambista);

			if (!resultadoExecucaoAlterarConfiguracaoCambista.equals("OK")){

				throw new BOException("Erro ao alterar Configuracao Cambista. "+resultadoExecucaoAlterarConfiguracaoCambista);
			}

			return Conexao.verificarResultadosDaExecucaoDeProceduresValidandoCommit(resultadoExecucaoProcedures);

		}catch (Exception ex) { 

			throw new BOException(ex);

		}finally{

			resultadoExecucaoAlterarConfiguracaoCambista = null;
			resultadoExecucaoProcedures.clear();

		}
	}

	
}

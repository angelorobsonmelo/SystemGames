package br.com.systemGames.usuario.cambista.bo.impl;

import java.util.ArrayList;

import br.com.systemGames.database.Conexao;
import br.com.systemGames.excecao.BOException;
import br.com.systemGames.usuario.bo.impl.UsuarioBO;
import br.com.systemGames.usuario.cambista.bo.IConfiguracaoCambistaBO;
import br.com.systemGames.usuario.cambista.dao.impl.ConfiguracaoCambistaDAO;
import br.com.systemGames.usuario.cambista.model.ConfiguracaoCambistaVO;

public class ConfiguracaoCambistaBO implements IConfiguracaoCambistaBO {

	private ArrayList<String> resultadoExecucaoProcedures;	
	ConfiguracaoCambistaDAO configuracaoCambistaDAO;	
	UsuarioBO usuarioBO;
	
	public ConfiguracaoCambistaBO() {
		configuracaoCambistaDAO = new ConfiguracaoCambistaDAO();
		usuarioBO = new UsuarioBO();
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
		String[] resultadoExecucaoInserirUsuario = null;
		String resultadoExecucaoInserirConfiguracaoCambista = null;

		try{
			/*Setar o AutoCommit para False, validar toda a transação antes do Commit*/
			Conexao.setarAutoCommitParaFalse();

			resultadoExecucaoInserirUsuario =  configuracaoCambistaDAO.inserirUsuario(configuracaoCambistaVO).split("SEQ_USUARIO");
			resultadoExecucaoProcedures.add(resultadoExecucaoInserirUsuario[0]);

			if (resultadoExecucaoInserirUsuario[0].equals("OK")){	
				
				String codigo = resultadoExecucaoInserirUsuario[1];
				System.out.println("codigo = "+codigo);
				configuracaoCambistaVO.setSequencial(Integer.parseInt(codigo));				
				resultadoExecucaoInserirConfiguracaoCambista = configuracaoCambistaDAO.inserir(configuracaoCambistaVO);
				
				if(!resultadoExecucaoInserirConfiguracaoCambista.equals("OK")){
					throw new BOException("Erro ao inserir Configuração Cambista. "+resultadoExecucaoInserirConfiguracaoCambista);
				}				
				
			}else{
				throw new BOException("Erro ao inserir na tabela Usuario"+resultadoExecucaoInserirUsuario[0]);
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

			resultadoExecucaoRemoverConfiguracaoCambista =  configuracaoCambistaDAO.remover(configuracaoCambistaVO);
			resultadoExecucaoProcedures.add(resultadoExecucaoRemoverConfiguracaoCambista);

			if (!resultadoExecucaoRemoverConfiguracaoCambista.equals("OK")){
				
				throw new BOException("Erro ao remover Usuario Cambista. "+resultadoExecucaoRemoverConfiguracaoCambista);
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
		String resultadoExecucaoAlterarUsuario = null;

		try{
			/*Setar o AutoCommit para False, validar toda a transação antes do Commit*/
			Conexao.setarAutoCommitParaFalse();
			resultadoExecucaoProcedures = new ArrayList<String>();

			resultadoExecucaoAlterarUsuario =  configuracaoCambistaDAO.alterarUsuario(configuracaoCambistaVO);
			resultadoExecucaoProcedures.add(resultadoExecucaoAlterarUsuario);

			if (resultadoExecucaoAlterarUsuario.equals("OK")){

				resultadoExecucaoAlterarConfiguracaoCambista = configuracaoCambistaDAO.alterar(configuracaoCambistaVO);
				if (!resultadoExecucaoAlterarConfiguracaoCambista.equals("OK")){

					throw new BOException("Erro ao alterar Configuração Cambita. "+resultadoExecucaoAlterarConfiguracaoCambista);
				}
				
			}else{
				throw new BOException("Erro ao alterar Usuario. "+resultadoExecucaoAlterarUsuario);
				
			}

			return Conexao.verificarResultadosDaExecucaoDeProceduresValidandoCommit(resultadoExecucaoProcedures);

		}catch (Exception ex) { 

			throw new BOException(ex);

		}finally{

			resultadoExecucaoAlterarUsuario = null;
			resultadoExecucaoAlterarConfiguracaoCambista = null;
			resultadoExecucaoProcedures.clear();

		}
	}


	public String removerUsuario(ConfiguracaoCambistaVO configuracaoCambistaVO)
			throws BOException {
		String resultadoExecucaoRemoverUsuario = null;

		try{
			/*Setar o AutoCommit para False, validar toda a transação antes do Commit*/
			Conexao.setarAutoCommitParaFalse();

			resultadoExecucaoRemoverUsuario =  configuracaoCambistaDAO.removerUsuario(configuracaoCambistaVO);
			resultadoExecucaoProcedures.add(resultadoExecucaoRemoverUsuario);

			if (!resultadoExecucaoRemoverUsuario.equals("OK")){

				throw new BOException("Erro ao remover Usuario. "+resultadoExecucaoRemoverUsuario);
			}

			return Conexao.verificarResultadosDaExecucaoDeProceduresValidandoCommit(resultadoExecucaoProcedures);

		}catch (Exception ex) { 

			throw new BOException(ex);

		}finally{

			resultadoExecucaoRemoverUsuario = null;
			resultadoExecucaoProcedures.clear();

		}
	}

	
}

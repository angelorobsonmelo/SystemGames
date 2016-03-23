package br.com.systemGames.usuario.bo.impl;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.systemGames.database.Conexao;
import br.com.systemGames.excecao.BOException;
import br.com.systemGames.usuario.bo.ICambistaBO;
import br.com.systemGames.usuario.dao.impl.CambistaDAO;
import br.com.systemGames.usuario.model.CambistaVO;

public class CambistaBO implements ICambistaBO {

	 
	CambistaDAO cambistaDAO = new CambistaDAO();

	ArrayList<String> resultadoExecucaoProcedures= new ArrayList<String>();

	
	
	public String salvar(CambistaVO cambistaVO) throws BOException,
			SQLException {
		String resultadoExecucaoInserirUnidadeDeSaude = null;

		try{	
			/*Setar o AutoCommit para False, validar toda a transação antes do Commit*/
			Conexao.setarAutoCommitParaFalse();

			resultadoExecucaoProcedures.clear();

			resultadoExecucaoInserirUnidadeDeSaude =  cambistaDAO.salvar(cambistaVO);
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
	
	
	
	public String inserir(CambistaVO cambistaVO) throws BOException,
	SQLException {
String resultadoExecucaoInserirUnidadeDeSaude = null;

try{	
	/*Setar o AutoCommit para False, validar toda a transação antes do Commit*/
	Conexao.setarAutoCommitParaFalse();

	resultadoExecucaoProcedures.clear();

	resultadoExecucaoInserirUnidadeDeSaude =  cambistaDAO.salvar(cambistaVO);
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



	public ArrayList<CambistaVO> pesquisarPorSeqUsuario(CambistaVO cambistaVO)
			throws BOException, SQLException {
		try {

			/*Setar o AutoCommit para False, validar toda a transação antes do Commit*/
			Conexao.setarAutoCommitParaFalse();

			return cambistaDAO.pesquisarPorSeqUsuario(cambistaVO);
		} catch (Exception e) {
			throw new BOException(e);
		}
	}



	public String remover(CambistaVO cambistaVO) throws BOException,
			SQLException {
	
			String resultadoExecucaoInserirUnidadeDeSaude = null;

			try{	
				/*Setar o AutoCommit para False, validar toda a transação antes do Commit*/
				Conexao.setarAutoCommitParaFalse();

				resultadoExecucaoProcedures.clear();

				resultadoExecucaoInserirUnidadeDeSaude =  cambistaDAO.remover(cambistaVO);
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

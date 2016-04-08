package br.com.systemGames.jogo.dao.impl;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import br.com.systemGames.database.Conexao;
import br.com.systemGames.excecao.BOException;
import br.com.systemGames.excecao.DAOException;
import br.com.systemGames.jogo.dao.IConfiguracaoJogoDAO;
import br.com.systemGames.jogo.model.ConfiguracaoJogoVO;
import br.com.systemGames.jogo.model.JogoVO;
import br.com.systemGames.util.VerificadorValorObjeto;

public class ConfiguracaoJogoDAO implements IConfiguracaoJogoDAO {

	private CallableStatement cstmt;
	private String resultado;
	private String procedure;

	public String inserir(ConfiguracaoJogoVO configuracaoJogoVO)
			throws DAOException {
		procedure = "{ ? = CALL SP_CONFIGURACAO_JOGO_INSERIR(?,?,?)}";
		cstmt = null;
		resultado = null;

		try
		{

			cstmt = Conexao.getConexao().prepareCall(procedure);
			cstmt.registerOutParameter(1, Types.VARCHAR);
			cstmt.setInt(2, VerificadorValorObjeto.retornaIntValorObjetoOuZero(configuracaoJogoVO.getJogoVO().getSequencial()));
			cstmt.setBoolean(3, configuracaoJogoVO.getFinalizarAutomaticamente());
			cstmt.setBoolean(4, configuracaoJogoVO.getJogoFinalizado());

			cstmt.execute();

			resultado = (String) cstmt.getString(1);
			cstmt.close();

			return resultado;
		}
		catch(Exception ex)
		{
			throw new DAOException(ex);
		}
		finally{

			procedure = null;
			cstmt = null;
		}
	}

	public String atualizar(ConfiguracaoJogoVO configuracaoJogoVO) throws DAOException {

		procedure = "{ ? = CALL SP_CONFIGURACAO_JOGO_ATUALIZAR(?,?)}";
		cstmt = null;
		resultado = null;

		try
		{

			cstmt = Conexao.getConexao().prepareCall(procedure);
			cstmt.registerOutParameter(1, Types.VARCHAR);
			cstmt.setInt(2, VerificadorValorObjeto.retornaIntValorObjetoOuZero(configuracaoJogoVO.getJogoVO().getSequencial()));
			cstmt.setBoolean(3, configuracaoJogoVO.getJogoFinalizado());


			cstmt.execute();

			resultado = (String) cstmt.getString(1);
			cstmt.close();

			return resultado;
		}
		catch(Exception ex)
		{
			throw new DAOException(ex);
		}
		finally{

			procedure = null;
			cstmt = null;
		}


	}

	public ConfiguracaoJogoVO buscarConfiguracaoPorSeqJogo(
			JogoVO jogoVO) throws DAOException {
		procedure = "{? = CALL SP_CONFIGURACAO_JOGO_BUSCAR_POR_SEQ_JOGO(?)}";
		cstmt = null;
		ConfiguracaoJogoVO configuracaoJogoRetorno = null;

		try
		{
			cstmt = Conexao.getConexao().prepareCall(procedure);
			cstmt.registerOutParameter(1, Types.OTHER);
			cstmt.setInt(2, VerificadorValorObjeto.retornaIntValorObjetoOuZero(jogoVO.getSequencial()));

			cstmt.execute();

			configuracaoJogoRetorno = mapearResultSet((ResultSet) cstmt.getObject(1));

			cstmt.close();		

			return configuracaoJogoRetorno;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			throw new DAOException(ex);
		} finally{        	
			/*Indicar ao Garbage Collection do Java que as variáveis 
			 * podem ser esvaziadas do Coletor de Lixo
			 */        	
			procedure = null;
			cstmt = null;
		}
	}


	public ConfiguracaoJogoVO mapearResultSet(ResultSet rs) throws SQLException, BOException, DAOException{

		ConfiguracaoJogoVO configuracaoJogoVO = null;

		if(rs.next()){

			configuracaoJogoVO = new ConfiguracaoJogoVO();

			configuracaoJogoVO.setSequencial(rs.getInt("seq_configuracao_jogo"));
			configuracaoJogoVO.setFinalizarAutomaticamente(rs.getBoolean("finalizar_automaticamente"));
			configuracaoJogoVO.setJogoFinalizado(rs.getBoolean("jogo_finalizado"));

		}
		return configuracaoJogoVO;


	}

	public String salvar(ConfiguracaoJogoVO configuracaoJogoVO) throws DAOException { 


		if (configuracaoJogoVO.getJogoVO().getSequencial() != null) {

			return atualizar(configuracaoJogoVO);

		} else {


			return inserir(configuracaoJogoVO);
		}
	}

}

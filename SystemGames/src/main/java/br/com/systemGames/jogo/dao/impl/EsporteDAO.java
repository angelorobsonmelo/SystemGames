package br.com.systemGames.jogo.dao.impl;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import br.com.systemGames.database.Conexao;
import br.com.systemGames.excecao.BOException;
import br.com.systemGames.excecao.DAOException;
import br.com.systemGames.jogo.dao.IEsporteDAO;
import br.com.systemGames.jogo.model.EsporteVO;
import br.com.systemGames.util.VerificadorValorObjeto;

public class EsporteDAO implements IEsporteDAO {


	private CallableStatement cstmt;
	private String resultado;
	private String procedure;


	public String atualizar(EsporteVO esporteVO) throws DAOException {
		procedure = "{ ? = CALL SP_ESPORTE_ATUALIZAR(?,?)}";
		cstmt = null;
		resultado = null;

		try
		{

			cstmt = Conexao.getConexao().prepareCall(procedure);
			cstmt.registerOutParameter(1, Types.VARCHAR);
			cstmt.setString(2, VerificadorValorObjeto.retornaStringValorObjetoOuNull(esporteVO.getNome()));
			cstmt.setInt(3, VerificadorValorObjeto.retornaIntValorObjetoOuZero(esporteVO.getSequencial()));

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


	public String inserir(EsporteVO esporteVO) throws DAOException {
		procedure = "{ ? = CALL SP_ESPORTE_INSERIR(?)}";
		cstmt = null;
		resultado = null;

		try
		{

			cstmt = Conexao.getConexao().prepareCall(procedure);
			cstmt.registerOutParameter(1, Types.VARCHAR);
			cstmt.setString(2, VerificadorValorObjeto.retornaStringValorObjetoOuNull(esporteVO.getNome()));

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

	public String salvar(EsporteVO esporteVO) throws DAOException {


		if (esporteVO.getSequencial() != null) {

			return atualizar(esporteVO);

		} else {

			return inserir(esporteVO);

		}

	}

	public ArrayList<EsporteVO> listarTodos() throws DAOException {
		procedure = "{? = CALL SP_ESPORTE_BUSCAR_TODOS()}";
		cstmt = null;
		ArrayList<EsporteVO> lista = new ArrayList<EsporteVO>();

		try
		{
			cstmt = Conexao.getConexao().prepareCall(procedure);
			cstmt.registerOutParameter(1, Types.OTHER);


			cstmt.execute();

			lista = mapearResultSet((ResultSet) cstmt.getObject(1));

			cstmt.close();		

			return lista;
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


	public ArrayList<EsporteVO> mapearResultSet(ResultSet rs) throws SQLException, BOException, DAOException{

		ArrayList<EsporteVO> lista = new ArrayList<EsporteVO>();

		while(rs.next()){

			EsporteVO esporteVO = new EsporteVO();

			esporteVO.setSequencial(rs.getInt("seq_esporte"));
			esporteVO.setNome(rs.getString("nome_esporte"));

			lista.add(esporteVO);
		}
		return lista;
	}

	public EsporteVO listarPorSequencial(EsporteVO esporteVO)
			throws DAOException {
		procedure = "{? = CALL SP_ESPORTE_BUSCAR_POR_SEQUENCIAL(?)}";
		cstmt = null;
		EsporteVO esporteVORetorno = null;

		try
		{
			cstmt = Conexao.getConexao().prepareCall(procedure);
			cstmt.registerOutParameter(1, Types.OTHER);
			cstmt.setInt(2, VerificadorValorObjeto.retornaIntValorObjetoOuZero(esporteVO.getSequencial()));

			cstmt.execute();

			esporteVORetorno = mapearResultSetUnicoObjeto((ResultSet) cstmt.getObject(1));

			cstmt.close();		

			return esporteVORetorno;
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


	public EsporteVO mapearResultSetUnicoObjeto(ResultSet rs) throws SQLException, BOException, DAOException{

		EsporteVO esporteVO = null;

		if(rs.next()){

			esporteVO = new EsporteVO();

			esporteVO.setSequencial(rs.getInt("seq_esporte"));
			esporteVO.setNome(rs.getString("nome_esporte"));

		}
		return esporteVO;


	}

}

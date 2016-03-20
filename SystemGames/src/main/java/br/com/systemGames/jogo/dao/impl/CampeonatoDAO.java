package br.com.systemGames.jogo.dao.impl;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import br.com.systemGames.database.Conexao;
import br.com.systemGames.excecao.BOException;
import br.com.systemGames.excecao.DAOException;
import br.com.systemGames.jogo.dao.ICampeonatoDAO;
import br.com.systemGames.jogo.model.CampeonatoVO;
import br.com.systemGames.util.VerificadorValorObjeto;

public class CampeonatoDAO implements ICampeonatoDAO {

	private CallableStatement cstmt;
	private String resultado;
	private String procedure;
	
	
	
	public String atualizar(CampeonatoVO campeonatoVO) throws DAOException {
		procedure = "{ ? = CALL SP_CAMPEONATO_ATUALIZAR(?,?)}";
		cstmt = null;
		resultado = null;

		try
		{

			cstmt = Conexao.getConexao().prepareCall(procedure);
			cstmt.registerOutParameter(1, Types.VARCHAR);
			cstmt.setString(2, VerificadorValorObjeto.retornaStringValorObjetoOuNull(campeonatoVO.getNome()));
			cstmt.setInt(3, VerificadorValorObjeto.retornaIntValorObjetoOuZero(campeonatoVO.getSequencial()));

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


	public String inserir(CampeonatoVO campeonatoVO) throws DAOException {
		procedure = "{ ? = CALL SP_CAMPEONATO_INSERIR(?)}";
		cstmt = null;
		resultado = null;

		try
		{

			cstmt = Conexao.getConexao().prepareCall(procedure);
			cstmt.registerOutParameter(1, Types.VARCHAR);
			cstmt.setString(2, VerificadorValorObjeto.retornaStringValorObjetoOuNull(campeonatoVO.getNome()));

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

	public String salvar(CampeonatoVO campeonatoVO) throws DAOException {


		if (campeonatoVO.getSequencial() != null) {

			return atualizar(campeonatoVO);

		} else {

			return inserir(campeonatoVO);

		}

	}

	public ArrayList<CampeonatoVO> listarTodos() throws DAOException {
		procedure = "{? = CALL SP_CAMPEONATO_BUSCAR_TODOS()}";
		cstmt = null;
		ArrayList<CampeonatoVO> lista = new ArrayList<CampeonatoVO>();

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


	public ArrayList<CampeonatoVO> mapearResultSet(ResultSet rs) throws SQLException, BOException, DAOException{

		ArrayList<CampeonatoVO> lista = new ArrayList<CampeonatoVO>();

		while(rs.next()){

			CampeonatoVO esporteVO = new CampeonatoVO();

			esporteVO.setSequencial(rs.getInt("seq_campeonato"));
			esporteVO.setNome(rs.getString("nome_campeonato"));

			lista.add(esporteVO);
		}
		return lista;
	}

	public CampeonatoVO listarPorSequencial(CampeonatoVO esporteVO)
			throws DAOException {
		procedure = "{? = CALL SP_CAMPEONATO_BUSCAR_POR_SEQUENCIAL(?)}";
		cstmt = null;
		CampeonatoVO campeonatoVORetorno = null;

		try
		{
			cstmt = Conexao.getConexao().prepareCall(procedure);
			cstmt.registerOutParameter(1, Types.OTHER);
			cstmt.setInt(2, VerificadorValorObjeto.retornaIntValorObjetoOuZero(esporteVO.getSequencial()));

			cstmt.execute();

			campeonatoVORetorno = mapearResultSetUnicoObjeto((ResultSet) cstmt.getObject(1));

			cstmt.close();		

			return campeonatoVORetorno;
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


	public CampeonatoVO mapearResultSetUnicoObjeto(ResultSet rs) throws SQLException, BOException, DAOException{

		CampeonatoVO esporteVO = null;

		if(rs.next()){

			esporteVO = new CampeonatoVO();

			esporteVO.setSequencial(rs.getInt("seq_campeonato"));
			esporteVO.setNome(rs.getString("nome_campeonato"));

		}
		return esporteVO;


	}


	public String remover(CampeonatoVO campeonatoVO) throws DAOException {
		procedure = "{ ? = CALL SP_CAMPEONATO_REMOVER(?)}";
		cstmt = null;
		resultado = null;

		try
		{

			cstmt = Conexao.getConexao().prepareCall(procedure);
			cstmt.registerOutParameter(1, Types.VARCHAR);
			cstmt.setInt(2, VerificadorValorObjeto.retornaIntValorObjetoOuZero(campeonatoVO.getSequencial()));

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

}

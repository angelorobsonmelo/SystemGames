package br.com.systemGames.jogo.dao.impl;

import java.sql.CallableStatement;
import java.sql.Types;

import br.com.systemGames.database.Conexao;
import br.com.systemGames.excecao.DAOException;
import br.com.systemGames.jogo.dao.IResultadoJogoDAO;
import br.com.systemGames.jogo.model.JogoVO;
import br.com.systemGames.util.VerificadorValorObjeto;

public class ResultadoJogoDAO implements IResultadoJogoDAO {

	private CallableStatement cstmt;
	private String resultado;
	private String procedure;


	public String salvar(JogoVO jogoVO) throws DAOException {


		return inserir(jogoVO);

	}


	public String inserir(JogoVO jogoVO) throws DAOException {
		procedure = "{ ? = CALL SP_RESULTADO_JOGO_INSERIR(?,?,?)}";
		cstmt = null;
		resultado = null;

		try
		{

			cstmt = Conexao.getConexao().prepareCall(procedure);
			cstmt.registerOutParameter(1, Types.VARCHAR);
			cstmt.setInt(2, VerificadorValorObjeto.retornaIntValorObjetoOuZero(jogoVO.getSequencial()));
			cstmt.setInt(3, VerificadorValorObjeto.retornaIntValorObjetoOuZero(jogoVO.getResultadoJogoVO().getResultadoCasa()));
			cstmt.setInt(4, VerificadorValorObjeto.retornaIntValorObjetoOuZero(jogoVO.getResultadoJogoVO().getResultadoFora()));

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

	public String atualizar(JogoVO jogoVO) throws DAOException {
		procedure = "{ ? = CALL SP_RESULTADO_JOGO_ATUALIZAR(?,?,?)}";
		cstmt = null;
		resultado = null;

		try
		{

			cstmt = Conexao.getConexao().prepareCall(procedure);
			cstmt.registerOutParameter(1, Types.VARCHAR);
			cstmt.setInt(2, VerificadorValorObjeto.retornaIntValorObjetoOuZero(jogoVO.getResultadoJogoVO().getSequencial()));
			cstmt.setInt(3, VerificadorValorObjeto.retornaIntValorObjetoOuZero(jogoVO.getResultadoJogoVO().getResultadoCasa()));
			cstmt.setInt(4, VerificadorValorObjeto.retornaIntValorObjetoOuZero(jogoVO.getResultadoJogoVO().getResultadoFora()));

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


	public String remover(JogoVO jogoVO) throws DAOException {
		procedure = "{ ? = CALL SP_RESULTADO_JOGO_REMOVER(?)}";
		cstmt = null;
		resultado = null;

		try
		{

			cstmt = Conexao.getConexao().prepareCall(procedure);
			cstmt.registerOutParameter(1, Types.VARCHAR);
			cstmt.setInt(2, VerificadorValorObjeto.retornaIntValorObjetoOuZero(jogoVO.getResultadoJogoVO().getSequencial()));

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

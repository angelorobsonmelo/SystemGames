package br.com.systemGames.aposta.dao.impl;

import java.sql.CallableStatement;
import java.sql.Types;
import java.util.ArrayList;

import br.com.systemGames.aposta.dao.IApostaDAO;
import br.com.systemGames.aposta.model.ApostaVO;
import br.com.systemGames.database.Conexao;
import br.com.systemGames.excecao.DAOException;

public class ApostaDAO implements IApostaDAO {
	
	private String procedure;
	private CallableStatement cstmt;
	private String resultado;

	public String salvar(ApostaVO apostaVO) throws DAOException {
		if (apostaVO.getSequencial() != null) {

			return atualizar(apostaVO);
		} else {

			return inserir(apostaVO);
		}
	}
	
	public String inserir(ApostaVO apostaVO) throws DAOException {
		procedure = "{ ? = CALL SP_APOSTA_INSERIR(?,?,?,?,?)}";	
		cstmt = null;
		resultado = null;

		try
		{

			cstmt = Conexao.getConexao().prepareCall(procedure);
			cstmt.registerOutParameter(1, Types.VARCHAR);
			cstmt.setInt(2, apostaVO.getCambistaVO().getSequencial());
			cstmt.setString(3, apostaVO.getNomeApostador());
			cstmt.setDouble(4, apostaVO.getValApostado());
			cstmt.setDouble(5, apostaVO.getValComissao());			
			cstmt.setDouble(6, apostaVO.getValRetornoPossivel());


			cstmt.execute();

			resultado = (String) cstmt.getString(1);
			cstmt.close();

			return resultado;

		}
		catch(Exception ex)
		{
			throw new DAOException(ex);
		}finally{

			procedure = null;
			cstmt = null;
		}

	}


	public String atualizar(ApostaVO apostaVO) throws DAOException {
		procedure = "{ ? = CALL SP_APOSTA_ATUALIZAR(?,?,?,?,?,?,?,?,?,?,?,?,?)}";	
		cstmt = null;
		resultado = null;

		try
		{

			cstmt = Conexao.getConexao().prepareCall(procedure);
			cstmt.registerOutParameter(1, Types.VARCHAR);
			cstmt.setInt(2, apostaVO.getSequencial());
			cstmt.setInt(3, apostaVO.getCambistaVO().getSequencial());
			cstmt.setString(4, apostaVO.getNomeApostador());
			cstmt.setDouble(5, apostaVO.getValApostado());
			cstmt.setDouble(6, apostaVO.getValComissao());			
			cstmt.setDouble(7, apostaVO.getValRetornoPossivel());

			cstmt.setInt(8, apostaVO.getJogoApostadoVO2().getSequencial());
			cstmt.setString(9, apostaVO.getJogoApostadoVO2().getDataJogo());
			cstmt.setString(10, apostaVO.getJogoApostadoVO2().getHoraJogo());
			cstmt.setString(11, apostaVO.getJogoApostadoVO2().getJogoApostado());
						
			cstmt.setString(13, apostaVO.getJogoApostadoVO2().getTipoAposta());
			cstmt.setDouble(14, apostaVO.getJogoApostadoVO2().getValTaxa());

			cstmt.execute();

			resultado = (String) cstmt.getString(1);
			cstmt.close();

			return resultado;

		}
		catch(Exception ex)
		{
			throw new DAOException(ex);
		}finally{

			procedure = null;
			cstmt = null;
		}

	}

	public String remover(ApostaVO apostaVO) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<ApostaVO> pesquisarPorSeqUsuario(ApostaVO apostaVO)
			throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String salvarJogo(ApostaVO apostaVO) throws DAOException {
		procedure = "{ ? = CALL SP_APOSTA_JOGO_APOSTADO_INSERIR(?,?,?,?,?)}";	
		cstmt = null;
		resultado = null;

		try
		{

			cstmt = Conexao.getConexao().prepareCall(procedure);
			cstmt.registerOutParameter(1, Types.VARCHAR);
			cstmt.setString(2, apostaVO.getJogoApostadoVO2().getDataJogo());
			cstmt.setString(3, apostaVO.getJogoApostadoVO2().getHoraJogo());
			cstmt.setInt(4, apostaVO.getJogoApostadoVO2().getSeq());						
			cstmt.setString(5, apostaVO.getJogoApostadoVO2().getTipoAposta());
			cstmt.setDouble(6, apostaVO.getJogoApostadoVO2().getValTaxa());

			cstmt.execute();

			resultado = (String) cstmt.getString(1);
			cstmt.close();

			return resultado;

		}
		catch(Exception ex)
		{
			throw new DAOException(ex);
		}finally{

			procedure = null;
			cstmt = null;
		}
	}

}

package br.com.systemGames.jogo.dao.impl;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import br.com.systemGames.database.Conexao;
import br.com.systemGames.excecao.BOException;
import br.com.systemGames.excecao.DAOException;
import br.com.systemGames.jogo.dao.ILimiteApostaDAO;
import br.com.systemGames.jogo.model.JogoVO;
import br.com.systemGames.jogo.model.LimiteApostaVO;
import br.com.systemGames.util.VerificadorValorObjeto;

public class LimiteApostaDAO implements ILimiteApostaDAO {

	private CallableStatement cstmt;
	private String resultado;
	private String procedure;


	public String inserir(JogoVO jogoVO) throws DAOException {

		procedure = "{ ? = CALL SP_LIMITE_APOSTA_INSERIR(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		cstmt = null;
		resultado = null;

		try
		{

			cstmt = Conexao.getConexao().prepareCall(procedure);
			cstmt.registerOutParameter(1, Types.VARCHAR);
			cstmt.setInt(2, VerificadorValorObjeto.retornaIntValorObjetoOuZero(jogoVO.getSequencial()));
			cstmt.setDouble(3, VerificadorValorObjeto.retornaDoubleValorObjetoOuZero(jogoVO.getLimiteApostaVO().getCasa()));
			cstmt.setDouble(4, VerificadorValorObjeto.retornaDoubleValorObjetoOuZero(jogoVO.getLimiteApostaVO().getEmpate()));
			cstmt.setDouble(5, VerificadorValorObjeto.retornaDoubleValorObjetoOuZero(jogoVO.getLimiteApostaVO().getFora()));
			cstmt.setDouble(6, VerificadorValorObjeto.retornaDoubleValorObjetoOuZero(jogoVO.getLimiteApostaVO().getGolEMeio()));
			cstmt.setDouble(7, VerificadorValorObjeto.retornaDoubleValorObjetoOuZero(jogoVO.getLimiteApostaVO().getDuplaChance()));
			cstmt.setDouble(8, VerificadorValorObjeto.retornaDoubleValorObjetoOuZero(jogoVO.getLimiteApostaVO().getAmbos()));
			
			cstmt.setDouble(9, VerificadorValorObjeto.retornaDoubleValorObjetoOuZero(jogoVO.getLimiteApostaVO().getLimiteCasa()));
			cstmt.setDouble(10, VerificadorValorObjeto.retornaDoubleValorObjetoOuZero(jogoVO.getLimiteApostaVO().getLimiteEmpate()));
			cstmt.setDouble(11, VerificadorValorObjeto.retornaDoubleValorObjetoOuZero(jogoVO.getLimiteApostaVO().getLimiteFora()));
			cstmt.setDouble(12, VerificadorValorObjeto.retornaDoubleValorObjetoOuZero(jogoVO.getLimiteApostaVO().getLimiteGolEMeio()));
			cstmt.setDouble(13, VerificadorValorObjeto.retornaDoubleValorObjetoOuZero(jogoVO.getLimiteApostaVO().getLimiteDuplaChance()));
			cstmt.setDouble(14, VerificadorValorObjeto.retornaDoubleValorObjetoOuZero(jogoVO.getLimiteApostaVO().getLimiteIndividual()));
			cstmt.setDouble(15, VerificadorValorObjeto.retornaDoubleValorObjetoOuZero(jogoVO.getLimiteApostaVO().getLimiteAmbos()));

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

		procedure = "{ ? = CALL SP_LIMITE_APOSTA_ATUALIZAR(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		cstmt = null;
		resultado = null;

		System.out.println(jogoVO.getSequencial());
		
		try
		{

			cstmt = Conexao.getConexao().prepareCall(procedure);
			cstmt.registerOutParameter(1, Types.VARCHAR);
			cstmt.setInt(2, VerificadorValorObjeto.retornaIntValorObjetoOuZero(jogoVO.getSequencial()));
			cstmt.setDouble(3, VerificadorValorObjeto.retornaDoubleValorObjetoOuZero(jogoVO.getLimiteApostaVO().getCasa()));
			cstmt.setDouble(4, VerificadorValorObjeto.retornaDoubleValorObjetoOuZero(jogoVO.getLimiteApostaVO().getEmpate()));
			cstmt.setDouble(5, VerificadorValorObjeto.retornaDoubleValorObjetoOuZero(jogoVO.getLimiteApostaVO().getFora()));
			cstmt.setDouble(6, VerificadorValorObjeto.retornaDoubleValorObjetoOuZero(jogoVO.getLimiteApostaVO().getGolEMeio()));
			cstmt.setDouble(7, VerificadorValorObjeto.retornaDoubleValorObjetoOuZero(jogoVO.getLimiteApostaVO().getDuplaChance()));
			cstmt.setDouble(8, VerificadorValorObjeto.retornaDoubleValorObjetoOuZero(jogoVO.getLimiteApostaVO().getAmbos()));
			cstmt.setDouble(9, VerificadorValorObjeto.retornaDoubleValorObjetoOuZero(jogoVO.getLimiteApostaVO().getLimiteCasa()));
			cstmt.setDouble(10, VerificadorValorObjeto.retornaDoubleValorObjetoOuZero(jogoVO.getLimiteApostaVO().getLimiteEmpate()));
			cstmt.setDouble(11, VerificadorValorObjeto.retornaDoubleValorObjetoOuZero(jogoVO.getLimiteApostaVO().getLimiteFora()));
			cstmt.setDouble(12, VerificadorValorObjeto.retornaDoubleValorObjetoOuZero(jogoVO.getLimiteApostaVO().getLimiteGolEMeio()));
			cstmt.setDouble(13, VerificadorValorObjeto.retornaDoubleValorObjetoOuZero(jogoVO.getLimiteApostaVO().getLimiteDuplaChance()));
			cstmt.setDouble(14, VerificadorValorObjeto.retornaDoubleValorObjetoOuZero(jogoVO.getLimiteApostaVO().getLimiteIndividual()));
			cstmt.setDouble(15, VerificadorValorObjeto.retornaDoubleValorObjetoOuZero(jogoVO.getLimiteApostaVO().getLimiteAmbos()));

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

	public String salvar(JogoVO jogoVO) throws DAOException {

		if (jogoVO.getLimiteApostaVO().getSequencial() != null) {

			return atualizar(jogoVO);
		}else {

			return inserir(jogoVO);

		}
	}

	public LimiteApostaVO buscarTodosPorSeqJogo(JogoVO jogoVO)
			throws DAOException {
		procedure = "{? = CALL SP_LIMITE_APOSTA_BUSCAR_POR_SEQ_JOGO(?)}";
		cstmt = null;
		LimiteApostaVO limiteApostaVORetorno = null;

		try
		{
			cstmt = Conexao.getConexao().prepareCall(procedure);
			cstmt.registerOutParameter(1, Types.OTHER);
			cstmt.setInt(2, VerificadorValorObjeto.retornaIntValorObjetoOuZero(jogoVO.getSequencial()));

			cstmt.execute();

			limiteApostaVORetorno = mapearResultSet((ResultSet) cstmt.getObject(1));

			cstmt.close();		

			return limiteApostaVORetorno;
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
	
	
	
	public LimiteApostaVO mapearResultSet(ResultSet rs) throws SQLException, BOException, DAOException{

		LimiteApostaVO limiteApostaVO = null;

		if(rs.next()){

			limiteApostaVO = new LimiteApostaVO();

			limiteApostaVO.setSequencial(rs.getInt("seq_limite_aposta"));
			//limiteApostaVO.getJogoVO().setSequencial(rs.getInt("cod_jogo"));
			limiteApostaVO.setCasa(rs.getDouble("casa"));
			limiteApostaVO.setEmpate(rs.getDouble("empate"));
			limiteApostaVO.setFora(rs.getDouble("fora"));
			limiteApostaVO.setGolEMeio(rs.getDouble("gol_e_meio"));
			limiteApostaVO.setDuplaChance(rs.getDouble("dupla_chance"));
			limiteApostaVO.setAmbos(rs.getDouble("ambos"));
			limiteApostaVO.setLimiteCasa(rs.getDouble("limite_casa"));
			limiteApostaVO.setLimiteEmpate(rs.getDouble("limite_empate"));
			limiteApostaVO.setLimiteFora(rs.getDouble("limite_fora"));
			limiteApostaVO.setLimiteGolEMeio(rs.getDouble("limite_gol_e_meio"));
			limiteApostaVO.setLimiteDuplaChance(rs.getDouble("limite_dupla_chance"));
			limiteApostaVO.setLimiteIndividual(rs.getDouble("limite_individual"));
			limiteApostaVO.setLimiteAmbos(rs.getDouble("limite_ambos"));

		}
		return limiteApostaVO;


	}

}

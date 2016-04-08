package br.com.systemGames.jogo.dao.impl;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import br.com.systemGames.database.Conexao;
import br.com.systemGames.excecao.BOException;
import br.com.systemGames.excecao.DAOException;
import br.com.systemGames.jogo.dao.ILimiteApostaDAO;
import br.com.systemGames.jogo.model.LimiteApostaVO;
import br.com.systemGames.util.VerificadorValorObjeto;

public class LimiteApostaDAO implements ILimiteApostaDAO {

	private CallableStatement cstmt;
	private String resultado;
	private String procedure;


	public String inserir(LimiteApostaVO limiteApostaVO) throws DAOException {

		procedure = "{ ? = CALL SP_LIMITE_APOSTA_INSERIR(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		cstmt = null;
		resultado = null;

		try
		{

			cstmt = Conexao.getConexao().prepareCall(procedure);
			cstmt.registerOutParameter(1, Types.VARCHAR);
			cstmt.setInt(2, VerificadorValorObjeto.retornaIntValorObjetoOuZero(limiteApostaVO.getJogoVO().getSequencial()));
			cstmt.setDouble(3, VerificadorValorObjeto.retornaDoubleValorObjetoOuZero(limiteApostaVO.getCasa()));
			cstmt.setDouble(4, VerificadorValorObjeto.retornaDoubleValorObjetoOuZero(limiteApostaVO.getEmpate()));
			cstmt.setDouble(5, VerificadorValorObjeto.retornaDoubleValorObjetoOuZero(limiteApostaVO.getFora()));
			cstmt.setDouble(6, VerificadorValorObjeto.retornaDoubleValorObjetoOuZero(limiteApostaVO.getGolEMeio()));
			cstmt.setDouble(7, VerificadorValorObjeto.retornaDoubleValorObjetoOuZero(limiteApostaVO.getDuplaChance()));
			cstmt.setDouble(8, VerificadorValorObjeto.retornaDoubleValorObjetoOuZero(limiteApostaVO.getAmbos()));

			cstmt.setDouble(9, VerificadorValorObjeto.retornaDoubleValorObjetoOuZero(limiteApostaVO.getLimiteCasa()));
			cstmt.setDouble(10, VerificadorValorObjeto.retornaDoubleValorObjetoOuZero(limiteApostaVO.getLimiteEmpate()));
			cstmt.setDouble(11, VerificadorValorObjeto.retornaDoubleValorObjetoOuZero(limiteApostaVO.getLimiteFora()));
			cstmt.setDouble(12, VerificadorValorObjeto.retornaDoubleValorObjetoOuZero(limiteApostaVO.getLimiteGolEMeio()));
			cstmt.setDouble(13, VerificadorValorObjeto.retornaDoubleValorObjetoOuZero(limiteApostaVO.getLimiteDuplaChance()));
			cstmt.setDouble(14, VerificadorValorObjeto.retornaDoubleValorObjetoOuZero(limiteApostaVO.getLimiteIndividual()));
			cstmt.setDouble(15, VerificadorValorObjeto.retornaDoubleValorObjetoOuZero(limiteApostaVO.getLimiteAmbos()));
			cstmt.setInt(16, VerificadorValorObjeto.retornaIntValorObjetoOuZero(limiteApostaVO.getUsuarioVO().getSequencial()));

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


	public String atualizar(LimiteApostaVO limiteApostaVO) throws DAOException {

		procedure = "{ ? = CALL SP_LIMITE_APOSTA_ATUALIZAR(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		cstmt = null;
		resultado = null;

		System.out.println(limiteApostaVO.getSequencial());

		try
		{

			cstmt = Conexao.getConexao().prepareCall(procedure);
			cstmt.registerOutParameter(1, Types.VARCHAR);
			cstmt.setInt(2, VerificadorValorObjeto.retornaIntValorObjetoOuZero(limiteApostaVO.getSequencial()));
			cstmt.setDouble(3, VerificadorValorObjeto.retornaDoubleValorObjetoOuZero(limiteApostaVO.getCasa()));
			cstmt.setDouble(4, VerificadorValorObjeto.retornaDoubleValorObjetoOuZero(limiteApostaVO.getEmpate()));
			cstmt.setDouble(5, VerificadorValorObjeto.retornaDoubleValorObjetoOuZero(limiteApostaVO.getFora()));
			cstmt.setDouble(6, VerificadorValorObjeto.retornaDoubleValorObjetoOuZero(limiteApostaVO.getGolEMeio()));
			cstmt.setDouble(7, VerificadorValorObjeto.retornaDoubleValorObjetoOuZero(limiteApostaVO.getDuplaChance()));
			cstmt.setDouble(8, VerificadorValorObjeto.retornaDoubleValorObjetoOuZero(limiteApostaVO.getAmbos()));
			cstmt.setDouble(9, VerificadorValorObjeto.retornaDoubleValorObjetoOuZero(limiteApostaVO.getLimiteCasa()));
			cstmt.setDouble(10, VerificadorValorObjeto.retornaDoubleValorObjetoOuZero(limiteApostaVO.getLimiteEmpate()));
			cstmt.setDouble(11, VerificadorValorObjeto.retornaDoubleValorObjetoOuZero(limiteApostaVO.getLimiteFora()));
			cstmt.setDouble(12, VerificadorValorObjeto.retornaDoubleValorObjetoOuZero(limiteApostaVO.getLimiteGolEMeio()));
			cstmt.setDouble(13, VerificadorValorObjeto.retornaDoubleValorObjetoOuZero(limiteApostaVO.getLimiteDuplaChance()));
			cstmt.setDouble(14, VerificadorValorObjeto.retornaDoubleValorObjetoOuZero(limiteApostaVO.getLimiteIndividual()));
			cstmt.setDouble(15, VerificadorValorObjeto.retornaDoubleValorObjetoOuZero(limiteApostaVO.getLimiteAmbos()));

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

	public String salvar(LimiteApostaVO limiteApostaVO) throws DAOException {

		if (limiteApostaVO.getSequencial() != null) {

			System.out.println("entrou no atualizar");

			return atualizar(limiteApostaVO);
		}else {

			System.out.println("entrou no atualizar");

			return inserir(limiteApostaVO);

		}
	}

	public LimiteApostaVO buscarTodosPorSeqJogoEUsuario(LimiteApostaVO limiteApostaVO)
			throws DAOException {
		procedure = "{? = CALL SP_LIMITE_APOSTA_BUSCAR_POR_SEQ_JOGO_E_USU(?,?)}";
		cstmt = null;
		LimiteApostaVO limiteApostaVORetorno = null;

		try
		{
			cstmt = Conexao.getConexao().prepareCall(procedure);
			cstmt.registerOutParameter(1, Types.OTHER);
			cstmt.setInt(2, VerificadorValorObjeto.retornaIntValorObjetoOuZero(limiteApostaVO.getJogoVO().getSequencial()));
			cstmt.setInt(3, VerificadorValorObjeto.retornaIntValorObjetoOuZero(limiteApostaVO.getUsuarioVO().getSequencial()));

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


	@Override
	public ArrayList<LimiteApostaVO> buscarTodosPorSeqJogo(
			LimiteApostaVO limiteApostaVO) throws DAOException {
		procedure = "{ ? = CALL SP_LIMITE_APOSTA_BUSCAR_POR_SEQ_JOGO(?)}";
		ArrayList<LimiteApostaVO> lista = new ArrayList<LimiteApostaVO>();

		try
		{

			cstmt = Conexao.getConexao().prepareCall(procedure);
			cstmt.registerOutParameter(1, Types.OTHER);	
			cstmt.setInt(2, VerificadorValorObjeto.retornaIntValorObjetoOuZero(limiteApostaVO.getJogoVO().getSequencial()));
			cstmt.execute();

			lista = mapearResultSetTodos((ResultSet) cstmt.getObject(1));
			cstmt.close();

			return lista;

		}

		catch(Exception ex)
		{

			throw new DAOException(ex);

		}finally{

			procedure = null;
			cstmt = null;			

		}		

	}


	public ArrayList<LimiteApostaVO> mapearResultSetTodos(ResultSet rs) throws SQLException{

		ArrayList<LimiteApostaVO> lista = new ArrayList<LimiteApostaVO>();

		while(rs.next()){

			LimiteApostaVO limiteApostaVO = new LimiteApostaVO();

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
			
			limiteApostaVO.getUsuarioVO().setSequencial(rs.getInt("seq_usuario"));
			limiteApostaVO.getUsuarioVO().setNome(rs.getString("nome_usuario"));
			
			limiteApostaVO.getJogoVO().setSequencial(rs.getInt("cod_jogo"));

			lista.add(limiteApostaVO);

		}

		return lista;

	}


	@Override
	public ArrayList<LimiteApostaVO> buscarPorSeqUsuario(
			LimiteApostaVO limiteApostaVO) throws DAOException {
		procedure = "{ ? = CALL SP_LIMITE_APOSTA_POR_SEQ_USU(?)}";
		ArrayList<LimiteApostaVO> lista = new ArrayList<LimiteApostaVO>();

		try
		{

			cstmt = Conexao.getConexao().prepareCall(procedure);
			cstmt.registerOutParameter(1, Types.OTHER);	
			cstmt.setInt(2, VerificadorValorObjeto.retornaIntValorObjetoOuZero(limiteApostaVO.getUsuarioVO().getSequencial()));
			cstmt.execute();

			lista = mapearResultSetTodosPorSeqUsuario((ResultSet) cstmt.getObject(1));
			cstmt.close();

			return lista;

		}

		catch(Exception ex)
		{

			throw new DAOException(ex);

		}finally{

			procedure = null;
			cstmt = null;			

		}		
	}
	
	
	public ArrayList<LimiteApostaVO> mapearResultSetTodosPorSeqUsuario(ResultSet rs) throws SQLException{

		ArrayList<LimiteApostaVO> lista = new ArrayList<LimiteApostaVO>();

		while(rs.next()){

			LimiteApostaVO limiteApostaVO = new LimiteApostaVO();

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
			
			limiteApostaVO.getUsuarioVO().setSequencial(rs.getInt("cod_usuario"));
			
			
			limiteApostaVO.getJogoVO().setSequencial(rs.getInt("seq_jogo"));
			limiteApostaVO.getJogoVO().setJogo(rs.getString("jogo"));
			limiteApostaVO.getJogoVO().setHoraInicialJogo(rs.getString("hora_inicial_jogo"));
			limiteApostaVO.getJogoVO().setDataJogo(rs.getDate("data_jogo"));

			limiteApostaVO.getJogoVO().getCampeonatoVO().setSequencial(rs.getInt("cod_campeonato"));
			limiteApostaVO.getJogoVO().getCampeonatoVO().setNome(rs.getString("nome_campeonato"));

			limiteApostaVO.getJogoVO().getEsporteVO().setSequencial(rs.getInt("cod_esporte"));
			limiteApostaVO.getJogoVO().getEsporteVO().setNome(rs.getString("nome_esporte"));
			
			limiteApostaVO.getJogoVO().setSequencial(rs.getInt("cod_jogo"));

			lista.add(limiteApostaVO);

		}

		return lista;

	}

}

package br.com.systemGames.jogo.dao.impl;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import br.com.systemGames.database.Conexao;
import br.com.systemGames.excecao.BOException;
import br.com.systemGames.excecao.DAOException;
import br.com.systemGames.jogo.dao.IJogoDAO;
import br.com.systemGames.jogo.model.JogoVO;
import br.com.systemGames.util.VerificadorValorObjeto;

public class JogoDAO implements IJogoDAO {

	private CallableStatement cstmt;
	private String resultado;
	private String procedure;


	public String inserir(JogoVO jogoVO) throws DAOException {

		procedure = "{ ? = CALL SP_JOGO_INSERIR(?,?,?,?,?)}";
		cstmt = null;
		resultado = null;

		try
		{

			cstmt = Conexao.getConexao().prepareCall(procedure);
			cstmt.registerOutParameter(1, Types.VARCHAR);
			cstmt.setString(2, VerificadorValorObjeto.retornaStringValorObjetoOuNull(jogoVO.getJogo()));
			cstmt.setInt(3, VerificadorValorObjeto.retornaIntValorObjetoOuZero(jogoVO.getCampeonatoVO().getSequencial()));
			cstmt.setInt(4, VerificadorValorObjeto.retornaIntValorObjetoOuZero(jogoVO.getEsporteVO().getSequencial()));
			cstmt.setDate(5, VerificadorValorObjeto.retornaSQLDateTratandoFusoHorarioBrasileiro(jogoVO.getDataJogo()));
			cstmt.setString(6, VerificadorValorObjeto.retornaStringValorObjetoOuNullOutVazio(jogoVO.getHoraInicialJogo()));

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

		procedure = "{ ? = CALL SP_JOGO_ATUALIZAR(?,?,?,?,?,?)}";
		cstmt = null;
		resultado = null;

		try
		{

			cstmt = Conexao.getConexao().prepareCall(procedure);
			cstmt.registerOutParameter(1, Types.VARCHAR);
			cstmt.setString(2, VerificadorValorObjeto.retornaStringValorObjetoOuNull(jogoVO.getJogo()));
			cstmt.setInt(3, VerificadorValorObjeto.retornaIntValorObjetoOuZero(jogoVO.getCampeonatoVO().getSequencial()));
			cstmt.setInt(4, VerificadorValorObjeto.retornaIntValorObjetoOuZero(jogoVO.getEsporteVO().getSequencial()));
			cstmt.setDate(5, VerificadorValorObjeto.retornaSQLDateTratandoFusoHorarioBrasileiro(jogoVO.getDataJogo()));
			cstmt.setString(6, VerificadorValorObjeto.retornaStringValorObjetoOuNullOutVazio(jogoVO.getHoraInicialJogo()));
			cstmt.setInt(7, VerificadorValorObjeto.retornaIntValorObjetoOuZero(jogoVO.getSequencial()));

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

		if (jogoVO.getSequencial() != null) {

			return atualizar(jogoVO);

		} else {

			return inserir(jogoVO);

		}

	}

	public String remover(JogoVO jogoVO) throws DAOException {
		procedure = "{ ? = CALL SP_JOGO_REMOVER(?)}";
		cstmt = null;
		resultado = null;

		try
		{

			cstmt = Conexao.getConexao().prepareCall(procedure);
			cstmt.registerOutParameter(1, Types.VARCHAR);
			cstmt.setInt(2, VerificadorValorObjeto.retornaIntValorObjetoOuZero(jogoVO.getSequencial()));

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

	public ArrayList<JogoVO> listarTodos() throws DAOException {
		procedure = "{? = CALL SP_JOGO_BUSCAR_TODOS()}";
		cstmt = null;
		ArrayList<JogoVO> lista = new ArrayList<JogoVO>();

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

	public JogoVO retornarUltimoSequencial() throws DAOException {
		procedure = "{? = CALL SP_JOGO_RETORNAR_ULTIMO_SEQUENCIAL()}";
		cstmt = null;
		JogoVO jogoRetorno = null;

		try
		{
			cstmt = Conexao.getConexao().prepareCall(procedure);
			cstmt.registerOutParameter(1, Types.OTHER);

			cstmt.execute();

			jogoRetorno = mapearResultSetUltimoSequencial((ResultSet) cstmt.getObject(1));

			cstmt.close();		

			return jogoRetorno;
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
	
	

	public ArrayList<JogoVO> listarTodosBasico() throws DAOException {
		procedure = "{? = CALL SP_JOGO_BUSCAR_TODOS_BASICO()}";
		cstmt = null;
		ArrayList<JogoVO> lista = new ArrayList<JogoVO>();

		try
		{
			cstmt = Conexao.getConexao().prepareCall(procedure);
			cstmt.registerOutParameter(1, Types.OTHER);


			cstmt.execute();

			lista = mapearResultSetBasico((ResultSet) cstmt.getObject(1));

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



	public JogoVO mapearResultSetUltimoSequencial(ResultSet rs) throws SQLException, BOException, DAOException{

		JogoVO jogoVO = null;

		if(rs.next()){

			jogoVO = new JogoVO();

			jogoVO.setSequencial(rs.getInt("seq_jogo"));

		}
		return jogoVO;


	}


	public ArrayList<JogoVO> mapearResultSet(ResultSet rs) throws SQLException, BOException, DAOException{

		ArrayList<JogoVO> lista = new ArrayList<JogoVO>();


		while(rs.next()){

			JogoVO jogoVO = new JogoVO();

			jogoVO.setSequencial(rs.getInt("seq_jogo"));
			jogoVO.setJogo(rs.getString("jogo"));
			jogoVO.setHoraInicialJogo(rs.getString("hora_inicial_jogo"));
			jogoVO.setDataJogo(rs.getDate("data_jogo"));

			jogoVO.getCampeonatoVO().setSequencial(rs.getInt("cod_campeonato"));
			jogoVO.getCampeonatoVO().setNome(rs.getString("nome_campeonato"));

			jogoVO.getEsporteVO().setSequencial(rs.getInt("cod_esporte"));
			jogoVO.getEsporteVO().setNome(rs.getString("nome_esporte"));

			jogoVO.getConfiguracaoJogoVO().setSequencial(rs.getInt("seq_configuracao_jogo"));
			jogoVO.getConfiguracaoJogoVO().setFinalizarAutomaticamente(rs.getBoolean("finalizar_automaticamente"));
			jogoVO.getConfiguracaoJogoVO().setJogoFinalizado(rs.getBoolean("jogo_finalizado"));

			jogoVO.getLimiteApostaVO().setSequencial(rs.getInt("seq_limite_aposta"));
			jogoVO.getLimiteApostaVO().setCasa(rs.getDouble("casa"));
			jogoVO.getLimiteApostaVO().setEmpate(rs.getDouble("empate"));
			jogoVO.getLimiteApostaVO().setFora(rs.getDouble("fora"));
			jogoVO.getLimiteApostaVO().setGolEMeio(rs.getDouble("gol_e_meio"));
			jogoVO.getLimiteApostaVO().setDuplaChance(rs.getDouble("dupla_chance"));
			jogoVO.getLimiteApostaVO().setAmbos(rs.getDouble("ambos"));
			jogoVO.getLimiteApostaVO().setLimiteCasa(rs.getDouble("limite_casa"));
			jogoVO.getLimiteApostaVO().setLimiteEmpate(rs.getDouble("limite_empate"));
			jogoVO.getLimiteApostaVO().setLimiteFora(rs.getDouble("limite_fora"));
			jogoVO.getLimiteApostaVO().setLimiteGolEMeio(rs.getDouble("limite_gol_e_meio"));
			jogoVO.getLimiteApostaVO().setLimiteDuplaChance(rs.getDouble("limite_dupla_chance"));
			jogoVO.getLimiteApostaVO().setLimiteIndividual(rs.getDouble("limite_individual"));
			jogoVO.getLimiteApostaVO().setLimiteAmbos(rs.getDouble("limite_ambos"));

			jogoVO.getResultadoJogoVO().setSequencial(rs.getInt("seq_resultado_jogo"));
			jogoVO.getResultadoJogoVO().setResultadoCasa(rs.getInt("resultado_jogo_casa"));
			jogoVO.getResultadoJogoVO().setResultadoFora(rs.getInt("resultado_jogo_fora"));

			lista.add(jogoVO);
		}
		return lista;
	}

	

	public ArrayList<JogoVO> mapearResultSetBasico(ResultSet rs) throws SQLException, BOException, DAOException{

		ArrayList<JogoVO> lista = new ArrayList<JogoVO>();

		while(rs.next()){

			JogoVO jogoVO = new JogoVO();

			jogoVO.setSequencial(rs.getInt("seq_jogo"));
			jogoVO.setJogo(rs.getString("jogo"));
			jogoVO.setHoraInicialJogo(rs.getString("hora_inicial_jogo"));
			jogoVO.setDataJogo(rs.getDate("data_jogo"));

			lista.add(jogoVO);
		}
		return lista;
	}


	public ArrayList<JogoVO> listarPorParams(JogoVO jogoVO) throws DAOException {
		procedure = "{? = CALL SP_JOGO_BUSCAR_POR_PARAMS(?)}";
		cstmt = null;
		ArrayList<JogoVO> lista = new ArrayList<JogoVO>();

		try
		{
			cstmt = Conexao.getConexao().prepareCall(procedure);
			cstmt.registerOutParameter(1, Types.OTHER);
			cstmt.setInt(2, VerificadorValorObjeto.retornaIntValorObjetoOuZero(jogoVO.getCampeonatoVO().getSequencial()));
			
			cstmt.execute();

			lista = mapearResultSetJogo((ResultSet) cstmt.getObject(1));

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
	
	public ArrayList<JogoVO> mapearResultSetJogo(ResultSet rs) throws SQLException, BOException, DAOException{

		ArrayList<JogoVO> lista = new ArrayList<JogoVO>();


		while(rs.next()){

			JogoVO jogoVO = new JogoVO();

			jogoVO.setSequencial(rs.getInt("seq_jogo"));
			jogoVO.setJogo(rs.getString("jogo"));
			jogoVO.setHoraInicialJogo(rs.getString("hora_inicial_jogo"));
			jogoVO.setDataJogo(rs.getDate("data_jogo"));

			jogoVO.getCampeonatoVO().setSequencial(rs.getInt("cod_campeonato"));
			jogoVO.getCampeonatoVO().setNome(rs.getString("nome_campeonato"));
			
			jogoVO.getConfiguracaoJogoVO().setSequencial(rs.getInt("seq_configuracao_jogo"));
			jogoVO.getConfiguracaoJogoVO().setFinalizarAutomaticamente(rs.getBoolean("finalizar_automaticamente"));
			jogoVO.getConfiguracaoJogoVO().setJogoFinalizado(rs.getBoolean("jogo_finalizado"));

			jogoVO.getLimiteApostaVO().setSequencial(rs.getInt("seq_limite_aposta"));
			jogoVO.getLimiteApostaVO().setCasa(rs.getDouble("casa"));
			jogoVO.getLimiteApostaVO().setEmpate(rs.getDouble("empate"));
			jogoVO.getLimiteApostaVO().setFora(rs.getDouble("fora"));
			jogoVO.getLimiteApostaVO().setGolEMeio(rs.getDouble("gol_e_meio"));
			jogoVO.getLimiteApostaVO().setDuplaChance(rs.getDouble("dupla_chance"));
			jogoVO.getLimiteApostaVO().setAmbos(rs.getDouble("ambos"));
			jogoVO.getLimiteApostaVO().setLimiteCasa(rs.getDouble("limite_casa"));
			jogoVO.getLimiteApostaVO().setLimiteEmpate(rs.getDouble("limite_empate"));
			jogoVO.getLimiteApostaVO().setLimiteFora(rs.getDouble("limite_fora"));
			jogoVO.getLimiteApostaVO().setLimiteGolEMeio(rs.getDouble("limite_gol_e_meio"));
			jogoVO.getLimiteApostaVO().setLimiteDuplaChance(rs.getDouble("limite_dupla_chance"));
			jogoVO.getLimiteApostaVO().setLimiteIndividual(rs.getDouble("limite_individual"));
			jogoVO.getLimiteApostaVO().setLimiteAmbos(rs.getDouble("limite_ambos"));

			
			lista.add(jogoVO);
		}
		return lista;
	}



}

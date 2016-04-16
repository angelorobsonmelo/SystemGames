package br.com.systemGames.aposta.dao.impl;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import br.com.systemGames.aposta.dao.IApostaDAO;
import br.com.systemGames.aposta.model.ApostaVO;
import br.com.systemGames.database.Conexao;
import br.com.systemGames.excecao.DAOException;
import br.com.systemGames.util.DataUtil;
import br.com.systemGames.util.VerificadorValorObjeto;

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
		procedure = "{ ? = CALL SP_APOSTA_INSERIR(?,?,?,?,?,?,?)}";	
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
			cstmt.setInt(7, apostaVO.getQtdJogos());
			cstmt.setInt(8, apostaVO.getCodigo());


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
	
	
		public ArrayList<ApostaVO> consultarSomaApostaPorParametros(
			ApostaVO apostaVO) throws DAOException {
		String consulta = "{? = CALL sp_aposta_soma_valor_apostado_por_params(?,?)}";
        CallableStatement cstmt = null;
        ArrayList<ApostaVO> listaAposta = new ArrayList<ApostaVO>();
               
        try
        {        	
            cstmt = Conexao.getConexao().prepareCall(consulta);
            cstmt.registerOutParameter(1, Types.OTHER);
            cstmt.setInt(2, VerificadorValorObjeto.retornaIntValorObjetoOuZero(apostaVO.getCambistaVO().getSequencial()));
            cstmt.setDate(3, new java.sql.Date(VerificadorValorObjeto.retornaLongValorObjetoOuZero(apostaVO.getDataInicial().getTime())));
            cstmt.execute();
			
            listaAposta = mapearResultSetSoma((ResultSet) cstmt.getObject(1));
			
			cstmt.close();	
			
	        return listaAposta;
        }
        catch(Exception ex)
        {
        	throw new DAOException(ex);
        }
        finally{        	
        	/*Indicar ao Garbage Collection do Java que as variáveis 
			* podem ser esvaziadas do Coletor de Lixo
			*/        	
        	procedure = null;
        	cstmt = null;
        }
	}
	
	
	private ArrayList<ApostaVO> mapearResultSetSoma(ResultSet rs) throws SQLException{
	
	ArrayList<ApostaVO> lista = new ArrayList<ApostaVO>();
    
	while(rs.next()){
	
		ApostaVO apostaVO = new ApostaVO();
		apostaVO.setValApostado(rs.getDouble("LIMITE_ATUAL"));
		apostaVO.getCambistaVO().getConfiguracaoCambistaVO().setLimiteMaximoVendaDiario(rs.getDouble("limite"));
		lista.add(apostaVO);
	}
	return lista;
}

	public ArrayList<ApostaVO> consultarApostaPorParametros(
			ApostaVO apostaVO) throws DAOException {
		String consulta = "{? = CALL SP_APOSTA_BUSCAR_POR_PARAMS(?,?,?,?,?)}";
		CallableStatement cstmt = null;
		ArrayList<ApostaVO> listaAposta = new ArrayList<ApostaVO>();

		try
		{        	
			cstmt = Conexao.getConexao().prepareCall(consulta);
			cstmt.registerOutParameter(1, Types.OTHER);
			cstmt.setInt(2, VerificadorValorObjeto.retornaIntValorObjetoOuZero(apostaVO.getCambistaVO().getSequencial()));
			cstmt.setDate(3, new java.sql.Date(VerificadorValorObjeto.retornaLongValorObjetoOuZero(apostaVO.getDataInicial().getTime())));            
			cstmt.setDate(4, VerificadorValorObjeto.retornaSQLDateValorObjetoOuNull(apostaVO.getDataFinal()));
			cstmt.setInt(5, VerificadorValorObjeto.retornaIntValorObjetoOuZero(apostaVO.getSequencial()));
			cstmt.setInt(6, VerificadorValorObjeto.retornaIntValorObjetoOuZero(apostaVO.getCambistaVO().getUsuarioVO().getSequencial()));
			cstmt.execute();

			listaAposta = mapearResultSet((ResultSet) cstmt.getObject(1));

			cstmt.close();	

			return listaAposta;
		}
		catch(Exception ex)
		{
			throw new DAOException(ex);
		}
		finally{        	
			/*Indicar ao Garbage Collection do Java que as variáveis 
			 * podem ser esvaziadas do Coletor de Lixo
			 */        	
			 procedure = null;
			 cstmt = null;
		}
	}

	private ArrayList<ApostaVO> mapearResultSet(ResultSet rs) throws SQLException{

		ArrayList<ApostaVO> lista = new ArrayList<ApostaVO>();

		while(rs.next()){

			ApostaVO apostaVO = new ApostaVO();
			apostaVO.setSequencial(rs.getInt("seq_aposta"));
			apostaVO.setDthInclusao(rs.getString("dth_inclusao_aposta"));
			apostaVO.setValApostado(rs.getDouble("val_apostado"));
			apostaVO.setValComissao(rs.getDouble("val_comissao"));
			apostaVO.setValRetornoPossivel(rs.getDouble("val_retorno_possivel"));
			apostaVO.setNomeApostador(rs.getString("nome_apostador"));
			apostaVO.setQtdJogos(rs.getInt("qtd_jogos"));
			apostaVO.setCodigo(rs.getInt("codigo"));
			apostaVO.getCambistaVO().setNome(rs.getString("nome_cambista"));
			apostaVO.getCambistaVO().setApelido(rs.getString("apelido_cambista"));

			lista.add(apostaVO);
		}
		return lista;
	}


	public ArrayList<ApostaVO> apostaPorSequencial(ApostaVO apostaVO)
			throws DAOException {
		String consulta = "{? = CALL sp_aposta_buscar_por_sequencial(?)}";
		CallableStatement cstmt = null;
		ArrayList<ApostaVO> listaAposta = new ArrayList<ApostaVO>();

		try
		{        	
			cstmt = Conexao.getConexao().prepareCall(consulta);
			cstmt.registerOutParameter(1, Types.OTHER);
			cstmt.setInt(2, VerificadorValorObjeto.retornaIntValorObjetoOuZero(apostaVO.getSequencial()));
			cstmt.execute();

			listaAposta = mapearResultSetApostaPorSequencial((ResultSet) cstmt.getObject(1));

			cstmt.close();	

			return listaAposta;
		}
		catch(Exception ex)
		{
			throw new DAOException(ex);
		}
		finally{        	
			/*Indicar ao Garbage Collection do Java que as variáveis 
			 * podem ser esvaziadas do Coletor de Lixo
			 */        	
			procedure = null;
			cstmt = null;
		}
	}

	private ArrayList<ApostaVO> mapearResultSetApostaPorSequencial(ResultSet rs) throws SQLException{

		ArrayList<ApostaVO> lista = new ArrayList<ApostaVO>();

		while(rs.next()){

			ApostaVO apostaVO = new ApostaVO();		
			apostaVO.setSequencial(rs.getInt("seq_aposta"));
			apostaVO.getCambistaVO().setSequencial(rs.getInt("seq_cambista"));
			apostaVO.getJogoApostadoVO2().setSequencial(rs.getInt("seq_jogo_apostado"));
			apostaVO.getJogoApostadoVO2().setDataJogo(rs.getString("data_jogo"));
			apostaVO.getJogoApostadoVO2().setHoraJogo(rs.getString("hora_jogo"));
			apostaVO.getJogoApostadoVO2().setSeq(rs.getInt("cod_jogo"));
			apostaVO.getJogoApostadoVO2().setTipoAposta(rs.getString("tipo_aposta"));
			apostaVO.getJogoApostadoVO2().setValTaxa(rs.getDouble("val_taxa"));
			apostaVO.getJogoApostadoVO2().setCodAposta(rs.getInt("cod_aposta"));
			apostaVO.getJogoApostadoVO2().setJogoApostado(rs.getString("jogo"));

			apostaVO.getConfiguracaoJogoVO().setJogoFinalizado(rs.getBoolean("jogo_finalizado"));

			apostaVO.getResultadoJogoVO().setResultadoCasa(rs.getInt("resultado_jogo_casa"));
			apostaVO.getResultadoJogoVO().setResultadoFora(rs.getInt("resultado_jogo_fora"));


			apostaVO.setDthInclusao(rs.getString("dth_inclusao_aposta"));
			apostaVO.setValApostado(rs.getDouble("val_apostado"));
			apostaVO.setValComissao(rs.getDouble("val_comissao"));
			apostaVO.setValRetornoPossivel(rs.getDouble("val_retorno_possivel"));
			apostaVO.setNomeApostador(rs.getString("nome_apostador"));
			apostaVO.setQtdJogos(rs.getInt("qtd_jogos"));
			apostaVO.setCodigo(rs.getInt("codigo"));



			lista.add(apostaVO);
		}
		return lista;
	}

	
	public String inserirResultadoAposta(ApostaVO apostaVO) throws DAOException {
		procedure = "{ ? = CALL SP_APOSTA_ALTERAR_RESULTADO(?,?)}";	
		cstmt = null;
		resultado = null;

		try
		{

			cstmt = Conexao.getConexao().prepareCall(procedure);
			cstmt.registerOutParameter(1, Types.VARCHAR);
			cstmt.setInt(2, apostaVO.getSequencial());
			cstmt.setString(3, apostaVO.getResultadoAposta());

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

package br.com.systemGames.usuario.cambista.dao.impl;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import br.com.systemGames.database.Conexao;
import br.com.systemGames.excecao.DAOException;
import br.com.systemGames.usuario.cambista.dao.IConfiguracaoCambistaDAO;
import br.com.systemGames.usuario.cambista.model.ConfiguracaoCambistaVO;
import br.com.systemGames.util.VerificadorValorObjeto;

public class ConfiguracaoCambistaDAO implements IConfiguracaoCambistaDAO {
	
	private String procedure;
	private CallableStatement cstmt;
	private String resultado;

	
	public ArrayList<ConfiguracaoCambistaVO> listarTodos(ConfiguracaoCambistaVO configuracaoCambistaVO) throws DAOException {
		procedure = "{ ? = CALL SP_CONFIGURACAO_CAMBISTA_BUSCAR_TODOS()}";
		ArrayList<ConfiguracaoCambistaVO> lista = new ArrayList<ConfiguracaoCambistaVO>();

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
			
			throw new DAOException(ex);
			
		}finally{
			
			procedure = null;
			cstmt = null;			
			
		}		

	}
	
	public ArrayList<ConfiguracaoCambistaVO> mapearResultSet(ResultSet rs) throws SQLException{

		ArrayList<ConfiguracaoCambistaVO> lista = new ArrayList<ConfiguracaoCambistaVO>();

		while(rs.next()){

			ConfiguracaoCambistaVO configuracaoCambistaVO = new ConfiguracaoCambistaVO();	

			configuracaoCambistaVO.setSequencial(rs.getInt("SEQ_CAMBISTA"));
			configuracaoCambistaVO.setLimiteMaximoVendaDiario(rs.getDouble("LIMITE_MAX_VENDA_DIARIO"));
			configuracaoCambistaVO.setLimiteMaximoVendaIndividual(rs.getDouble("LIMITE_MAX_VENDA_INDIVIDUAL"));
			configuracaoCambistaVO.setObservacao(rs.getString("OBSERVACAO"));
			configuracaoCambistaVO.setSequencial(rs.getInt("COD_USUARIO"));
			configuracaoCambistaVO.setComissao1(rs.getInt("COMISSAO1"));
			configuracaoCambistaVO.setComissao2(rs.getInt("COMISSAO2"));
			configuracaoCambistaVO.setComissao3(rs.getInt("COMISSAO3"));
			configuracaoCambistaVO.setSequencial(rs.getInt("SEQ_USUARIO"));
			configuracaoCambistaVO.setNomeUsuario(rs.getString("NOME_USUARIO"));
			configuracaoCambistaVO.setEmail(rs.getString("EMAIL"));
			configuracaoCambistaVO.setLogin(rs.getString("NOM_LOGIN"));
			configuracaoCambistaVO.setSenha(rs.getString("SENHA"));
			configuracaoCambistaVO.setCpf(rs.getString("CPF"));
			configuracaoCambistaVO.setNumeroRg(rs.getString("NUM_RG"));
			configuracaoCambistaVO.setContato(rs.getString("CONTATO"));
			configuracaoCambistaVO.getCodigoTipoUsuario().setSequencial(rs.getInt("COD_TIPO_USUARIO"));
			configuracaoCambistaVO.setEndereco(rs.getString("ENDERECO"));
			configuracaoCambistaVO.setNumeroEndereco(rs.getString("NUM_ENDERECO"));
			configuracaoCambistaVO.setComplemento(rs.getString("COMPLEMENTO"));
			configuracaoCambistaVO.setBairro(rs.getString("BAIRRO"));
			configuracaoCambistaVO.setCidade(rs.getString("CIDADE"));
			configuracaoCambistaVO.setCep(rs.getString("CEP"));
			configuracaoCambistaVO.setUf(rs.getString("UF"));


			lista.add(configuracaoCambistaVO);
			
		}
		
		return lista;
		
	}

	
	public String inserir(ConfiguracaoCambistaVO configuracaoCambistaVO)
			throws DAOException {
		procedure = "{ ? = CALL SP_CONFIGURACAO_CAMBISTA_INSERIR(?,?,?,?,?,?,?)}";	
		cstmt = null;
		resultado = null;

		try
		{

			cstmt = Conexao.getConexao().prepareCall(procedure);
			cstmt.registerOutParameter(1, Types.VARCHAR);
			cstmt.setDouble(2, configuracaoCambistaVO.getLimiteMaximoVendaDiario());
			cstmt.setDouble(3, configuracaoCambistaVO.getLimiteMaximoVendaIndividual());
			cstmt.setString(4, configuracaoCambistaVO.getObservacao());
			cstmt.setInt(5, configuracaoCambistaVO.getSequencial());			
			cstmt.setInt(6, configuracaoCambistaVO.getComissao1());
			cstmt.setInt(7, configuracaoCambistaVO.getComissao2());
			cstmt.setInt(8, configuracaoCambistaVO.getComissao3());
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

	
	public String remover(ConfiguracaoCambistaVO configuracaoCambistaVO)
			throws DAOException {
		procedure = "{ ? = CALL SP_CONFIGURACAO_CAMBISTA_REMOVER(?)}";	
		cstmt = null;
		resultado = null;

		try
		{

			cstmt = Conexao.getConexao().prepareCall(procedure);
			cstmt.registerOutParameter(1, Types.VARCHAR);
			cstmt.setInt(2, configuracaoCambistaVO.getSequencial());			
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

	
	public String alterar(ConfiguracaoCambistaVO configuracaoCambistaVO)
			throws DAOException {
		procedure = "{ ? = CALL SP_CONFIGURACAO_CAMBISTA_ALTERAR(?,?,?,?,?,?,?,?)}";	
		cstmt = null;
		resultado = null;

		try
		{

			cstmt = Conexao.getConexao().prepareCall(procedure);
			cstmt.registerOutParameter(1, Types.VARCHAR);
			cstmt.setInt(2, VerificadorValorObjeto.retornaIntValorObjetoOuZero(configuracaoCambistaVO.getSequencial()));
			cstmt.setDouble(3, configuracaoCambistaVO.getLimiteMaximoVendaDiario());
			cstmt.setDouble(4, configuracaoCambistaVO.getLimiteMaximoVendaIndividual());
			cstmt.setString(5, configuracaoCambistaVO.getObservacao());
			cstmt.setInt(6, configuracaoCambistaVO.getSequencial());
			cstmt.setInt(7, configuracaoCambistaVO.getComissao1());
			cstmt.setInt(8, configuracaoCambistaVO.getComissao2());
			cstmt.setInt(9, configuracaoCambistaVO.getComissao3());
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

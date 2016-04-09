

package br.com.systemGames.usuario.dao.impl;

import java.sql.CallableStatement;
import java.sql.Types;

import br.com.systemGames.database.Conexao;
import br.com.systemGames.excecao.DAOException;
import br.com.systemGames.usuario.dao.IConfiguracaoCambistaDAO;
import br.com.systemGames.usuario.model.CambistaVO;

public class ConfiguracaoCambistaDAO implements IConfiguracaoCambistaDAO {
	
	private String procedure;
	private CallableStatement cstmt;
	private String resultado;

	
//	public ArrayList<ConfiguracaoCambistaVO> listarTodos(ConfiguracaoCambistaVO configuracaoCambistaVO) throws DAOException {
//		procedure = "{ ? = CALL SP_CONFIGURACAO_CAMBISTA_BUSCAR_TODOS()}";
//		ArrayList<ConfiguracaoCambistaVO> lista = new ArrayList<ConfiguracaoCambistaVO>();
//
//		try
//		{
//
//			cstmt = Conexao.getConexao().prepareCall(procedure);
//			cstmt.registerOutParameter(1, Types.OTHER);
//			cstmt.execute();
//
//			lista = mapearResultSet((ResultSet) cstmt.getObject(1));
//			cstmt.close();
//			
//			return lista;
//
//		}
//		
//		catch(Exception ex)
//		{
//			
//			throw new DAOException(ex);
//			
//		}finally{
//			
//			procedure = null;
//			cstmt = null;			
//			
//		}		
//
//	}
//	
//	public ArrayList<ConfiguracaoCambistaVO> mapearResultSet(ResultSet rs) throws SQLException{
//
//		ArrayList<ConfiguracaoCambistaVO> lista = new ArrayList<ConfiguracaoCambistaVO>();
//
//		while(rs.next()){
//
//			ConfiguracaoCambistaVO configuracaoCambistaVO = new ConfiguracaoCambistaVO();	
//
//			/*
//			configuracaoCambistaVO.setSequencial(rs.getInt("SEQ_CAMBISTA"));
//			configuracaoCambistaVO.setLimiteMaximoVendaDiario(rs.getDouble("LIMITE_MAX_VENDA_DIARIO"));
//			configuracaoCambistaVO.setLimiteMaximoVendaIndividual(rs.getDouble("LIMITE_MAX_VENDA_INDIVIDUAL"));
//			configuracaoCambistaVO.setObservacao(rs.getString("OBSERVACAO"));
//			configuracaoCambistaVO.getCodigoUsuario().setUsuariosequencial(rs.getInt("COD_USUARIO"));
//			configuracaoCambistaVO.setComissao1(rs.getInt("COMISSAO1"));
//			configuracaoCambistaVO.setComissao2(rs.getInt("COMISSAO2"));
//			configuracaoCambistaVO.setComissao3(rs.getInt("COMISSAO3"));
//			configuracaoCambistaVO.setUsuariosequencial(rs.getInt("SEQ_USUARIO"));
//			configuracaoCambistaVO.setNomeUsuario(rs.getString("NOME_USUARIO"));
//			configuracaoCambistaVO.setEmail(rs.getString("EMAIL"));
//			configuracaoCambistaVO.setLogin(rs.getString("NOM_LOGIN"));
//			configuracaoCambistaVO.setSenha(rs.getString("SENHA"));
//			configuracaoCambistaVO.setCpf(rs.getString("CPF"));
//			configuracaoCambistaVO.setNumeroRg(rs.getString("NUM_RG"));
//			configuracaoCambistaVO.setContato(rs.getString("CONTATO"));
//			configuracaoCambistaVO.getCodigoTipoUsuario().setSequencial(rs.getInt("COD_TIPO_USUARIO"));
//			configuracaoCambistaVO.setEndereco(rs.getString("ENDERECO"));
//			configuracaoCambistaVO.setNumeroEndereco(rs.getString("NUM_ENDERECO"));
//			configuracaoCambistaVO.setComplemento(rs.getString("COMPLEMENTO"));
//			configuracaoCambistaVO.setBairro(rs.getString("BAIRRO"));
//			configuracaoCambistaVO.setCidade(rs.getString("CIDADE"));
//			configuracaoCambistaVO.setCep(rs.getString("CEP"));
//			configuracaoCambistaVO.setUf(rs.getString("UF"));
//   /*
//
//			lista.add(configuracaoCambistaVO);
//			
//		}
//		
//		return lista;
//		
//	}
//
//	
	public String inserir(CambistaVO cambistaVO)
			throws DAOException {
		procedure = "{ ? = CALL SP_CAMBISTA_INSERIR(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";	
		cstmt = null;
		resultado = null;

		try
		{

			cstmt = Conexao.getConexao().prepareCall(procedure);
			cstmt.registerOutParameter(1, Types.VARCHAR);
			
			cstmt.setString(2, cambistaVO.getNome());
			cstmt.setString(3, cambistaVO.getEmail());
			cstmt.setString(4, cambistaVO.getSenha());
			cstmt.setString(5, cambistaVO.getCpf());			
			cstmt.setString(6, cambistaVO.getNumeroRg());
			cstmt.setString(7, cambistaVO.getContato());
			
			cstmt.setInt(8, cambistaVO.getUsuarioVO().getSequencial());
			
			cstmt.setString(9, cambistaVO.getEndereco());
			cstmt.setString(10, cambistaVO.getNumeroEndereco());
			cstmt.setString(11, cambistaVO.getComplemento());
			cstmt.setString(12, cambistaVO.getBairro());
			cstmt.setString(13, cambistaVO.getCidade());
			cstmt.setString(14, cambistaVO.getCpf());
			cstmt.setString(15, cambistaVO.getUf());
			
			cstmt.setDouble(16, cambistaVO.getConfiguracaoCambistaVO().getLimiteMaximoVendaDiario());
			cstmt.setDouble(17, cambistaVO.getConfiguracaoCambistaVO().getLimiteMaximoVendaIndividual());
			cstmt.setString(18, cambistaVO.getConfiguracaoCambistaVO().getObservacao());
			cstmt.setDouble(19, cambistaVO.getConfiguracaoCambistaVO().getComissao1());
			cstmt.setDouble(20, cambistaVO.getConfiguracaoCambistaVO().getComissao2());
			cstmt.setDouble(21, cambistaVO.getConfiguracaoCambistaVO().getComissao3());

			
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

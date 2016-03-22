package br.com.systemGames.usuario.dao.impl;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import br.com.systemGames.database.Conexao;
import br.com.systemGames.excecao.DAOException;
import br.com.systemGames.usuario.dao.IUsuarioDAO;
import br.com.systemGames.usuario.model.UsuarioVO;

public class UsuarioDAO implements IUsuarioDAO {
	
	private String procedure;
	private CallableStatement cstmt;
	private String resultado;

	
	public ArrayList<?> listarTodos() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}


	public String inserir(UsuarioVO usuarioVO) throws DAOException {
		procedure = "{ ? = CALL SP_USUARIO_INSERIR(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";	
		cstmt = null;
		resultado = null;

		try
		{

			cstmt = Conexao.getConexao().prepareCall(procedure);
			cstmt.registerOutParameter(1, Types.VARCHAR);
			cstmt.setString(2, usuarioVO.getNomeUsuario());
			cstmt.setString(3, usuarioVO.getEmail());
			cstmt.setString(4, usuarioVO.getLogin());
			cstmt.setString(5, usuarioVO.getSenha());
			cstmt.setString(6, usuarioVO.getCpf());
			cstmt.setString(7, usuarioVO.getNumeroRg());
			cstmt.setString(8, usuarioVO.getContato());
			cstmt.setInt(9, usuarioVO.getCodigoTipoUsuario().getSequencial());
			cstmt.setString(10, usuarioVO.getEndereco());
			cstmt.setString(11, usuarioVO.getNumeroEndereco());
			cstmt.setString(12, usuarioVO.getComplemento());
			cstmt.setString(13, usuarioVO.getBairro());
			cstmt.setString(14, usuarioVO.getCidade());
			cstmt.setString(15, usuarioVO.getCep());
			cstmt.setString(16, usuarioVO.getUf());
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


	public String remover(UsuarioVO usuarioVO) throws DAOException {
		procedure = "{ ? = CALL SP_USUARIO_REMOVER(?)}";	
		cstmt = null;
		resultado = null;

		try
		{

			cstmt = Conexao.getConexao().prepareCall(procedure);
			cstmt.registerOutParameter(1, Types.VARCHAR);
			cstmt.setInt(2, usuarioVO.getUsuariosequencial());			
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


	public String alterar(UsuarioVO usuarioVO) throws DAOException {
		procedure = "{ ? = CALL SP_USUARIO_ALTERAR(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";	
		cstmt = null;
		resultado = null;

		try
		{

			cstmt = Conexao.getConexao().prepareCall(procedure);
			cstmt.registerOutParameter(1, Types.VARCHAR);
			cstmt.setString(2, usuarioVO.getNomeUsuario());
			cstmt.setString(3, usuarioVO.getEmail());
			cstmt.setString(4, usuarioVO.getLogin());
			cstmt.setString(5, usuarioVO.getSenha());
			cstmt.setString(6, usuarioVO.getCpf());
			cstmt.setString(7, usuarioVO.getNumeroRg());
			cstmt.setString(8, usuarioVO.getContato());
			cstmt.setInt(9, usuarioVO.getCodigoTipoUsuario().getSequencial());
			cstmt.setString(10, usuarioVO.getEndereco());
			cstmt.setString(11, usuarioVO.getNumeroEndereco());
			cstmt.setString(12, usuarioVO.getComplemento());
			cstmt.setString(13, usuarioVO.getBairro());
			cstmt.setString(14, usuarioVO.getCidade());
			cstmt.setString(15, usuarioVO.getCep());
			cstmt.setString(16, usuarioVO.getUf());
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

	
	public ArrayList<UsuarioVO> consultarPorParams()
			throws DAOException {
		procedure = "{ ? = CALL SP_USUARIO_BUSCAR_TODOS()}";
		ArrayList<UsuarioVO> lista = new ArrayList<UsuarioVO>();
		UsuarioVO usuarioVO = new UsuarioVO();

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
	
	public ArrayList<UsuarioVO> mapearResultSet(ResultSet rs) throws SQLException{

		ArrayList<UsuarioVO> lista = new ArrayList<UsuarioVO>();

		while(rs.next()){

			UsuarioVO usuarioVO = new UsuarioVO();	

			usuarioVO.setUsuariosequencial(rs.getInt("SEQ_USUARIO"));
			usuarioVO.setNomeUsuario(rs.getString("NOME_USUARIO"));
			usuarioVO.setEmail(rs.getString("EMAIL"));
			usuarioVO.setLogin(rs.getString("NOM_LOGIN"));
			usuarioVO.setSenha(rs.getString("SENHA"));
			usuarioVO.setCpf(rs.getString("CPF"));
			usuarioVO.setNumeroRg(rs.getString("NUM_RG"));
			usuarioVO.setContato(rs.getString("CONTATO"));
			usuarioVO.getCodigoTipoUsuario().setSequencial(rs.getInt("COD_TIPO_USUARIO"));
			usuarioVO.setEndereco(rs.getString("ENDERECO"));
			usuarioVO.setNumeroEndereco(rs.getString("NUM_ENDERECO"));
			usuarioVO.setComplemento(rs.getString("COMPLEMENTO"));
			usuarioVO.setBairro(rs.getString("BAIRRO"));
			usuarioVO.setCidade(rs.getString("CIDADE"));
			usuarioVO.setCep(rs.getString("CEP"));
			usuarioVO.setUf(rs.getString("UF"));
			
			


			lista.add(usuarioVO);
			
		}
		
		return lista;
		
	}
}

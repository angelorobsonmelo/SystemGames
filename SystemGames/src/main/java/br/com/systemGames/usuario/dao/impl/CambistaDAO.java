package br.com.systemGames.usuario.dao.impl;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import br.com.systemGames.database.Conexao;
import br.com.systemGames.excecao.BOException;
import br.com.systemGames.excecao.DAOException;
import br.com.systemGames.usuario.dao.ICambistaDAO;
import br.com.systemGames.usuario.model.CambistaVO;
import br.com.systemGames.util.VerificadorValorObjeto;

public class CambistaDAO implements ICambistaDAO {

	private String procedure;
	private CallableStatement cstmt;
	private String resultado;


	public String salvar(CambistaVO cambistaVO) throws DAOException {

		if (cambistaVO.getSequencial() != null) {

			return atualizar(cambistaVO);
		} else {

			return inserir(cambistaVO);
		}
	}



	public String inserir(CambistaVO cambistaVO) throws DAOException {
		procedure = "{ ? = CALL SP_CAMBISTA_INSERIR(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";	
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
			cstmt.setString(16, cambistaVO.getApelido());

			cstmt.setDouble(17, cambistaVO.getConfiguracaoCambistaVO().getLimiteMaximoVendaDiario());
			cstmt.setDouble(18, cambistaVO.getConfiguracaoCambistaVO().getLimiteMaximoVendaIndividual());
			cstmt.setString(19, cambistaVO.getConfiguracaoCambistaVO().getObservacao());
			cstmt.setDouble(20, cambistaVO.getConfiguracaoCambistaVO().getComissao1());
			cstmt.setDouble(21, cambistaVO.getConfiguracaoCambistaVO().getComissao2());
			cstmt.setDouble(22, cambistaVO.getConfiguracaoCambistaVO().getComissao3());


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


	public String atualizar(CambistaVO cambistaVO) throws DAOException {
		procedure = "{ ? = CALL SP_CAMBISTA_ATUALIZAR(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";	
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

			cstmt.setString(8, cambistaVO.getEndereco());
			cstmt.setString(9, cambistaVO.getNumeroEndereco());
			cstmt.setString(10, cambistaVO.getComplemento());
			cstmt.setString(11, cambistaVO.getBairro());
			cstmt.setString(12, cambistaVO.getCidade());
			cstmt.setString(13, cambistaVO.getCpf());
			cstmt.setString(14, cambistaVO.getUf());
			cstmt.setString(15, cambistaVO.getApelido());

			cstmt.setDouble(16, cambistaVO.getConfiguracaoCambistaVO().getLimiteMaximoVendaDiario());
			cstmt.setDouble(17, cambistaVO.getConfiguracaoCambistaVO().getLimiteMaximoVendaIndividual());
			cstmt.setString(18, cambistaVO.getConfiguracaoCambistaVO().getObservacao());
			cstmt.setDouble(19, cambistaVO.getConfiguracaoCambistaVO().getComissao1());
			cstmt.setDouble(20, cambistaVO.getConfiguracaoCambistaVO().getComissao2());
			cstmt.setDouble(21, cambistaVO.getConfiguracaoCambistaVO().getComissao3());
			cstmt.setInt(22, cambistaVO.getSequencial());

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



	public ArrayList<CambistaVO> pesquisarPorSeqUsuario(CambistaVO cambistaVO)
			throws DAOException {
		procedure = "{? = CALL SP_CAMBISTA_PESQUISAR_POR_SEQ_USUARIO(?)}";
		cstmt = null;
		ArrayList<CambistaVO> lista = new ArrayList<CambistaVO>();

		try
		{
			cstmt = Conexao.getConexao().prepareCall(procedure);
			cstmt.registerOutParameter(1, Types.OTHER);

			cstmt.setInt(2, cambistaVO.getUsuarioVO().getSequencial());
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




	public ArrayList<CambistaVO> mapearResultSet(ResultSet rs) throws SQLException, BOException, DAOException{

		ArrayList<CambistaVO> lista = new ArrayList<CambistaVO>();


		while(rs.next()){

			CambistaVO cambistaVO = new CambistaVO();

			cambistaVO.setSequencial(rs.getInt("seq_cambista"));
			cambistaVO.setNome(rs.getString("nome_cambista"));
			cambistaVO.setEmail(rs.getString("email_cambista"));
			cambistaVO.setCpf(rs.getString("cpf_cambista"));
			cambistaVO.setNumeroRg(rs.getString("num_rg_cambista"));
			cambistaVO.setContato(rs.getString("num_contato_cambista"));
			cambistaVO.setEndereco(rs.getString("endereco_cambista"));
			cambistaVO.setComplemento(rs.getString("complemento_cambista"));
			cambistaVO.setBairro(rs.getString("bairro_cambista"));
			cambistaVO.setCidade(rs.getString("cidade_cambista"));
			cambistaVO.setCep(rs.getString("cep_cambista"));
			cambistaVO.setUf(rs.getString("uf_cambista"));
			cambistaVO.setNumeroEndereco(rs.getString("num_endereco_cambista"));
			cambistaVO.setApelido(rs.getString("apelido_cambista"));
			cambistaVO.setSenha(rs.getString("senha_cambista"));

			cambistaVO.getTipoUsuarioVO().setSequencial(rs.getInt("cod_tipo_usuario"));

			cambistaVO.getUsuarioVO().setSequencial(rs.getInt("seq_usuario"));
			cambistaVO.getUsuarioVO().setNome(rs.getString("nome_usuario"));
			cambistaVO.getUsuarioVO().setEmail(rs.getString("email"));
			cambistaVO.getUsuarioVO().setCpf(rs.getString("cpf"));
			cambistaVO.getUsuarioVO().setNumeroRg(rs.getString("num_rg"));
			cambistaVO.getUsuarioVO().setContato(rs.getString("num_contato"));
			cambistaVO.getUsuarioVO().setEndereco(rs.getString("endereco"));
			cambistaVO.getUsuarioVO().setComplemento(rs.getString("complemento"));
			cambistaVO.getUsuarioVO().setBairro(rs.getString("bairro"));
			cambistaVO.getUsuarioVO().setCidade(rs.getString("cidade"));
			cambistaVO.getUsuarioVO().setCep(rs.getString("cep"));
			cambistaVO.getUsuarioVO().setUf(rs.getString("uf"));
			cambistaVO.getUsuarioVO().setNumeroEndereco(rs.getString("num_endereco"));
			cambistaVO.getUsuarioVO().setApelido(rs.getString("apelido"));

			cambistaVO.getConfiguracaoCambistaVO().setSequencial(rs.getInt("seq_configuracao_cambista"));
			cambistaVO.getConfiguracaoCambistaVO().setLimiteMaximoVendaDiario(rs.getDouble("limite_max_venda_diaria"));
			cambistaVO.getConfiguracaoCambistaVO().setLimiteMaximoVendaIndividual(rs.getDouble("limite_max_venda_individual"));
			cambistaVO.getConfiguracaoCambistaVO().setObservacao(rs.getString("observacao"));

			cambistaVO.getConfiguracaoCambistaVO().setComissao1(rs.getDouble("comissao1"));
			cambistaVO.getConfiguracaoCambistaVO().setComissao2(rs.getDouble("comissao2"));
			cambistaVO.getConfiguracaoCambistaVO().setComissao3(rs.getDouble("comissao3"));

			lista.add(cambistaVO);
		}
		return lista;
	}



	public String remover(CambistaVO cambistaVO) throws DAOException {
		procedure = "{ ? = CALL SP_CAMBISTA_REMOVER(?)}";	
		cstmt = null;
		resultado = null;

		try
		{

			cstmt = Conexao.getConexao().prepareCall(procedure);
			cstmt.registerOutParameter(1, Types.VARCHAR);

			cstmt.setInt(2, cambistaVO.getSequencial());

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

	public CambistaVO autenticar(CambistaVO cambistaVO) throws DAOException {
		procedure = "{? = CALL SP_CAMBISTA_AUTENTICAR(?,?)}";
		cstmt = null;
		CambistaVO usuarioRetorno = null;

		try
		{
			cstmt = Conexao.getConexao().prepareCall(procedure);
			cstmt.registerOutParameter(1, Types.OTHER);
			
			cstmt.setString(2, VerificadorValorObjeto.retornaStringValorObjetoOuNull(cambistaVO.getApelido()));
            cstmt.setString(3, VerificadorValorObjeto.retornaStringValorObjetoOuNull(cambistaVO.getSenha()));
			
			cstmt.execute();

			usuarioRetorno = mapearResultUsuarioRetornado((ResultSet) cstmt.getObject(1));

			cstmt.close();		

			return usuarioRetorno;
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
	
	
	public CambistaVO mapearResultUsuarioRetornado(ResultSet rs) throws SQLException, BOException, DAOException{

		CambistaVO cambistaVO = null;

		if(rs.next()){

			cambistaVO = new CambistaVO();

			cambistaVO.setSequencial(rs.getInt("seq_cambista"));
			cambistaVO.setNome(rs.getString("nome_cambista"));
			cambistaVO.setEmail(rs.getString("email_cambista"));
			cambistaVO.setCpf(rs.getString("cpf_cambista"));
			cambistaVO.setNumeroRg(rs.getString("num_rg_cambista"));
			cambistaVO.setContato(rs.getString("num_contato_cambista"));
			cambistaVO.setEndereco(rs.getString("endereco_cambista"));
			cambistaVO.setComplemento(rs.getString("complemento_cambista"));
			cambistaVO.setBairro(rs.getString("bairro_cambista"));
			cambistaVO.setCidade(rs.getString("cidade_cambista"));
			cambistaVO.setCep(rs.getString("cep_cambista"));
			cambistaVO.setUf(rs.getString("uf_cambista"));
			cambistaVO.setNumeroEndereco(rs.getString("num_endereco_cambista"));
			cambistaVO.setApelido(rs.getString("apelido_cambista"));
			cambistaVO.getTipoUsuarioVO().setSequencial(rs.getInt("cod_tipo_usuario"));
		}
		return cambistaVO;


	}

}

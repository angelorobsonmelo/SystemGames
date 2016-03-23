//package br.com.systemGames.usuario.bo.impl;
//
//import java.util.ArrayList;
//
//import br.com.systemGames.database.Conexao;
//import br.com.systemGames.excecao.BOException;
//import br.com.systemGames.usuario.bo.IUsuarioBO;
//import br.com.systemGames.usuario.cambista.dao.impl.ConfiguracaoCambistaDAO;
//import br.com.systemGames.usuario.cambista.model.ConfiguracaoCambistaVO;
//import br.com.systemGames.usuario.dao.impl.UsuarioDAO;
//import br.com.systemGames.usuario.model.UsuarioVO;
//
//public class UsuarioBO implements IUsuarioBO {
//	
//	private ArrayList<String> resultadoExecucaoProcedures;	
//	UsuarioDAO usuarioDAO;	
//	
//	ConfiguracaoCambistaDAO configuracaoCambistaDAO;
//	
//	public UsuarioBO() {
//		usuarioDAO = new UsuarioDAO();
//		resultadoExecucaoProcedures = new ArrayList<String>();
//		
//		configuracaoCambistaDAO = new ConfiguracaoCambistaDAO();
//	}
//
//	public ArrayList<?> listarTodos() throws BOException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	public String inserir(UsuarioVO usuarioVO) throws BOException {
//		
//		String[] resultadoExecucaoInserirUsuario = null;
//		String resultadoExecucaoInserirConfiguracaoCambista = null;
//		
//		try{
//			/*Setar o AutoCommit para False, validar toda a transação antes do Commit*/
//			
//			resultadoExecucaoInserirUsuario =  usuarioDAO.inserir(usuarioVO).split("SEQ_USUARIO");
//			resultadoExecucaoProcedures.add(resultadoExecucaoInserirUsuario[0]);
//			
//			
//			if (resultadoExecucaoInserirUsuario[0].equals("OK")){				
//				System.out.println("sequencial = "+resultadoExecucaoInserirUsuario[1]);
//				String codigo = resultadoExecucaoInserirUsuario[1];
//				System.out.println("codigo = "+codigo);
//				ConfiguracaoCambistaVO configuracaoCambistaVO = new ConfiguracaoCambistaVO();
//				configuracaoCambistaVO.setLimiteMaximoVendaDiario(usuarioVO.getConfiguracaoCambistaVO().getLimiteMaximoVendaDiario());
//				configuracaoCambistaVO.setLimiteMaximoVendaIndividual(usuarioVO.getConfiguracaoCambistaVO().getLimiteMaximoVendaIndividual());
//				configuracaoCambistaVO.setObservacao(usuarioVO.getConfiguracaoCambistaVO().getObservacao());
//				configuracaoCambistaVO.setSequencial(Integer.parseInt(codigo));
//				configuracaoCambistaVO.setComissao1(usuarioVO.getConfiguracaoCambistaVO().getComissao1());
//				configuracaoCambistaVO.setComissao2(usuarioVO.getConfiguracaoCambistaVO().getComissao2());
//				configuracaoCambistaVO.setComissao3(usuarioVO.getConfiguracaoCambistaVO().getComissao3());
//				resultadoExecucaoInserirConfiguracaoCambista =  configuracaoCambistaDAO.inserir(configuracaoCambistaVO);
//				resultadoExecucaoProcedures.add(resultadoExecucaoInserirConfiguracaoCambista);
//				if(!resultadoExecucaoInserirConfiguracaoCambista.equals("OK")){
//					throw new BOException("Erro ao inserir Configuração Cambista. "+resultadoExecucaoInserirConfiguracaoCambista);
//				}
//				//codigoBarrasVO.setSequencial(Integer.parseInt(resultadoExecucaoInserirUsuario[1]));
//				//codigo = codigoBarrasUtil.gerarCodigoDeBarra(Integer.parseInt(resultadoExecucaoInserirUsuario[1]), codigoBarrasVO.getProduto().getNomProduto());
//				 
//				
//			}else{
//				throw new BOException("Erro ao inserir na tabela Usuario"+resultadoExecucaoInserirUsuario[0]);
//			}
//			
//			return Conexao.verificarResultadosDaExecucaoDeProceduresValidandoCommit(resultadoExecucaoProcedures);
//			
//			
//		}catch (Exception ex) {
//			System.out.println(ex);
//			throw new BOException(ex);
//		}
//
//	}
//
//
//	public String remover(UsuarioVO usuarioVO) throws BOException {
//		String resultadoExecucaoRemoverUsuario = null;
//
//		try{
//			/*Setar o AutoCommit para False, validar toda a transação antes do Commit*/
//			Conexao.setarAutoCommitParaFalse();
//
//			resultadoExecucaoRemoverUsuario =  usuarioDAO.remover(usuarioVO);
//			resultadoExecucaoProcedures.add(resultadoExecucaoRemoverUsuario);
//
//			if (!resultadoExecucaoRemoverUsuario.equals("OK")){
//
//				throw new BOException("Erro ao remover Usuario. "+resultadoExecucaoRemoverUsuario);
//			}
//
//			return Conexao.verificarResultadosDaExecucaoDeProceduresValidandoCommit(resultadoExecucaoProcedures);
//
//		}catch (Exception ex) { 
//
//			throw new BOException(ex);
//
//		}finally{
//
//			resultadoExecucaoRemoverUsuario = null;
//			resultadoExecucaoProcedures.clear();
//
//		}
//	}
//
//	
//	public String alterar(UsuarioVO usuarioVO) throws BOException {
//		String resultadoExecucaoAlterarUsuario = null;
//
//		try{
//			/*Setar o AutoCommit para False, validar toda a transação antes do Commit*/
//			Conexao.setarAutoCommitParaFalse();
//			resultadoExecucaoProcedures = new ArrayList<String>();
//
//			resultadoExecucaoAlterarUsuario =  usuarioDAO.alterar(usuarioVO);
//			resultadoExecucaoProcedures.add(resultadoExecucaoAlterarUsuario);
//
//			if (!resultadoExecucaoAlterarUsuario.equals("OK")){
//
//				throw new BOException("Erro ao alterar Usuario. "+resultadoExecucaoAlterarUsuario);
//			}
//
//			return Conexao.verificarResultadosDaExecucaoDeProceduresValidandoCommit(resultadoExecucaoProcedures);
//
//		}catch (Exception ex) { 
//
//			throw new BOException(ex);
//
//		}finally{
//
//			resultadoExecucaoAlterarUsuario = null;
//			resultadoExecucaoProcedures.clear();
//
//		}
//	}
//
//
//	public ArrayList<UsuarioVO> consultarPorParams(UsuarioVO usuarioVO)
//			throws BOException {
//		try{
//			/*Setar o AutoCommit para False*/
//			Conexao.setarAutoCommitParaFalse();	
//			
//			return usuarioDAO.consultarPorParams();
//
//		}catch (Exception ex) { 
//			System.out.println(ex);
//
//			throw new BOException(ex);
//			
//		}
//	}
//
//	
//}

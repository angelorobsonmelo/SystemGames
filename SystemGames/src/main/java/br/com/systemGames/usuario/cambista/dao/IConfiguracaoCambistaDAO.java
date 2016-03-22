package br.com.systemGames.usuario.cambista.dao;

import java.util.ArrayList;

import br.com.systemGames.excecao.DAOException;
import br.com.systemGames.usuario.cambista.model.ConfiguracaoCambistaVO;

public interface IConfiguracaoCambistaDAO {
	
	public ArrayList<ConfiguracaoCambistaVO> listarTodos(ConfiguracaoCambistaVO configuracaoCambistaVO) throws DAOException;
	
	public String inserirUsuario(ConfiguracaoCambistaVO configuracaoCambistaVO) throws DAOException;
	
	public String inserir(ConfiguracaoCambistaVO configuracaoCambistaVO) throws DAOException;

	public String removerUsuario(ConfiguracaoCambistaVO configuracaoCambistaVO) throws DAOException;
	
	public String remover(ConfiguracaoCambistaVO configuracaoCambistaVO) throws DAOException;

	public String alterar(ConfiguracaoCambistaVO configuracaoCambistaVO) throws DAOException;
	
	public String alterarUsuario(ConfiguracaoCambistaVO configuracaoCambistaVO) throws DAOException;


}

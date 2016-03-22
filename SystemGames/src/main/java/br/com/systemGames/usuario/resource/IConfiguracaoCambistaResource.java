package br.com.systemGames.usuario.resource;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.systemGames.excecao.BOException;
import br.com.systemGames.usuario.cambista.model.ConfiguracaoCambistaVO;

public interface IConfiguracaoCambistaResource {
	
	public ArrayList<ConfiguracaoCambistaVO> listarTodos() throws SQLException, BOException;
	
	public String inserir(ConfiguracaoCambistaVO configuracaoCambistaVO) throws SQLException, BOException;

	public String remover(Integer sequencial, Integer sequencialUsuario) throws SQLException, BOException;

	public String alterar(ConfiguracaoCambistaVO configuracaoCambistaVO) throws SQLException, BOException;


}

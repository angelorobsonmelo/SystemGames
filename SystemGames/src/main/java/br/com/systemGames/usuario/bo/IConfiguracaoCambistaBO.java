package br.com.systemGames.usuario.bo;

import java.util.ArrayList;

import br.com.systemGames.excecao.BOException;
import br.com.systemGames.usuario.model.ConfiguracaoCambistaVO;

public interface IConfiguracaoCambistaBO {
	
	public ArrayList<ConfiguracaoCambistaVO> listarTodos(ConfiguracaoCambistaVO configuracaoCambistaVO) throws BOException;
	
	public String inserir(ConfiguracaoCambistaVO configuracaoCambistaVO) throws BOException;

	public String remover(ConfiguracaoCambistaVO configuracaoCambistaVO) throws BOException;
	
	public String removerUsuario(ConfiguracaoCambistaVO configuracaoCambistaVO) throws BOException;

	public String alterar(ConfiguracaoCambistaVO configuracaoCambistaVO) throws BOException;

}

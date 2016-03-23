package br.com.systemGames.usuario.resource;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.systemGames.excecao.BOException;
import br.com.systemGames.usuario.model.CambistaVO;

public interface ICambistaResource {

	public String salvar(CambistaVO cambistaVO) throws BOException, SQLException;
	
	public String remover(Integer sequencial) throws BOException, SQLException;
	
	
	public ArrayList<CambistaVO> pesquisarPorSeqUsuario(Integer sequencialUsuario) throws BOException, SQLException;

	
}

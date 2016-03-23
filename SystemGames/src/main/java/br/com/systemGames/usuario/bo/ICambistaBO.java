package br.com.systemGames.usuario.bo;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.systemGames.excecao.BOException;
import br.com.systemGames.excecao.DAOException;
import br.com.systemGames.usuario.model.CambistaVO;

public interface ICambistaBO {

	public String salvar(CambistaVO cambistaVO) throws BOException, SQLException;
	
	public String remover(CambistaVO cambistaVO) throws BOException, SQLException;
	
	public CambistaVO autenticar(CambistaVO cambistaVO) throws BOException, SQLException;
	
	public ArrayList<CambistaVO> pesquisarPorSeqUsuario(CambistaVO cambistaVO) throws BOException, SQLException;
	
	
}

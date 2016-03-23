package br.com.systemGames.usuario.dao;

import java.util.ArrayList;

import br.com.systemGames.excecao.DAOException;
import br.com.systemGames.usuario.model.CambistaVO;

public interface ICambistaDAO {

	public String salvar(CambistaVO cambistaVO) throws DAOException;
	
	public String remover(CambistaVO cambistaVO) throws DAOException;
	
	public ArrayList<CambistaVO> pesquisarPorSeqUsuario(CambistaVO cambistaVO) throws DAOException;
	
}

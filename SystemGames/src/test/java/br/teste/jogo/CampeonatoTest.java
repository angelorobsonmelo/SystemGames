package br.teste.jogo;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.systemGames.excecao.BOException;
import br.com.systemGames.jogo.bo.impl.CampeonatoBO;
import br.com.systemGames.jogo.model.CampeonatoVO;

public class CampeonatoTest {


	public static void main(String[] args) throws BOException, SQLException {


		//salvar();

		//listarTodos();

		//pesquisarPorSequencialCampeonato();


	}

	private static void pesquisarPorSequencialCampeonato() throws SQLException,
			BOException {
		CampeonatoVO campeonatoVO = new CampeonatoVO();

		CampeonatoBO campeonatoBO = new CampeonatoBO();
		
		campeonatoVO.setSequencial(1);
		
		CampeonatoVO campeonatoRetornado = campeonatoBO.listarPorSequencial(campeonatoVO);
		
		System.out.println(campeonatoRetornado.getNome());
	}

	private static void listarTodos() throws SQLException, BOException {
		CampeonatoBO campeonatoBO = new CampeonatoBO();

		ArrayList<CampeonatoVO> listaRetornada = campeonatoBO.listarTodos();


		for (CampeonatoVO campeonatoVO : listaRetornada) {

			System.out.println(campeonatoVO.getSequencial());
		}
	}

	private static void salvar() throws SQLException, BOException {
		CampeonatoVO campeonatoVO = new CampeonatoVO();

		CampeonatoBO campeonatoBO = new CampeonatoBO();

		campeonatoVO.setSequencial(2);
		campeonatoVO.setNome("Campeonato Inglês");

		campeonatoBO.salvar(campeonatoVO);
	}






}

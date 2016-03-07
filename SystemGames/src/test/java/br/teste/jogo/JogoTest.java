package br.teste.jogo;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.systemGames.excecao.BOException;
import br.com.systemGames.jogo.bo.impl.ConfiguracaoJogoBO;
import br.com.systemGames.jogo.bo.impl.JogoBO;
import br.com.systemGames.jogo.bo.impl.LimiteApostaBO;
import br.com.systemGames.jogo.model.ConfiguracaoJogoVO;
import br.com.systemGames.jogo.model.JogoVO;
import br.com.systemGames.jogo.model.LimiteApostaVO;

public class JogoTest {

	public static void main(String[] args) throws BOException, SQLException {

		JogoVO jogoVO = new JogoVO();
		JogoBO jogoBO = new JogoBO();
		LimiteApostaVO limiteApostaVO = new LimiteApostaVO();
		LimiteApostaBO limiteApostaBO = new LimiteApostaBO();
		ConfiguracaoJogoVO configuracaoJogoVO = new ConfiguracaoJogoVO();
		ConfiguracaoJogoBO configuracaoJogoBO = new ConfiguracaoJogoBO();

		Date hoje = new Date();
		SimpleDateFormat df;
		df = new SimpleDateFormat("dd/MM/yyyy");


	    jogoVO.setSequencial(17);
		jogoVO.setJogo("Sport e Palmeiras");
		jogoVO.getCampeonatoVO().setSequencial(1);
		jogoVO.getEsporteVO().setSequencial(1);
		jogoVO.setDataJogo(hoje);
		jogoVO.setHoraInicialJogo("11:00");

		String resultado = jogoBO.salvar(jogoVO);

		if (resultado.equals("OK")) {

			if (jogoVO.getSequencial() != null) {

				System.out.println("caiu aqui no id preenchido");

				JogoVO jogoVOUltimoSequencial = jogoVO;

				limiteApostaVO.setJogoVO(jogoVOUltimoSequencial);

				LimiteApostaVO limiteApostaRetorno = limiteApostaBO.buscarTodosPorSeqJogo(limiteApostaVO);

				limiteApostaVO.setSequencial(limiteApostaRetorno.getSequencial());
				limiteApostaVO.setCasa(3D);
				limiteApostaVO.setEmpate(4D);
				limiteApostaVO.setFora(5D);
				limiteApostaVO.setGolEMeio(6D);
				limiteApostaVO.setDuplaChance(7D);
				limiteApostaVO.setAmbos(8D);

				limiteApostaVO.setLimiteCasa(9D);
				limiteApostaVO.setLimiteEmpate(10D);
				limiteApostaVO.setLimiteFora(11D);
				limiteApostaVO.setLimiteGolEMeio(12D);
				limiteApostaVO.setLimiteDuplaChance(13D);

				limiteApostaVO.setLimiteIndividual(14D);

				limiteApostaVO.setLimiteAmbos(15D);

				String limiteApostaRetornoProcedures = limiteApostaBO.salvar(limiteApostaVO);


				if (limiteApostaRetornoProcedures.equals("OK")) {

					if (jogoVO.getSequencial() != null) {


						configuracaoJogoVO.setJogoVO(jogoVO);

						ConfiguracaoJogoVO configuracaoJogoRetorno = configuracaoJogoBO.buscarConfiguracaoPorSeqJogo(configuracaoJogoVO);

						configuracaoJogoVO.setSequencial(configuracaoJogoRetorno.getSequencial());

						configuracaoJogoVO.setFinalizarAutomaticamente(true);
						configuracaoJogoVO.setJogoFinalizado(false);

						configuracaoJogoBO.salvar(configuracaoJogoVO);

					}


				}


			} else {

				System.out.println("caiu aqui no id vazio");

				JogoVO jogoVOUltimoSequencial = jogoBO.retornarUltimoSequencial();

				limiteApostaVO.setJogoVO(jogoVOUltimoSequencial);

				limiteApostaVO.setCasa(1D);
				limiteApostaVO.setEmpate(2D);
				limiteApostaVO.setFora(3D);
				limiteApostaVO.setGolEMeio(4D);
				limiteApostaVO.setDuplaChance(5D);
				limiteApostaVO.setAmbos(6D);

				limiteApostaVO.setLimiteCasa(7D);
				limiteApostaVO.setLimiteEmpate(8D);
				limiteApostaVO.setLimiteFora(9D);
				limiteApostaVO.setLimiteGolEMeio(10D);
				limiteApostaVO.setLimiteDuplaChance(11D);

				limiteApostaVO.setLimiteIndividual(13D);

				limiteApostaVO.setLimiteAmbos(12D);

				String limiteApostaRetornoProcedures = limiteApostaBO.salvar(limiteApostaVO);


				if (limiteApostaRetornoProcedures.equals("OK")) {


					configuracaoJogoVO.setJogoVO(jogoBO.retornarUltimoSequencial());
					configuracaoJogoVO.setFinalizarAutomaticamente(false);
					configuracaoJogoVO.setJogoFinalizado(true);

					configuracaoJogoBO.salvar(configuracaoJogoVO);

				}


			}



		}


	}

}

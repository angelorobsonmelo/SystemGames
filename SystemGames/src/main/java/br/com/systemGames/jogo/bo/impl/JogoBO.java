package br.com.systemGames.jogo.bo.impl;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import br.com.systemGames.database.Conexao;
import br.com.systemGames.excecao.BOException;
import br.com.systemGames.jogo.bo.IJogoBO;
import br.com.systemGames.jogo.dao.impl.JogoDAO;
import br.com.systemGames.jogo.model.ConfiguracaoJogoVO;
import br.com.systemGames.jogo.model.JogoVO;
import br.com.systemGames.jogo.model.LimiteApostaVO;

public class JogoBO implements IJogoBO {

	JogoDAO jogoDAO = new JogoDAO();

	ArrayList<String> resultadoExecucaoProcedures= new ArrayList<String>();


	public String salvar(JogoVO jogoVO) throws SQLException, BOException {
		String resultadoExecucaoInserirUnidadeDeSaude = null;

		try{	
			/*Setar o AutoCommit para False, validar toda a transação antes do Commit*/
			Conexao.setarAutoCommitParaFalse();

			resultadoExecucaoProcedures.clear();

			resultadoExecucaoInserirUnidadeDeSaude =  jogoDAO.salvar(jogoVO);
			resultadoExecucaoProcedures.add(resultadoExecucaoInserirUnidadeDeSaude);

			if (!resultadoExecucaoInserirUnidadeDeSaude.equals("OK")){
				throw new BOException("Erro ao inserir unidade de saúde. "+resultadoExecucaoInserirUnidadeDeSaude);

			}else {


				JogoBO jogoBO = new JogoBO();
				LimiteApostaVO limiteApostaVO = new LimiteApostaVO();
				LimiteApostaBO limiteApostaBO = new LimiteApostaBO();
				ConfiguracaoJogoVO configuracaoJogoVO = new ConfiguracaoJogoVO();
				ConfiguracaoJogoBO configuracaoJogoBO = new ConfiguracaoJogoBO();


				String resultado = resultadoExecucaoInserirUnidadeDeSaude;

				if (resultado.equals("OK")) {

					if (jogoVO.getSequencial() != null) {

						System.out.println("caiu aqui no id preenchido");

						limiteApostaVO = limiteApostaBO.buscarTodosPorSeqJogo(jogoVO);

						jogoVO.getLimiteApostaVO().setSequencial(limiteApostaVO.getSequencial());



						System.out.println(jogoVO.getLimiteApostaVO().getSequencial());

						String limiteApostaRetornoProcedures = limiteApostaBO.salvar(jogoVO);


						if (limiteApostaRetornoProcedures.equals("OK")) {

							if (jogoVO.getSequencial() != null) {


								configuracaoJogoVO = 	configuracaoJogoBO.buscarConfiguracaoPorSeqJogo(jogoVO);

								jogoVO.getConfiguracaoJogoVO().setSequencial(configuracaoJogoVO.getSequencial());

								configuracaoJogoBO.salvar(jogoVO);

							}


						}


					} else {

						System.out.println("caiu aqui no id vazio");

						JogoVO jogoVOUltimoSequencial = jogoBO.retornarUltimoSequencial();

						jogoVO.setSequencial(jogoVOUltimoSequencial.getSequencial());


						String limiteApostaRetornoProcedures = limiteApostaBO.salvar(jogoVO);


						if (limiteApostaRetornoProcedures.equals("OK")) {



							jogoVO.setSequencial(jogoBO.retornarUltimoSequencial().getSequencial());

							if(jogoVO.getConfiguracaoJogoVO().getJogoFinalizado() == null){

								jogoVO.getConfiguracaoJogoVO().setJogoFinalizado(false);

							}

							jogoVO.getConfiguracaoJogoVO().setFinalizarAutomaticamente(true);


							configuracaoJogoBO.salvar(jogoVO);

						}


					}



				}


			}


			return Conexao.verificarResultadosDaExecucaoDeProceduresValidandoCommit(resultadoExecucaoProcedures);

		}catch (Exception ex) { 
			/*Preferivel que seja dado um Rollback neste caso*/

			throw new BOException(ex);
		}
		finally{
			resultadoExecucaoInserirUnidadeDeSaude = null;
			resultadoExecucaoProcedures.clear();

		}
	}

	public String remover(JogoVO jogoVO) throws SQLException, BOException {
		String resultadoExecucaoInserirUnidadeDeSaude = null;

		try{	
			/*Setar o AutoCommit para False, validar toda a transação antes do Commit*/
			Conexao.setarAutoCommitParaFalse();

			resultadoExecucaoProcedures.clear();

			resultadoExecucaoInserirUnidadeDeSaude =  jogoDAO.remover(jogoVO);
			resultadoExecucaoProcedures.add(resultadoExecucaoInserirUnidadeDeSaude);

			if (!resultadoExecucaoInserirUnidadeDeSaude.equals("OK")){
				throw new BOException("Erro ao inserir unidade de saúde. "+resultadoExecucaoInserirUnidadeDeSaude);
			}

			return Conexao.verificarResultadosDaExecucaoDeProceduresValidandoCommit(resultadoExecucaoProcedures);

		}catch (Exception ex) { 
			/*Preferivel que seja dado um Rollback neste caso*/

			throw new BOException(ex);
		}
		finally{
			resultadoExecucaoInserirUnidadeDeSaude = null;
			resultadoExecucaoProcedures.clear();

		}
	}

	public ArrayList<JogoVO> listarTodos() throws SQLException, BOException {
		try {

			/*Setar o AutoCommit para False, validar toda a transação antes do Commit*/
			Conexao.setarAutoCommitParaFalse();

			ArrayList<JogoVO> retornoBanco = jogoDAO.listarTodos();
			ArrayList<JogoVO> lista = new ArrayList<JogoVO>();

			DateFormat format = new SimpleDateFormat("dd/MM/yyyy");

			Calendar dataAtual = new GregorianCalendar();
			Calendar dataDoJogo = new GregorianCalendar();

			Date data = new Date(System.currentTimeMillis());  
			SimpleDateFormat formatarDate = new SimpleDateFormat("dd/MM/yyyy"); 

			dataAtual.setTime(format.parse(formatarDate.format(data)));



			for (JogoVO jogoVO : retornoBanco) {

				dataDoJogo.setTime(format.parse(jogoVO.getDataJogoFormatadaBasica()));


				if (jogoVO.getResultadoJogoVO().getResultadoCasa() == 0 && jogoVO.getResultadoJogoVO().getResultadoFora() == 0 && dataDoJogo.getTimeInMillis() >= dataAtual.getTimeInMillis()) {

					jogoVO.getResultadoJogoVO().setResultadoCasa(null);
					jogoVO.getResultadoJogoVO().setResultadoFora(null);

					lista.add(jogoVO);

				}else {

					lista.add(jogoVO);

				}
			}

			return lista;
		} catch (Exception e) {
			throw new BOException(e);
		}
	}

	public JogoVO retornarUltimoSequencial() throws SQLException, BOException {
		try {

			/*Setar o AutoCommit para False, validar toda a transação antes do Commit*/
			Conexao.setarAutoCommitParaFalse();

			return jogoDAO.retornarUltimoSequencial();
		} catch (Exception e) {
			throw new BOException(e);
		}
	}

	public ArrayList<JogoVO> listarTodosBasico() throws SQLException,
			BOException {
		try {

			/*Setar o AutoCommit para False, validar toda a transação antes do Commit*/
			Conexao.setarAutoCommitParaFalse();

			return jogoDAO.listarTodosBasico();

		} catch (Exception e) {
			throw new BOException(e);
		}
	}

}

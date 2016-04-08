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

	public ArrayList<JogoVO> listarPorParams(JogoVO jogoVO) throws BOException {
		try {

			/*Setar o AutoCommit para False, validar toda a transação antes do Commit*/
			Conexao.setarAutoCommitParaFalse();

			return jogoDAO.listarPorParams(jogoVO);
		} catch (Exception e) {
			throw new BOException(e);
		}
	}

}

package br.com.systemGames.util;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import br.com.systemGames.excecao.BOException;
import br.com.systemGames.jogo.bo.impl.ConfiguracaoJogoBO;
import br.com.systemGames.jogo.bo.impl.JogoBO;
import br.com.systemGames.jogo.model.JogoVO;

public class VericadorDeJogoReliazadoTarefa implements Job{


	private JogoBO jogoBO;
	private ConfiguracaoJogoBO configuracaoJogoBO;

	public VericadorDeJogoReliazadoTarefa() {

		jogoBO = new JogoBO();
		configuracaoJogoBO = new ConfiguracaoJogoBO();
	}


	public void execute(JobExecutionContext arg0) throws JobExecutionException {



		try {


			Calendar dataAtual = new GregorianCalendar();
			Calendar dataDoJogo = new GregorianCalendar();

			Date data = new Date(System.currentTimeMillis());  
			SimpleDateFormat formatarDate = new SimpleDateFormat("dd/MM/yyyy"); 

			dataAtual.setTime(formatarDate.parse(formatarDate.format(data)));

			ArrayList<JogoVO> listaJogoRetornado = jogoBO.listarTodos();
			
			
			Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Brazil/East"));
			
			/* 
			int ano = calendar.get(Calendar.YEAR);
			int mes = calendar.get(Calendar.MONTH); // O mês vai de 0 a 11.
			int semana = calendar.get(Calendar.WEEK_OF_MONTH);
			int dia = calendar.get(Calendar.DAY_OF_MONTH);
			*/
			
			int hora = calendar.get(Calendar.HOUR_OF_DAY);
			int minuto = calendar.get(Calendar.MINUTE);
			int segundo = calendar.get(Calendar.SECOND);

			String horaAtual = hora + "" + minuto + "" + segundo;

			Integer contador = 0;
			
			for (JogoVO jogoVO : listaJogoRetornado) {

				dataDoJogo.setTime(formatarDate.parse(jogoVO.getDataJogoFormatadaBasica()));
				
				String texto = jogoVO.getHoraInicialJogo();
		        int inicio = texto.indexOf("");
		        int fim = texto.indexOf(":", inicio);
		        
		        int inicio1 = texto.indexOf(":") + 1;
		        int fim2 = texto.indexOf(":", inicio1);
		        
		        int inicio3 = texto.indexOf(":") + 4;
		        
		        String horaInicialJogoRetornoString = texto.substring(inicio, fim) + "" + texto.substring(inicio1, fim2) + "" + texto.substring(inicio3);

				if (dataDoJogo.getTimeInMillis() <= dataAtual.getTimeInMillis() && Integer.parseInt(horaInicialJogoRetornoString) <= Integer.parseInt(horaAtual)) {
					
					
					System.out.println("jogo fora da data e hora: " + jogoVO.getJogo());
                      
					jogoVO.getConfiguracaoJogoVO().setJogoFinalizado(true);
					
					configuracaoJogoBO.salvar(jogoVO);
				}


			}

		} catch (BOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

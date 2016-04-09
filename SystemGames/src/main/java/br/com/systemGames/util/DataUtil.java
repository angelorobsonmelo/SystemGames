package br.com.systemGames.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;


/**
 * Classe que manipula formato de datas
 */
public class DataUtil {
	private static SimpleDateFormat formatador = new SimpleDateFormat();

	public DataUtil() {
	}

	/**
	 * M�todo que formata um Integer para o formato mm/aaaa
	 * 
	 * @param data(Uma
	 *            data no formato java.util.Date)
	 * @return dataRetorno(Uma data no formato dd/mm/aaaa)
	 */
	public static String getFormataDataCompetencia(Integer data) {
		String dataRetorno;
		String mes;
		String ano;
		dataRetorno = data.toString();
		mes = dataRetorno.substring(4, 6);
		ano = dataRetorno.substring(0, 4);
		dataRetorno = mes + "/" + ano;

		return dataRetorno;
	}

	/**
	 * M�todo que formata uma data para o formato dd/mm/aaaa
	 * 
	 * @param data(Uma
	 *            data no formato java.util.Date)
	 * @return dataRetorno(Uma data no formato dd/mm/aaaa)
	 */
	public static String getDataApresentacao(Date data) {
		String dataRetorno;
		formatador.applyPattern("dd/MM/yyyy");
		dataRetorno = formatador.format(data);
		return dataRetorno;
	}
	
	
	
	
	public static String getDataApresentacao(Date data,String formato) {
		String dataRetorno;
		formatador.applyPattern(formato);
		dataRetorno = formatador.format(data);
		return dataRetorno;
	}

	/**
	 * M�todo que formata uma data para o formato de banco (aaaa-mm-dd H:m:s)
	 * 
	 * @param data(Uma
	 *            data no formato java.util.Date)
	 * @return dataRetorno(Uma data no formato aaaa-mm-dd H:m:s)
	 */
	public static String getDataDBCompleta(Date data) {
		String dataRetorno;
		formatador.applyPattern("yyyy-MM-dd H:m:s");
		dataRetorno = formatador.format(data);
		return dataRetorno;
	}

	/**
	 * M�todo que formata uma data para o formato de banco sem hora (aaaa-mm-dd)
	 * 
	 * @param data(Uma
	 *            data no formato java.util.Date)
	 * @return dataRetorno(Uma data no formato aaaa-mm-dd)
	 */
	public static String getDataDBSemHora(Date data) {
		String dataRetorno;
		formatador.applyPattern("yyyy-MM-dd");
		dataRetorno = formatador.format(data);
		return dataRetorno;
	}
	
	public static String getDataDBSemHoraFormatoBR(Date data) {
		String dataRetorno;
		formatador.applyPattern("dd/MM/yyyy");
		dataRetorno = formatador.format(data);
		return dataRetorno;
	}

	/**
	 * M�todo que formata uma data para o formato (mm/aaaa)
	 * 
	 * @param data(Uma
	 *            data no formato java.util.Date)
	 * @return dataRetorno(Uma data no formato mm/aaaa)
	 * 
	 * @author Leonel Casado.
	 * @version 13/03/2006
	 */
	public static String getMesAno(Date data) {
		String dataRetorno;
		formatador.applyPattern("MM/yyyy");
		dataRetorno = formatador.format(data);
		return dataRetorno;
	}
	
	public static String getMes() {
		String dataRetorno;
		Date data = new Date();
		formatador.applyPattern("MM");
		dataRetorno = formatador.format(data);
		return dataRetorno;
	}
	
	public static String getAno() {
		String dataRetorno;
		Date data = new Date();
		formatador.applyPattern("yyyy");
		dataRetorno = formatador.format(data);
		return dataRetorno;
	}
	
	public static String getDia() {
		String dataRetorno;
		Date data = new Date();
		formatador.applyPattern("dd");
		dataRetorno = formatador.format(data);
		return dataRetorno;
	}
	
	public static String getDataCompletaStr() {
		String dataRetorno;
		Date data = new Date();
		//formatador.applyPattern("yyyy-MM-dd H:m:s");
		formatador.applyPattern("dd/MM/yyyy H:m:s");
		dataRetorno = formatador.format(data);
		return dataRetorno;
	}
	
	public static Date getDataCompletaDate() throws ParseException{
		
		Date data = null;
		try{
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd H:m:s");
			data = (java.util.Date)formatter.parse(getDataCompletaStr());
			return data;
		} catch (ParseException e) {              
			throw e;  
		}
	}
	
	public static Timestamp getDataCompletaTimeStamp() {
	
		Date data = new Date();
		formatador.applyPattern("yyyy-MM-dd H:m:s");
		Timestamp timestamp = Timestamp.valueOf(formatador.format(data));
		
		return timestamp;
	}

	/**
	 * Adiciona a quantidadeHoras correspondente a data indicada. Motiva��o:
	 * Alguns componentes transformam a data segundo a zona geogr�fica (GMT) e
	 * este m�todo serve para corrigir algum atraso inserido por esses
	 * componentes.
	 * 
	 * @param data
	 *            data sobre a qual se deseja adicionar uma certa quantidade de
	 *            horas
	 * @param quantidadeHoras
	 *            n�mero de horas que ser�o somadas a data informada
	 * @return data com a quantidadeHoras adicionada
	 * @author Alisson
	 * @version 23/11/2005
	 */
	public static Date addHoras(Date data, int quantidadeHoras) {
		try {
			Calendar c = Calendar.getInstance();
			c.setTime(data);
			c.add(Calendar.HOUR, quantidadeHoras);
			return c.getTime();
		} catch (Exception e) {
			return null;
		}
	}

	public static Date stringToDate(String data) {
		try {
			// SimpleDateFormat df = new SimpleDateFormat("MM/yyyy");
			Date dataConvertida = formatador.parse(data);
			return dataConvertida;
		} catch (Exception e) {
			return null;
		}
	}

	public static String dateToString(Date data) {
		Calendar dataConvertida = Calendar.getInstance();
		dataConvertida.setTime(data);
		int ano = dataConvertida.get(Calendar.YEAR);
		int mes = 1 + dataConvertida.get(Calendar.MONTH);
		int dia = dataConvertida.get(Calendar.DAY_OF_MONTH);

		return dia + "/" + mes + "/" + ano;
	}

	/**
	 * M�todo usado para validar e converter um valor passado para uma data.
	 * 
	 * @author Leonel Casado
	 */
	public Date getDate(Object valor) throws Exception {
		Date resultado = null;
		if (valor == null) {
			resultado = null;
		} else {
			// SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			// N�o deixa converter para uma data v�lida mais pr�xima caso a
			// data informada seja inv�lida.
			formatador.setLenient(false);
			resultado = formatador.parse(valor.toString());
		}
		return resultado;
	}

	/**
	 * Retorna uma String passando uma Data.
	 * 
	 * @param data
	 * @return
	 */
	public static String obterDataFormatada(Date data) {
		// DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		if(data != null){
			formatador.applyPattern("dd/MM/yyyy");
			return formatador.format(data);
		}
		else{
			return null;
		}
		
	}

	/**
	 * Retorna uma data Passando uma String
	 * 
	 * @param data
	 * @return
	 */
	public static Date obterDataFormatada(String data) throws Exception {
		// DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		formatador.applyPattern("dd/MM/yyyy");
		return formatador.parse(data);
	}
	
		
	
	
	
	public static Date stringToDate(String data, String formato) 
				throws java.text.ParseException {
		
		try{
			formatador.applyPattern(formato);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return formatador.parse(data);
	}
	
	
	/**
	 * Retorna a Hora 
	 * @param data Java.Util.Date informada
	 * @return String com a hora ex: 10:10:05
	 */
	public static String getHoraDBCompleta(Date data) {
		String dataRetorno;
		formatador.applyPattern("HH:mm:ss");
		dataRetorno = formatador.format(data);
		return dataRetorno;
	}
	
	/**
	 * Converte uma String para um objeto Date. Caso a String seja vazia ou
	 * nula, retorna null - para facilitar em casos onde formulários podem ter
	 * campos de datas vazios.
	 * 
	 * @param data
	 * String no formato dd/MM/yyyy HH:mm:ss a ser formatada
	 * @return Date Objeto Date ou null caso receba uma String vazia ou nula
	 * @throws Exception
	 *             Caso a String esteja no formato errado
	 */
	public static Date formataDataHora(String data) throws Exception {
		if (data == null || data.equals(""))
			return null;

		Date date = null;
		try {
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			
			date = (java.util.Date) formatter.parse(data);
		} catch (ParseException e) {
			throw e;
		}
		return date;
	}
	
	public static Timestamp formataDataToTimestamp(Date data) throws Exception {
		if (data == null || data.equals(""))
			return null;

		Timestamp timeStampDate = new Timestamp(data.getTime());
		
		return timeStampDate;
	}
	
	public static String formatarDataRetornandoString(Date data) {
		if(data != null){
			return new SimpleDateFormat("dd/MM/yyyy").format(data);
		}
		return "";
	}
	
	public static String formatarDataRetornandoStringOuNull(Date data) {
		if(data != null){
			return new SimpleDateFormat("dd/MM/yyyy").format(data);
		}
		return null;
	}
		
	
	public static String formatarDataComHoraRetornandoString(Date data) {
		if(data != null){
			return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(data);
		}
		return "";
	}
	
	public static String formatarDataSemHoraRetornandoString(Date data) {
		if(data != null){
			return new SimpleDateFormat("dd/MM/yyyy").format(data);
		}
		return "";
	}
	
	public static String formatarDataTimeStampComHoraRetornandoString(Date data) {
		if(data != null){
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(data);
		}
		return "";
	}
	
	public static Integer getIdade(Date data) {  
		
		if(data != null){
			Calendar dateOfBirth = new GregorianCalendar();
	        dateOfBirth.setTime(data);
	        Calendar today = Calendar.getInstance();
	        int age = today.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);
	        dateOfBirth.add(Calendar.YEAR, age);
	        
	        if (today.before(dateOfBirth)) {
	            age--;
	        }
	        return age;
		}else{
			return null;
		}   
	}
	
	//Calcula a Idade baseado em java.util.Date
    public static int calculaIdade(java.util.Date dataNasc){
        Calendar dateOfBirth = new GregorianCalendar();
        dateOfBirth.setTime(dataNasc);
        
        // Cria um objeto calendar com a data atual
        Calendar today = Calendar.getInstance();
        
        // Obtém a idade baseado no ano
        int age = today.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);
        
        dateOfBirth.add(Calendar.YEAR, age);
        
        //se a data de hoje é antes da data de Nascimento, então diminui 1(um)
        if (today.before(dateOfBirth)) {
            age--;
        }
        return age;
        
    }
    
    //Calcula a Idade baseado em java.util.Date
    public static String retornarIdadeEmAnosMesesSemanasDias(java.util.Date dataNasc){
    	
    	Calendar dateOfBirth = new GregorianCalendar();
        dateOfBirth.setTime(dataNasc);
        Calendar today = Calendar.getInstance();
    
    	DateTime start = new DateTime(dateOfBirth.get(Calendar.YEAR), dateOfBirth.get(Calendar.MONTH)+1, dateOfBirth.get(Calendar.DAY_OF_MONTH), 0, 0, 0, 0);  
        DateTime end = new DateTime(today.get(Calendar.YEAR), today.get(Calendar.MONTH)+1, today.get(Calendar.DAY_OF_MONTH), 0, 0, 0, 0);  
        Period per = new Period (start, end);  
        
        // Isto imprime "27 years, 6 months, 3 weeks and 3 days"  
        //System.out.println (PeriodFormat.getDefault().print (per));
        
        // Isto imprime "27 anos, 6 meses, 3 semanas e 3 dias"  
        PeriodFormatter pf = new PeriodFormatterBuilder()  
            .appendYears()  
            .appendSuffix (" ano, ", " anos, ")  
            .appendMonths()   
            .appendSuffix (" mês, ", " meses, ")  
            .appendWeeks()   
            .appendSuffix (" semana e ", " semanas e ")  
            .appendDays()  
            .appendSuffix (" dia ", " dias ")  
            .toFormatter();  
        
          
        
        return pf.print(per);
    }  
    
    public static String retornaDataAtualExtensoComDiaSemana(){

    	DateFormat dfmt = new SimpleDateFormat("EEEE, d 'de' MMMM 'de' yyyy"); 
    	Date hoje = Calendar.getInstance(Locale.getDefault()).getTime(); 
    	return dfmt.format(hoje); 
    	
    }
    
    public static String retornaDataAtualExtenso(){

    	DateFormat dfmt = new SimpleDateFormat("d 'de' MMMM 'de' yyyy"); 
    	Date hoje = Calendar.getInstance(Locale.getDefault()).getTime(); 
    	return dfmt.format(hoje); 
    	
    }
    
    /**
	 Intervalo de tempo(Horas e Minutos entre duas datas
	 */
	public static String getIntervaloDeTempoEntreDatas(Date dataHoraInicial, Date dataHoraFinal) {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		
		c1.clear();
		c1.set(dataHoraInicial.getYear(), dataHoraInicial.getMonth(), dataHoraInicial.getDay());
		c1.set(Calendar.HOUR, dataHoraInicial.getHours());
		c1.set(Calendar.MINUTE, dataHoraInicial.getMinutes());
		c1.set(Calendar.SECOND, dataHoraInicial.getSeconds());
		
		c2.clear();
		c2.set(dataHoraFinal.getYear(), dataHoraFinal.getMonth(), dataHoraFinal.getDay());
		c2.set(Calendar.HOUR, dataHoraFinal.getHours());
		c2.set(Calendar.MINUTE, dataHoraFinal.getMinutes());
		c2.set(Calendar.SECOND, dataHoraFinal.getSeconds());
		
		//Diferença em Minutos
		long diffMinutos = (c2.getTimeInMillis()- c1.getTimeInMillis())/(60*1000);
		//System.out.println("Diferença em Minutos:"+diffMinutos+" minutos.");
		
		//Diferença em Horas e Minutos
		long diff = c2.getTimeInMillis()- c1.getTimeInMillis();
		long hours = (60 * 60 * 1000);
		
		long diffHoras = diff / hours;
		long diffHorasMinutos = (diff % hours) / (60 * 1000);
		long diffHorasMinutosSegundos = ((diff % hours) % (60 * 1000)) / (1000);
		//System.out.println("Diferença em Horas/Minutos:"+ diffHoras + " horas e "+diffHorasMinutos+" minutos.");
		
		String diferencaHoras = "";
		long verificadorUmDigito = 9;
		if(diffHoras>verificadorUmDigito){
			diferencaHoras = ""+diffHoras;			
		}else{
			diferencaHoras="0"+diffHoras;
		}
		
		String diferencaHorasMinutos = "";
		if(diffHorasMinutos>verificadorUmDigito){
			diferencaHorasMinutos = ""+diffHorasMinutos;			
		}else{
			diferencaHorasMinutos="0"+diffHorasMinutos;
		}
		
		String diferencaHorasMinutosSegundos = "";
		if(diffHorasMinutosSegundos>verificadorUmDigito){
			diferencaHorasMinutosSegundos = ""+diffHorasMinutosSegundos;			
		}else{
			diferencaHorasMinutosSegundos="0"+diffHorasMinutosSegundos;
		}
		
		String diferencaEmHorasEeMinutos = diferencaHoras+":"+diferencaHorasMinutos+":"+diferencaHorasMinutosSegundos;
		
		return diferencaEmHorasEeMinutos;
	}
	
	
	public static void main(String [] args){
		
		/*Calendar cal = Calendar.getInstance();
		cal.set(2009, 11, 9); //year is as expected, month is zero based, date is as expected
		Date data = cal.getTime();		
		System.out.println(DataUtil.retornarIdadeEmAnosMesesSemanasDias(data));*/
		System.out.println(DataUtil.retornaDataAtualExtenso());
		
	}
	

}
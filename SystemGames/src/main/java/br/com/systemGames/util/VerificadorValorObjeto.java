package br.com.systemGames.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;



public class VerificadorValorObjeto {
	
	public static Long retornaLongValorObjetoOuZero(Long valorObjetoInteiro) {
		
		if (valorObjetoInteiro == null) {
			return new Long(0);
		}
		else{
			return valorObjetoInteiro;
		}

	}
	
	public static int retornaIntValorObjetoOuZero(Integer valorObjetoInteiro) {
		
		if (valorObjetoInteiro == null) {
			return 0;
		}
		else{
			return valorObjetoInteiro;
		}

	}
	
	public static String retornaIntValorObjetoOuZeroOutVazio(Integer valorObjetoInteiro) {
		
		if (valorObjetoInteiro == null) {
			return "";
		}
		else{
			return String.valueOf(valorObjetoInteiro);
		}

	}
	
	public static java.sql.Date retornaSQLDateValorObjetoOuNull(Date valorObjetoUtilDate) {
		
		if (valorObjetoUtilDate == null) {
			return null;
		}
		else{
			return new java.sql.Date(valorObjetoUtilDate.getTime());
		}

	}
	
	
	public static Date retornaUtilDateValorObjetoOuNull(Date valorObjetoUtilDate) {
		
		if (valorObjetoUtilDate == null) {
			return null;
		}
		else{
			return valorObjetoUtilDate;
		}

	}
	
	/*
	 * Tratamento do Horario de Verão
	 * Primeiramente caso o informe no flex 01/11/2012 ao criar a nova data acaba retornando 31/10/2012 11:00:00 devido ao Horario de Verao
	 * Valor retornado : (adiciona 3 horas para que retorne 'Date valorObjetoUtilDate 02:00:00', já que neste caso reduz-se 1 hora para Nordeste)
	 */	
	public static java.sql.Date retornaSQLDateTratandoFusoHorarioBrasileiro(Date valorObjetoUtilDate) {
		
		if (valorObjetoUtilDate == null) {
			return null;
		}
		else{
			return new java.sql.Date(valorObjetoUtilDate.getTime()+ 180 * 60 * 1000);
		}

	}

	public static String retornaStringValorObjetoOuNull(String valorObjetoString) {
		
		if(valorObjetoString == null){
			return null;
		}
		else{
			
			if (valorObjetoString.trim().equals("") || valorObjetoString.trim().equals("null")) {
				return null;
			}
			else{
				return valorObjetoString;
			}
		}	
	}
	
	
	
	public static String retornaStringValorObjetoOuNullOutVazio(String valorObjetoString) {
		
		if(valorObjetoString == null){
			return "";
		}
		else{
			
			if (valorObjetoString.trim().equals("")) {
				return "";
			}
			else{
				return valorObjetoString;
			}
		}	
	}
	
	public static String retornaStringValorDoubleFormatado(Double valor, Integer numeroDeCasasDecimais) {
		
		if(valor == null){
			return "";
		}
		else{
			DecimalFormat decimalFormat = new DecimalFormat(); 
			decimalFormat.setMinimumFractionDigits(numeroDeCasasDecimais); 
			
			return decimalFormat.format(valor);
		}	
	}
	
	public static String retornaStringValorBigDecimalFormatado(BigDecimal valor, Integer numeroDeCasasDecimais) {
		
		DecimalFormat decimalFormat = new DecimalFormat(); 
		decimalFormat.setMinimumFractionDigits(numeroDeCasasDecimais); 
		
		return decimalFormat.format(valor);
	}
	
	public static Double retornaDoubleValorObjetoOuZero(Double valorObjetoDouble) {
		
		if (valorObjetoDouble == null) {
			return 0.0;
		}
		else{
			return valorObjetoDouble;
		}

	}

	
}

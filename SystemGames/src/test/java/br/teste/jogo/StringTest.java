package br.teste.jogo;

public class StringTest {

	public static void main(String[] args) {
		
		/*
		String texto = "tipcal=Cálculo Mensal, perref=Abril / 2015, codcal=405, mais_algo_aqui=1111";
        int inicio = texto.indexOf("codcal=")+7;
        int fim = texto.indexOf(",", inicio);
        System.out.println(texto.substring(inicio, fim));
        */
		
        String texto = "21:11:12";
        int inicio = texto.indexOf("");
        int fim = texto.indexOf(":", inicio);
        
        int inicio1 = texto.indexOf(":") + 1;
        int fim2 = texto.indexOf(":", inicio1);
        
        int inicio3 = texto.indexOf(":") + 4;
        
        System.out.println("Hora: " + texto.substring(inicio, fim));
        
        System.out.println("Minuto: " + texto.substring(inicio1, fim2));
        
        System.out.println("Segundos: " + texto.substring(inicio3));
        
        
        String horaInicialJogoRetornoString = texto.substring(inicio, fim) + "" + texto.substring(inicio1, fim2) + "" + texto.substring(inicio3);

        System.out.println(horaInicialJogoRetornoString);

	}

}

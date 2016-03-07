package br.teste.banco;

import java.sql.SQLException;

import javax.naming.NamingException;

import br.com.systemGames.database.Conexao;

public class TesteConexaoBanco {

	
	public static void main(String[] args) throws ClassNotFoundException, SQLException, NamingException {
		
		Conexao.conectar();
		
	}
	
}

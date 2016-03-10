package br.com.systemGames.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import javax.naming.NamingException;

public class Conexao
{

    private static Connection conexao = null;
    private static Boolean autoCommit = false;
    
    public static void conectar()
        throws ClassNotFoundException, SQLException, NamingException
    {
       // Registrar o driver  
        Class.forName( "org.postgresql.Driver" ); 
        
        /*Banco PRODUCAO*/
        //String url = "jdbc:postgresql://192.168.50.200:5432/vita";
        String url = "jdbc:postgresql://localhost:5432/system_games";
        
        
        //Banco Local DESENV
        //String url = "jdbc:postgresql://localhost:5432/vita";
        
        Properties props = new Properties();
        props.setProperty("user","postgres");
        props.setProperty("password","postgres");
        //props.setProperty("ssl","true");
        //props.setProperty("socketTimeout",int);         
       
        // Registrado o driver, estabelecerá uma conexão  
        //conexao = DriverManager.getConnection("jdbc:postgresql://192.168.50.100:5432/bessaste_hosp","postgres","postgres"); 
        //conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/bessaste_hosp","postgres","postgres"); 
        conexao = DriverManager.getConnection(url, props);
        
        
    }

    public static Connection getConexao() throws SQLException
    {
        if(conexao == null)
        {
            try
            {
                conectar();
            }
            catch(ClassNotFoundException ex)
            {
                System.out.println("Erro ao carregar o driver de conex\343o!");
            }
            catch(SQLException ex)
            {
                System.out.println((new StringBuilder("Erro ao conectar com o banco de dados! Motivo: ")).append(ex.getMessage()).toString());
            }
            catch(NamingException ex)
            {
                System.out.println((new StringBuilder("Erro ao achar a fonte de dados JNDI! Motivo: ")).append(ex.getMessage()).toString());
            }
        }
                
        if(Conexao.autoCommit==false){
    		Conexao.setarAutoCommitParaFalse();	
        }
                     
        return conexao;
    }

    public static void desconectar()
    {
        try
        {
            conexao.close();
            conexao = null;
        }
        catch(SQLException e)
        {
            System.out.println("Erro ao desconectar aplica\347\343o!");
        }
    }
    
   
    
    public static void setarAutoCommitParaTrue() throws SQLException{
    	
		conexao.setAutoCommit(true);		
    	
    }
    
    public static void setarAutoCommitParaFalse() throws SQLException{
    	
    	verificarConexaoAoSetarAutoCommit();
		conexao.setAutoCommit(false);		
    	
    }
    
    public static void rollback() throws SQLException{
    	
		conexao.rollback();		
		
    	
    }

	public static Boolean getAutoCommit() {
		return autoCommit;
	}

	public static void setAutoCommit(Boolean autoCommit) {
		Conexao.autoCommit = autoCommit;
	}
	
	public static void verificarConexaoAoSetarAutoCommit() throws SQLException {
		if(conexao == null)
        {
			getConexao();
        }
	}
		

	
	public static String verificarResultadosDaExecucaoDeProceduresValidandoCommit(ArrayList<String> resultadosExecucoesProcedures) throws SQLException {
		
		Integer contadorResultados = 0;
		String retornoExecucaoVerificacaoProcedures=null;
		
		for (String resultadoExecucaoProcedure : resultadosExecucoesProcedures) {
			
			contadorResultados++;
			
			/*Verificar se a execução de cada procedure ocorreu algum erro, 
			 * caso ocorra será dado rollback e setado o AutoCommit para False*/
			if(!resultadoExecucaoProcedure.equals("OK")){
				
				retornoExecucaoVerificacaoProcedures = resultadoExecucaoProcedure;
				//Conexao.setarAutoCommitParaFalse();
				conexao.rollback();
				break;
			}
			
			/*Ao chegar na última verificação, verificará o resultado da última procedure, 
			 * caso seja OK será dado Commit e setado o AutoCommit para True*/
			if(resultadosExecucoesProcedures.size()==contadorResultados &&
			   resultadoExecucaoProcedure.equals("OK")){
				
				retornoExecucaoVerificacaoProcedures = resultadoExecucaoProcedure;
				//Conexao.setarAutoCommitParaTrue();
				conexao.commit();
			}
			
		}
		
		return retornoExecucaoVerificacaoProcedures;
	}
	

	public static String teste() {
		
		
		return "OK";
	}
}

package br.com.systemGames.excecao;
import br.com.systemGames.database.Conexao;


public class DAOException extends VacinacaoException  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DAOException() {
	}
	
	public DAOException(Exception erro) {
		
		super(erro);
		
		try {
			//Cancelar a execução de transações pendentes, assim que ocorrer uma exceção
    		Conexao.rollback();
    		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public DAOException(String mensagem) {
		
		super(mensagem);	
		
		try {
			//Cancelar a execução de transações pendentes, assim que ocorrer uma exceção
    		Conexao.rollback();
    		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public DAOException(String mensagem, Exception erro) {
		
		super(mensagem, erro);
		
		try {
			//Cancelar a execução de transações pendentes, assim que ocorrer uma exceção
    		Conexao.rollback();
    		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
}
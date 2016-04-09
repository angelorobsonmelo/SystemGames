package br.com.systemGames.excecao;
import br.com.systemGames.database.Conexao;

/**
 * exce��o que ser� lan�ada pela camada de BO
 */
public class BOException extends VacinacaoException  {

	public BOException() {
	}
	public BOException(Exception e) {
		super(e);		
	}
	public BOException(String mensagem, Exception e) {
		super(mensagem,e);			
	}
	
	public BOException(String mensagem) {
		super(mensagem);
		
		try {
			//Cancelar a execução de transações pendentes, assim que ocorrer uma exceção
    		Conexao.rollback();
    		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
	
}
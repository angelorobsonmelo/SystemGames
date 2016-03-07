package br.com.systemGames.jogo.model;



public class ConfiguracaoJogoVO {

	private Integer sequencial;
	private JogoVO jogoVO;
	private Boolean finalizarAutomaticamente;
	private Boolean jogoFinalizado;
	
	public ConfiguracaoJogoVO() {
		
	}

	public Integer getSequencial() {
		return sequencial;
	}

	public void setSequencial(Integer sequencial) {
		this.sequencial = sequencial;
	}

	public JogoVO getJogoVO() {
		return jogoVO;
	}

	public void setJogoVO(JogoVO jogoVO) {
		this.jogoVO = jogoVO;
	}

	public Boolean getFinalizarAutomaticamente() {
		return finalizarAutomaticamente;
	}

	public void setFinalizarAutomaticamente(Boolean finalizarAutomaticamente) {
		this.finalizarAutomaticamente = finalizarAutomaticamente;
	}

	public Boolean getJogoFinalizado() {
		return jogoFinalizado;
	}

	public void setJogoFinalizado(Boolean jogoFinalizado) {
		this.jogoFinalizado = jogoFinalizado;
	}
	
}

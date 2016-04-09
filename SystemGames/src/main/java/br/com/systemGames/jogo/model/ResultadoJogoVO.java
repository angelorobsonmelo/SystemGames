package br.com.systemGames.jogo.model;

public class ResultadoJogoVO {

	private Integer sequencial;
	private JogoVO jogoVO;
	private Integer resultadoCasa;
	private Integer resultadoFora;
	
	public ResultadoJogoVO() {
		
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

	public Integer getResultadoCasa() {
		return resultadoCasa;
	}

	public void setResultadoCasa(Integer resultadoCasa) {
		this.resultadoCasa = resultadoCasa;
	}

	public Integer getResultadoFora() {
		return resultadoFora;
	}

	public void setResultadoFora(Integer resultadoFora) {
		this.resultadoFora = resultadoFora;
	}
	
}

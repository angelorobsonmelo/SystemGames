package br.com.systemGames.jogo.model;

import br.com.systemGames.usuario.model.UsuarioVO;

public class LimiteApostaVO {

	private Integer sequencial;
	private JogoVO jogoVO;
	private Double casa;
	private Double fora;
	private Double empate;
	private Double golEMeio;
	private Double duplaChance;
	private Double ambos;
	private Double limiteCasa;
	private Double limiteFora;
	private Double limiteEmpate;
	private Double limiteGolEMeio;
	private Double limiteDuplaChance;
	private double limiteAmbos;
	private Double limiteIndividual;
	private UsuarioVO usuarioVO;
	
	public LimiteApostaVO() {

		usuarioVO = new UsuarioVO();
		jogoVO = new JogoVO();
		
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

	public Double getCasa() {
		return casa;
	}

	public void setCasa(Double casa) {
		this.casa = casa;
	}

	public Double getFora() {
		return fora;
	}

	public void setFora(Double fora) {
		this.fora = fora;
	}

	public Double getEmpate() {
		return empate;
	}

	public void setEmpate(Double empate) {
		this.empate = empate;
	}

	public Double getGolEMeio() {
		return golEMeio;
	}

	public void setGolEMeio(Double golEMeio) {
		this.golEMeio = golEMeio;
	}

	public Double getDuplaChance() {
		return duplaChance;
	}

	public void setDuplaChance(Double duplaChance) {
		this.duplaChance = duplaChance;
	}

	public Double getAmbos() {
		return ambos;
	}

	public void setAmbos(Double ambos) {
		this.ambos = ambos;
	}

	public Double getLimiteCasa() {
		return limiteCasa;
	}

	public void setLimiteCasa(Double limiteCasa) {
		this.limiteCasa = limiteCasa;
	}

	public Double getLimiteFora() {
		return limiteFora;
	}

	public void setLimiteFora(Double limiteFora) {
		this.limiteFora = limiteFora;
	}

	public Double getLimiteEmpate() {
		return limiteEmpate;
	}

	public void setLimiteEmpate(Double limiteEmpate) {
		this.limiteEmpate = limiteEmpate;
	}

	public Double getLimiteGolEMeio() {
		return limiteGolEMeio;
	}

	public void setLimiteGolEMeio(Double limiteGolEMeio) {
		this.limiteGolEMeio = limiteGolEMeio;
	}

	public Double getLimiteDuplaChance() {
		return limiteDuplaChance;
	}

	public void setLimiteDuplaChance(Double limiteDuplaChance) {
		this.limiteDuplaChance = limiteDuplaChance;
	}

	public double getLimiteAmbos() {
		return limiteAmbos;
	}

	public void setLimiteAmbos(double limiteAmbos) {
		this.limiteAmbos = limiteAmbos;
	}

	public Double getLimiteIndividual() {
		return limiteIndividual;
	}

	public void setLimiteIndividual(Double limiteIndividual) {
		this.limiteIndividual = limiteIndividual;
	}

	public UsuarioVO getUsuarioVO() {
		return usuarioVO;
	}

	public void setUsuarioVO(UsuarioVO usuarioVO) {
		this.usuarioVO = usuarioVO;
	}
	
}

package br.com.systemGames.usuario.model;



public class ConfiguracaoCambistaVO{

	private Integer sequencial;
	private Double limiteMaximoVendaDiario;
	private Double limiteMaximoVendaIndividual;
	private CambistaVO cambistaVO;
	private String observacao;
	private Double comissao1;
	private Double comissao2;
	private Double comissao3;
	
	
	public ConfiguracaoCambistaVO() {
		
	}
	
	public Integer getSequencial() {
		return sequencial;
	}
	public void setSequencial(Integer sequencial) {
		this.sequencial = sequencial;
	}
	public Double getLimiteMaximoVendaDiario() {
		return limiteMaximoVendaDiario;
	}
	public void setLimiteMaximoVendaDiario(Double limiteMaximoVendaDiario) {
		this.limiteMaximoVendaDiario = limiteMaximoVendaDiario;
	}
	public Double getLimiteMaximoVendaIndividual() {
		return limiteMaximoVendaIndividual;
	}
	public void setLimiteMaximoVendaIndividual(Double limiteMaximoVendaIndividual) {
		this.limiteMaximoVendaIndividual = limiteMaximoVendaIndividual;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	

	public CambistaVO getCambistaVO() {
		return cambistaVO;
	}

	public void setCambistaVO(CambistaVO cambistaVO) {
		this.cambistaVO = cambistaVO;
	}

	public Double getComissao1() {
		return comissao1;
	}

	public void setComissao1(Double comissao1) {
		this.comissao1 = comissao1;
	}

	public Double getComissao2() {
		return comissao2;
	}

	public void setComissao2(Double comissao2) {
		this.comissao2 = comissao2;
	}

	public Double getComissao3() {
		return comissao3;
	}

	public void setComissao3(Double comissao3) {
		this.comissao3 = comissao3;
	}
	
	
	
}

package br.com.systemGames.usuario.cambista.model;

import br.com.systemGames.usuario.model.UsuarioVO;


public class ConfiguracaoCambistaVO extends UsuarioVO{

	private Integer sequencial;
	private Double limiteMaximoVendaDiario;
	private Double limiteMaximoVendaIndividual;
	private UsuarioVO codigoUsuario;
	private String observacao;
	private Integer comissao1;
	private Integer comissao2;
	private Integer comissao3;
	
	
	public ConfiguracaoCambistaVO() {
		codigoUsuario = new UsuarioVO();
	}
	public UsuarioVO getCodigoUsuario() {
		return codigoUsuario;
	}
	public void setCodigoUsuario(UsuarioVO codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
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
	
	public Integer getComissao1() {
		return comissao1;
	}
	public void setComissao1(Integer comissao1) {
		this.comissao1 = comissao1;
	}
	public Integer getComissao2() {
		return comissao2;
	}
	public void setComissao2(Integer comissao2) {
		this.comissao2 = comissao2;
	}
	public Integer getComissao3() {
		return comissao3;
	}
	public void setComissao3(Integer comissao3) {
		this.comissao3 = comissao3;
	}
	
	
	
}

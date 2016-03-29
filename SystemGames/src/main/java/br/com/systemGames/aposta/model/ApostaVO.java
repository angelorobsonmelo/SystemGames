package br.com.systemGames.aposta.model;

import java.util.ArrayList;

import br.com.systemGames.usuario.model.CambistaVO;

public class ApostaVO {
	
	private Integer sequencial;
	private CambistaVO cambistaVO;
	private String nomeApostador;
	private Double valApostado;
	private Double valComissao;
	private Double valRetornoPossivel;
	private String dthInclusao;
	private JogoApostadoVO jogoApostadoVO2;
	private ArrayList<JogoApostadoVO> jogoApostadoVO;
	
		
	public ApostaVO() {
		cambistaVO = new CambistaVO();
		jogoApostadoVO = new ArrayList<JogoApostadoVO>();
		jogoApostadoVO2 = new JogoApostadoVO();
	}
	
	
	public Integer getSequencial() {
		return sequencial;
	}
	public void setSequencial(Integer sequencial) {
		this.sequencial = sequencial;
	}
	public CambistaVO getCambistaVO() {
		return cambistaVO;
	}
	public void setCambistaVO(CambistaVO cambistaVO) {
		this.cambistaVO = cambistaVO;
	}
	public String getNomeApostador() {
		return nomeApostador;
	}
	public void setNomeApostador(String nomeApostador) {
		this.nomeApostador = nomeApostador;
	}
	public Double getValApostado() {
		return valApostado;
	}
	public void setValApostado(Double valApostado) {
		this.valApostado = valApostado;
	}
	public Double getValComissao() {
		return valComissao;
	}
	public void setValComissao(Double valComissao) {
		this.valComissao = valComissao;
	}
	public Double getValRetornoPossivel() {
		return valRetornoPossivel;
	}
	public void setValRetornoPossivel(Double valRetornoPossivel) {
		this.valRetornoPossivel = valRetornoPossivel;
	}
	public String getDthInclusao() {
		return dthInclusao;
	}
	public void setDthInclusao(String dthInclusao) {
		this.dthInclusao = dthInclusao;
	}


	public ArrayList<JogoApostadoVO> getJogoApostadoVO() {
		return jogoApostadoVO;
	}


	public void setJogoApostadoVO(ArrayList<JogoApostadoVO> jogoApostadoVO) {
		this.jogoApostadoVO = jogoApostadoVO;
	}


	public JogoApostadoVO getJogoApostadoVO2() {
		return jogoApostadoVO2;
	}


	public void setJogoApostadoVO2(JogoApostadoVO jogoApostadoVO2) {
		this.jogoApostadoVO2 = jogoApostadoVO2;
	}
	
	
	

}

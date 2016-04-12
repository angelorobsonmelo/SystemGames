package br.com.systemGames.aposta.model;

import java.util.ArrayList;
import java.util.Date;

import br.com.systemGames.jogo.model.ConfiguracaoJogoVO;
import br.com.systemGames.jogo.model.ResultadoJogoVO;
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
	private ResultadoJogoVO resultadoJogoVO; 
	private Integer qtdJogos;
	private ConfiguracaoJogoVO configuracaoJogoVO;
	private String resultadoAposta;
	private String status;
	
	private Date dataInicial;
	private Date dataFinal;
	private java.sql.Date teste;
		
	public ApostaVO() {
		cambistaVO = new CambistaVO();
		jogoApostadoVO = new ArrayList<JogoApostadoVO>();
		jogoApostadoVO2 = new JogoApostadoVO();
		configuracaoJogoVO = new ConfiguracaoJogoVO();
		resultadoJogoVO = new ResultadoJogoVO();
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


	public ResultadoJogoVO getResultadoJogoVO() {
		return resultadoJogoVO;
	}


	public void setResultadoJogoVO(ResultadoJogoVO resultadoJogoVO) {
		this.resultadoJogoVO = resultadoJogoVO;
	}


	public Integer getQtdJogos() {
		return qtdJogos;
	}


	public void setQtdJogos(Integer qtdJogos) {
		this.qtdJogos = qtdJogos;
	}


	public Date getDataInicial() {
		return dataInicial;
	}


	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}


	public Date getDataFinal() {
		return dataFinal;
	}


	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}


	public ConfiguracaoJogoVO getConfiguracaoJogoVO() {
		return configuracaoJogoVO;
	}


	public void setConfiguracaoJogoVO(ConfiguracaoJogoVO configuracaoJogoVO) {
		this.configuracaoJogoVO = configuracaoJogoVO;
	}


	public String getResultadoAposta() {
		return resultadoAposta;
	}


	public void setResultadoAposta(String resultadoAposta) {
		this.resultadoAposta = resultadoAposta;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public java.sql.Date getTeste() {
		return teste;
	}


	public void setTeste(java.sql.Date teste) {
		this.teste = teste;
	}
	
	
	
	
	

}

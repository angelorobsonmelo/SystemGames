package br.com.systemGames.jogo.model;

import java.util.Date;

import br.com.systemGames.util.DataUtil;

public class JogoVO {

	private Integer sequencial;
	private String jogo;
	private CampeonatoVO campeonatoVO;
	private EsporteVO esporteVO;
	private Date dataJogo;
	private String dataJogoFormatada;
	private String dataJogoFormatadaBasica;
	private String horaInicialJogo;
	private ConfiguracaoJogoVO configuracaoJogoVO;
	private LimiteApostaVO limiteApostaVO;

	public JogoVO() {

		campeonatoVO = new CampeonatoVO();
		esporteVO = new EsporteVO();
		configuracaoJogoVO = new ConfiguracaoJogoVO();
		limiteApostaVO = new LimiteApostaVO();

	}

	public Integer getSequencial() {
		return sequencial;
	}

	public void setSequencial(Integer sequencial) {
		this.sequencial = sequencial;
	}

	public String getJogo() {
		return jogo;
	}

	public void setJogo(String jogo) {
		this.jogo = jogo;
	}


	public EsporteVO getEsporteVO() {
		return esporteVO;
	}

	public void setEsporteVO(EsporteVO esporteVO) {
		this.esporteVO = esporteVO;
	}

	public Date getDataJogo() {
		return dataJogo;
	}

	public void setDataJogo(Date dataJogo) {
		this.dataJogo = dataJogo;
		this.dataJogoFormatada = DataUtil.formatarDataComHoraRetornandoString(dataJogo);
		this.dataJogoFormatadaBasica = DataUtil.formatarDataRetornandoString(dataJogo);

	}

	public String getDataJogoFormatada() {
		return dataJogoFormatada;
	}

	public void setDataJogoFormatada(String dataJogoFormatada) {
		this.dataJogoFormatada = dataJogoFormatada;

	}

	public String getDataJogoFormatadaBasica() {
		return dataJogoFormatadaBasica;
	}

	public void setDataJogoFormatadaBasica(String dataJogoFormatadaBasica) {
		this.dataJogoFormatadaBasica = dataJogoFormatadaBasica;
	}

	public String getHoraInicialJogo() {
		return horaInicialJogo;
	}

	public void setHoraInicialJogo(String horaInicialJogo) {
		this.horaInicialJogo = horaInicialJogo;
	}

	public CampeonatoVO getCampeonatoVO() {
		return campeonatoVO;
	}

	public void setCampeonatoVO(CampeonatoVO campeonatoVO) {
		this.campeonatoVO = campeonatoVO;
	}

	public ConfiguracaoJogoVO getConfiguracaoJogoVO() {
		return configuracaoJogoVO;
	}

	public void setConfiguracaoJogoVO(ConfiguracaoJogoVO configuracaoJogoVO) {
		this.configuracaoJogoVO = configuracaoJogoVO;
	}

	public LimiteApostaVO getLimiteApostaVO() {
		return limiteApostaVO;
	}

	public void setLimiteApostaVO(LimiteApostaVO limiteApostaVO) {
		this.limiteApostaVO = limiteApostaVO;
	}



}

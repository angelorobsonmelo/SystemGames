package br.com.systemGames.usuario.model;


public class CambistaVO extends UsuarioVO {

	private UsuarioVO usuarioVO;
	private	ConfiguracaoCambistaVO configuracaoCambistaVO;
	private String observacao;

	public CambistaVO() {
		usuarioVO = new UsuarioVO();
		configuracaoCambistaVO = new ConfiguracaoCambistaVO();
	}

	public UsuarioVO getUsuarioVO() {
		return usuarioVO;
	}

	public void setUsuarioVO(UsuarioVO usuarioVO) {
		this.usuarioVO = usuarioVO;
	}

	public ConfiguracaoCambistaVO getConfiguracaoCambistaVO() {
		return configuracaoCambistaVO;
	}

	public void setConfiguracaoCambistaVO(
			ConfiguracaoCambistaVO configuracaoCambistaVO) {
		this.configuracaoCambistaVO = configuracaoCambistaVO;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

}

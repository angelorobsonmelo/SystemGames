package br.com.systemGames.usuario.model;



import br.com.systemGames.usuario.cambista.model.ConfiguracaoCambistaVO;


public class UsuarioVO {
	
	private Integer sequencial;
	private TipoUsuarioVO codigoTipoUsuario;
	private String nomeUsuario;
	private String email;
	private String login;
	private String senha;
	private String cpf;
	private String numeroRg;
	private String contato;
	private String endereco;
	private String numeroEndereco;
	private String complemento;
	private String bairro;
	private String cidade;
	private String cep;
	private String uf;
	ConfiguracaoCambistaVO configuracaoCambistaVO;

	

	public UsuarioVO() {
		codigoTipoUsuario = new TipoUsuarioVO();

	}
	public ConfiguracaoCambistaVO getConfiguracaoCambistaVO() {
		return configuracaoCambistaVO;
	}
	public void setConfiguracaoCambistaVO(
			ConfiguracaoCambistaVO configuracaoCambistaVO) {
		this.configuracaoCambistaVO = configuracaoCambistaVO;
	}
	public Integer getSequencial() {
		return sequencial;
	}
	public void setSequencial(Integer sequencial) {
		this.sequencial = sequencial;
	}
	public TipoUsuarioVO getCodigoTipoUsuario() {
		return codigoTipoUsuario;
	}
	public void setCodigoTipoUsuario(TipoUsuarioVO codigoTipoUsuario) {
		this.codigoTipoUsuario = codigoTipoUsuario;
	}
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNumeroRg() {
		return numeroRg;
	}
	public void setNumeroRg(String numeroRg) {
		this.numeroRg = numeroRg;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getContato() {
		return contato;
	}
	public void setContato(String contato) {
		this.contato = contato;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getNumeroEndereco() {
		return numeroEndereco;
	}
	public void setNumeroEndereco(String numeroEndereco) {
		this.numeroEndereco = numeroEndereco;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	
	
	

}

package br.dp.model;

public class Instituicao extends BasePojo {

	private String telefone;
	private String cnpj;
	private String logradouro;
	private String numero;
	private String cep;
	private String cpf;
	private Long municipioId;

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(final String telefone) {
		this.telefone = telefone;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(final String cnpj) {
		this.cnpj = cnpj;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(final String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(final String numero) {
		this.numero = numero;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(final String cep) {
		this.cep = cep;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(final String cpf) {
		this.cpf = cpf;
	}

	public Long getMunicipioId() {
		return municipioId;
	}

	public void setMunicipioId(final Long municipioId) {
		this.municipioId = municipioId;
	}

	@Override
	public String toString() {
		return "Instituicao [telefone=" + telefone + ", cnpj=" + cnpj + ", logradouro=" + logradouro + ", numero="
				+ numero + ", cep=" + cep + ", cpf=" + cpf + ", municipioId=" + municipioId + "]";
	}

	public Instituicao(final String telefone, final String cnpj, final String logradouro, final String numero,
			final String cep, final String cpf, final Long municipioId) {
		super();
		this.telefone = telefone;
		this.cnpj = cnpj;
		this.logradouro = logradouro;
		this.numero = numero;
		this.cep = cep;
		this.cpf = cpf;
		this.municipioId = municipioId;
	}

}

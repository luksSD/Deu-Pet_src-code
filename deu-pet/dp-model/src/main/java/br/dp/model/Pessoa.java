package br.dp.model;

public class Pessoa extends Usuario {

	private String telefone;
	private String logradouro;
	private String numero;
	private String cep;
	private Long municipioId;

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(final String telefone) {
		this.telefone = telefone;
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

	public Long getMunicipioId() {
		return municipioId;
	}

	public void setMunicipioId(final Long municipioId) {
		this.municipioId = municipioId;
	}

}

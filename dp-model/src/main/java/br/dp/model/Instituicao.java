package br.dp.model;

import java.security.Timestamp;

public class Instituicao extends Usuario {

	public Instituicao(final String nome, final String senha, final String email, final String celular,
			final boolean situacao, final Timestamp data, final boolean aceite, final String tipo) {
		super(nome, senha, email, celular, situacao, data, aceite, tipo);
		// TODO Auto-generated constructor stub
	}

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

}

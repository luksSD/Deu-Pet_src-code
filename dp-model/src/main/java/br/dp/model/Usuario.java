package br.dp.model;

import java.security.Timestamp;

public abstract class Usuario extends BasePojo {

	private String nome;
	private String senha;
	private String email;
	private String celular;
	private boolean situacao;
	private Timestamp dataCadastro;
	private boolean aceite;
	private String tipo;

	public String getNome() {
		return nome;
	}

	public void setNome(final String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(final String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(final String celular) {
		this.celular = celular;
	}

	public boolean isSituacao() {
		return situacao;
	}

	public void setSituacao(final boolean situacao) {
		this.situacao = situacao;
	}

	public Timestamp getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(final Timestamp dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public boolean isAceite() {
		return aceite;
	}

	public void setAceite(final boolean aceite) {
		this.aceite = aceite;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(final String tipo) {
		this.tipo = tipo;
	}

}

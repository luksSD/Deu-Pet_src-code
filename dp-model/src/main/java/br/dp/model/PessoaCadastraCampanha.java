package br.dp.model;

import java.security.Timestamp;

public class PessoaCadastraCampanha {

	private String pessoaId;
	private String campanhaId;
	private String situacao;
	private Timestamp data;

	public String getPessoaId() {
		return pessoaId;
	}

	public void setPessoaId(final String pessoaId) {
		this.pessoaId = pessoaId;
	}

	public String getCampanhaId() {
		return campanhaId;
	}

	public void setCampanhaId(final String campanhaId) {
		this.campanhaId = campanhaId;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(final String situacao) {
		this.situacao = situacao;
	}

	public Timestamp getData() {
		return data;
	}

	public void setData(final Timestamp data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "PessoaCadastraCampanha [pessoaId=" + pessoaId + ", campanhaId=" + campanhaId + ", situacao=" + situacao
				+ ", data=" + data + "]";
	}

	public PessoaCadastraCampanha(final String pessoaId, final String campanhaId, final String situacao,
			final Timestamp data) {
		super();
		this.pessoaId = pessoaId;
		this.campanhaId = campanhaId;
		this.situacao = situacao;
		this.data = data;
	}

}

package br.dp.model;

public class Animal extends BasePojo {

	private String tipo;
	private String sexo;
	private Double peso;
	private String porte;
	private String raca;
	private boolean situacaoAodocao;
	private String temperamento;
	private String pelagemPrimaria;
	private String pelagemSecundaria;

	public String getTipo() {
		return tipo;
	}

	public void setTipo(final String tipo) {
		this.tipo = tipo;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(final String sexo) {
		this.sexo = sexo;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(final Double peso) {
		this.peso = peso;
	}

	public String getPorte() {
		return porte;
	}

	public void setPorte(final String porte) {
		this.porte = porte;
	}

	public String getRaca() {
		return raca;
	}

	public void setRaca(final String raca) {
		this.raca = raca;
	}

	public boolean isSituacaoAodocao() {
		return situacaoAodocao;
	}

	public void setSituacaoAodocao(final boolean situacaoAodocao) {
		this.situacaoAodocao = situacaoAodocao;
	}

	public String getTemperamento() {
		return temperamento;
	}

	public void setTemperamento(final String temperamento) {
		this.temperamento = temperamento;
	}

	public String getPelagemPrimaria() {
		return pelagemPrimaria;
	}

	public void setPelagemPrimaria(final String pelagemPrimaria) {
		this.pelagemPrimaria = pelagemPrimaria;
	}

	public String getPelagemSecunaria() {
		return pelagemSecundaria;
	}

	public void setPelagemSecundaria(final String pelagemSecundaria) {
		this.pelagemSecundaria = pelagemSecundaria;
	}

}

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
	private String pelagemSecunaria;

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
		return pelagemSecunaria;
	}

	public void setPelagemSecunaria(final String pelagemSecunaria) {
		this.pelagemSecunaria = pelagemSecunaria;
	}

	@Override
	public String toString() {
		return "Animal [tipo=" + tipo + ", sexo=" + sexo + ", peso=" + peso + ", porte=" + porte + ", raca=" + raca
				+ ", situacaoAodocao=" + situacaoAodocao + ", temperamento=" + temperamento + ", pelagemPrimaria="
				+ pelagemPrimaria + ", pelagemSecunaria=" + pelagemSecunaria + "]";
	}

	public Animal(final String tipo, final String sexo, final Double peso, final String porte, final String raca,
			final boolean situacaoAodocao, final String temperamento, final String pelagemPrimaria,
			final String pelagemSecunaria) {
		super();
		this.tipo = tipo;
		this.sexo = sexo;
		this.peso = peso;
		this.porte = porte;
		this.raca = raca;
		this.situacaoAodocao = situacaoAodocao;
		this.temperamento = temperamento;
		this.pelagemPrimaria = pelagemPrimaria;
		this.pelagemSecunaria = pelagemSecunaria;
	}

}

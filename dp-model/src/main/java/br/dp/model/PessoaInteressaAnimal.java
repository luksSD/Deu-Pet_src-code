package br.dp.model;

import java.security.Timestamp;

public class PessoaInteressaAnimal {

	private String pessoaId;
	private String animalId;
	private Timestamp data;

	public String getPessoaId() {
		return pessoaId;
	}

	public void setPessoaId(final String pessoaId) {
		this.pessoaId = pessoaId;
	}

	public String getAnimalId() {
		return animalId;
	}

	public void setAnimalId(final String animalId) {
		this.animalId = animalId;
	}

	public Timestamp getData() {
		return data;
	}

	public void setData(final Timestamp data) {
		this.data = data;
	}

}

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

	@Override
	public String toString() {
		return "PessoaInteressaAnimal [pessoaId=" + pessoaId + ", animalId=" + animalId + ", data=" + data + "]";
	}

	public PessoaInteressaAnimal(final String pessoaId, final String animalId, final Timestamp data) {
		super();
		this.pessoaId = pessoaId;
		this.animalId = animalId;
		this.data = data;
	}

}

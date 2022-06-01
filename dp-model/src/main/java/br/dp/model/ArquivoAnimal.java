package br.dp.model;

public class ArquivoAnimal extends BasePojo {

	private Long animalID;
	private String fotos;

	public Long getAnimalID() {
		return animalID;
	}

	public void setAnimalID(final Long animalID) {
		this.animalID = animalID;
	}

	public String getFotos() {
		return fotos;
	}

	public void setFotos(final String fotos) {
		this.fotos = fotos;
	}

	@Override
	public String toString() {
		return "ArquivoAnimal [animalID=" + animalID + ", fotos=" + fotos + "]";
	}

	public ArquivoAnimal(final Long animalID, final String fotos) {
		super();
		this.animalID = animalID;
		this.fotos = fotos;
	}

}

package br.dp.model;

public class ArquivoCampanha extends BasePojo {

	private Long CampanhaId;
	private String fotos;

	public Long getCampanhaId() {
		return CampanhaId;
	}

	public void setCampanhaId(final Long campanhaId) {
		CampanhaId = campanhaId;
	}

	public String getFotos() {
		return fotos;
	}

	public void setFotos(final String fotos) {
		this.fotos = fotos;
	}

}

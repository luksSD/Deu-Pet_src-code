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

	@Override
	public String toString() {
		return "ArquivoCampanha [CampanhaId=" + CampanhaId + ", fotos=" + fotos + ", getCampanhaId()=" + getCampanhaId()
				+ ", getFotos()=" + getFotos() + ", getId()=" + getId() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}

	public ArquivoCampanha(final Long campanhaId, final String fotos) {
		super();
		CampanhaId = campanhaId;
		this.fotos = fotos;
	}

}

package br.dp.model;

public class Formulario extends BasePojo {

	private String url;
	private Long campanhaId;

	public String getUrl() {
		return url;
	}

	public void setUrl(final String url) {
		this.url = url;
	}

	public Long getCampanhaId() {
		return campanhaId;
	}

	public void setCampanhaId(final Long campanhaId) {
		this.campanhaId = campanhaId;
	}

}

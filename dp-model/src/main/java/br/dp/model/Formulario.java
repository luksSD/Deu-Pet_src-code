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

	@Override
	public String toString() {
		return "Formulario [url=" + url + ", campanhaId=" + campanhaId + "]";
	}

	public Formulario(final String url, final Long campanhaId) {
		super();
		this.url = url;
		this.campanhaId = campanhaId;
	}

}

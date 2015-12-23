package com.formandview;

import com.enums.UrlImgHabilidade;
import com.model.Habilidade;

public class VisualizaHabilidade {

	private UrlImgHabilidade url;
	private Habilidade habilidade;

	public VisualizaHabilidade() {
	}

	public VisualizaHabilidade(UrlImgHabilidade url, Habilidade habilidade) {
		super();
		this.url = url;
		this.habilidade = habilidade;
	}

	public UrlImgHabilidade getUrl() {
		return url;
	}

	public void setUrl(UrlImgHabilidade url) {
		this.url = url;
	}

	public Habilidade getHabilidade() {
		return habilidade;
	}

	public void setHabilidade(Habilidade habilidade) {
		this.habilidade = habilidade;
	}

}

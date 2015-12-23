package com.formandview;

import com.enums.UrlImgPersonagem;
import com.model.Personagem;

public class VisualizaPersonagem {

	private Personagem personagem;
	private UrlImgPersonagem url;

	public VisualizaPersonagem() {
	}

	public VisualizaPersonagem(Personagem personagem, UrlImgPersonagem url) {
		super();
		this.personagem = personagem;
		this.url = url;
	}

	public Personagem getPersonagem() {
		return personagem;
	}

	public void setPersonagem(Personagem personagem) {
		this.personagem = personagem;
	}

	public UrlImgPersonagem getUrl() {
		return url;
	}

	public void setUrl(UrlImgPersonagem url) {
		this.url = url;
	}

}

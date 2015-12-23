package com.enums;

public enum UrlImgPersonagem {
	PERSON1("Person 1", "capitaoamerica.png"), PERSON2("Person 2", "homemdeferro.png"), PERSON3("Person 3",
			"batman.png");

	UrlImgPersonagem(String nome, String url) {
		this.nome = nome;
		this.url = url;
	}

	private String url;
	private String nome;

	public String getNome() {
		return this.nome;
	}

	public String getUrl() {
		return this.url;
	}

	public static UrlImgPersonagem identificarUrlPersonagem(String nome) {
		if (nome.equals(UrlImgPersonagem.PERSON1.nome))
			return UrlImgPersonagem.PERSON1;
		else if (nome.equals(UrlImgPersonagem.PERSON2.nome))
			return UrlImgPersonagem.PERSON2;
		else
			return UrlImgPersonagem.PERSON3;
	}
}

package com.enums;

public enum UrlImgHabilidade {
	HABILIDADE1("Habilidade Person 1", "capitaoamericahabilidade.png"), HABILIDADE2("Habilidade Person 2",
			"homemdeferrohabilidade.png"), HABILIDADE3("Habilidade Person 3", "batmanhabilidade.png");

	UrlImgHabilidade(String nome, String url) {
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

	public static UrlImgHabilidade identificarUrlHabilidade(String nome) {
		if (nome.equals(UrlImgHabilidade.HABILIDADE1.nome))
			return UrlImgHabilidade.HABILIDADE1;
		else if (nome.equals(UrlImgHabilidade.HABILIDADE2.nome))
			return UrlImgHabilidade.HABILIDADE2;
		else
			return UrlImgHabilidade.HABILIDADE3;
	}
}

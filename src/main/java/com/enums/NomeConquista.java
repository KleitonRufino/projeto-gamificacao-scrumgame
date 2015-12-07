package com.enums;

public enum NomeConquista {

	AMGAPI("Amigo em Apuros I"), AMGAPII("Amigo em Apuros II"), AMGAPIII("Amigo em Apuros III"),CONQI("Conquistador I"), CONQII(
			"Conquistador II"), CONQIII("Conquistador III"), TRAEQ("Trabalhando com a equipe"), HERSPRINT(
					"Heroi da Sprint");

	NomeConquista(String nome) {
		this.nome = nome;
	}

	private String nome;

	public String getNome() {
		return this.nome;
	}
}

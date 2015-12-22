package com.enums;

public enum NomeConquista {

	AMGAPI("Amigo em Apuros I", 1, 1), AMGAPII("Amigo em Apuros II", 5, 2), AMGAPIII("Amigo em Apuros III", 9,
			3), AMGAPIV("Amigo em Apuros IV", 14, 4), CONQI("Conquistador I", 1, 1), CONQII("Conquistador II", 4,
					2), CONQIII("Conquistador III", 7, 3), CONQIV("Conquistador IV", 10,
							4), TRAEQ("Trabalhando com a equipe", 0, 4), HERSPRINT("Heroi da Sprint", 0, 4);

	NomeConquista(String nome, int numero, int recompensa) {
		this.nome = nome;
		this.numero = numero;
		this.recompensa = recompensa;
	}

	private int numero;
	private int recompensa;
	private String nome;

	public String getNome() {
		return this.nome;
	}

	public int getNumero() {
		return this.numero;
	}

	public int getRecompensa() {
		return this.recompensa;
	}
}

package com.enums;

public enum NivelExperiencia {

	NULL("Nenhum nivel de experiência", 0), UM("Jedi Iniciante (Youngling)", 2), DOIS("Padawan", 3), TRES("Cavaleiro Jedi",
			4), QUATRO("Mestre Jedi", 5);

	NivelExperiencia(String nome, int bonus) {
		this.nome = nome;
		this.bonus = bonus;
	}

	private String nome;
	private int bonus;

	public String getNome() {
		return nome;
	}

	public int getBonus() {
		return bonus;
	}

}

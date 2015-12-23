package com.enums;

public enum Usavel {
	UNICO("Usar uma vez"), RODADA("Usar na rodada"), SEMPRE("User Sempre");

	Usavel(String descricao) {
		this.descricao = descricao;
	}

	private String descricao;

	public String getDescricao() {
		return this.descricao;
	}

}

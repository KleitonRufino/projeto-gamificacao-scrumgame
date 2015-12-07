package com.formandview;

import java.io.Serializable;

public class FormCooperacao implements Serializable {

	private static final long serialVersionUID = 4697325296632027587L;
	private String jogadorQueAjudei;
	private int qtd;

	public String getJogadorQueAjudei() {
		return jogadorQueAjudei;
	}

	public void setJogadorQueAjudei(String jogadorQueAjudei) {
		this.jogadorQueAjudei = jogadorQueAjudei;
	}

	public int getQtd() {
		return qtd;
	}

	public void setQtd(int qtd) {
		this.qtd = qtd;
	}

}

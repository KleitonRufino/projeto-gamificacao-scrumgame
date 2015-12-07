package com.model;

public enum Pontuacao {

	CINCOPTS(5), DEZPTS(10), QUINZE(15), VINTEPTS(20), VINTEECINCOPTS(25), TRINTAPTS(30);

	Pontuacao(int pts) {
		this.pts = pts;
	}

	private int pts;

	public int getPts() {
		return this.pts;
	}
}

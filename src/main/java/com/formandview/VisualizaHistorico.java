package com.formandview;

import com.model.Rodada;

public class VisualizaHistorico {

	private VisualizaConquista conquista;
	private Rodada rodada;

	public VisualizaHistorico(Rodada rodada, VisualizaConquista conquista) {
		this.conquista = conquista;
		this.rodada = rodada;
	}

	public VisualizaConquista getConquista() {
		return conquista;
	}

	public void setConquista(VisualizaConquista conquista) {
		this.conquista = conquista;
	}

	public Rodada getRodada() {
		return rodada;
	}

	public void setRodada(Rodada rodada) {
		this.rodada = rodada;
	}
}

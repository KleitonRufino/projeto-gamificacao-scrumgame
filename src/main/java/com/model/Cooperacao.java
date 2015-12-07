package com.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Cooperacao {

	@Id
	@GeneratedValue
	private Long id;
	private Date data;
	@OneToOne
	private Jogador jogadorAjudado;
	@OneToOne
	private Rodada rodada;

	public Cooperacao() {
	}

	public Cooperacao(Date data, Jogador jogadorAjudado) {
		super();
		this.data = data;
		this.jogadorAjudado = jogadorAjudado;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Jogador getJogadorAjudado() {
		return jogadorAjudado;
	}

	public void setJogadorAjudado(Jogador jogadorAjudado) {
		this.jogadorAjudado = jogadorAjudado;
	}

	public Rodada getRodada() {
		return rodada;
	}

	public void setRodada(Rodada rodada) {
		this.rodada = rodada;
	}

}

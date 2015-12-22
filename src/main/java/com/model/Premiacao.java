package com.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Premiacao {

	@Id
	@GeneratedValue
	private Long id;
	private Date data;
	@OneToOne
	private Conquista conquista;
	@OneToOne
	private Rodada rodada;

	public Premiacao() {
	}

	public Premiacao(Date data, Conquista conquista, Rodada rodada) {
		super();
		this.data = data;
		this.conquista = conquista;
		this.rodada = rodada;
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

	public Conquista getConquista() {
		return conquista;
	}

	public void setConquista(Conquista conquista) {
		this.conquista = conquista;
	}

	public Rodada getRodada() {
		return rodada;
	}

	public void setRodada(Rodada rodada) {
		this.rodada = rodada;
	}

	@Override
	public String toString() {
		return "Premiacao [conquista=" + conquista + ", rodada=" + rodada + "]";
	}

}

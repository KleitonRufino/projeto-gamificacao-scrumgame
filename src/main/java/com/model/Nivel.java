package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.enums.Recompensa;

@Entity
public class Nivel {

	@Id
	@GeneratedValue
	private Long id;
	@Column(unique = true)
	private int nivel;
	private int xpMin;
	private int xpMax;
	private int aumentoDePontos;
	private String descricao;
	@Enumerated(EnumType.STRING)
	private Recompensa recompensa;

	public Nivel() {
		// TODO Auto-generated constructor stub
	}

	public Nivel(String descricao, int nivel, int xpMin, int xpMax, int aumentoDePontos, Recompensa recompensa) {
		super();
		this.descricao = descricao;
		this.nivel = nivel;
		this.xpMin = xpMin;
		this.xpMax = xpMax;
		this.aumentoDePontos = aumentoDePontos;
		this.recompensa = recompensa;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public Recompensa getRecompensa() {
		return recompensa;
	}

	public void setRecompensa(Recompensa recompensa) {
		this.recompensa = recompensa;
	}

	public int getXpMin() {
		return xpMin;
	}

	public void setXpMin(int xpMin) {
		this.xpMin = xpMin;
	}

	public int getXpMax() {
		return xpMax;
	}

	public void setXpMax(int xpMax) {
		this.xpMax = xpMax;
	}

	public int getAumentoDePontos() {
		return aumentoDePontos;
	}

	public void setAumentoDePontos(int aumentoDePontos) {
		this.aumentoDePontos = aumentoDePontos;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "Nivel [nivel=" + nivel + ", xpMin=" + xpMin + ", xpMax=" + xpMax + ", aumentoDePontos="
				+ aumentoDePontos + ", descricao=" + descricao + ", recompensa=" + recompensa + "]";
	}

}

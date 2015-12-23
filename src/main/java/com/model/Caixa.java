package com.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.enums.TipoRecompensa;

@Entity
public class Caixa {

	@Id
	@GeneratedValue
	private Long id;
	private String descricao;
	@Enumerated(EnumType.STRING)
	private TipoRecompensa tipoRecompensa;
	private int recompensa;

	public Caixa() {
	}

	public Caixa(String descricao, TipoRecompensa tipoRecompensa, int recompensa) {
		this.descricao = descricao;
		this.tipoRecompensa = tipoRecompensa;
		this.recompensa = recompensa;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getRecompensa() {
		return recompensa;
	}

	public void setRecompensa(int recompensa) {
		this.recompensa = recompensa;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoRecompensa getTipoRecompensa() {
		return tipoRecompensa;
	}

	public void setTipoRecompensa(TipoRecompensa tipoRecompensa) {
		this.tipoRecompensa = tipoRecompensa;
	}

	@Override
	public String toString() {
		return "Caixa [descricao=" + descricao + ", tipoRecompensa=" + tipoRecompensa + ", recompensa=" + recompensa
				+ "]";
	}

}

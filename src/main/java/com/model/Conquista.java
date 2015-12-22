package com.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.enums.NomeConquista;

@Entity
public class Conquista {

	@Id
	@GeneratedValue
	private Long id;
	@Enumerated(EnumType.STRING)
	private NomeConquista nomeConquista;
	private String descricao;
	private int recompensaEmEstrela;
	private int recompensaEmPontos;

	public Conquista() {
	}

	public Conquista(NomeConquista nomeConquista, String descricao, int recompensaEmEstrela, int recompensaEmPontos) {
		super();
		this.nomeConquista = nomeConquista;
		this.descricao = descricao;
		this.recompensaEmEstrela = recompensaEmEstrela;
		this.recompensaEmPontos = recompensaEmPontos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public NomeConquista getNomeConquista() {
		return nomeConquista;
	}

	public void setNomeConquista(NomeConquista nomeConquista) {
		this.nomeConquista = nomeConquista;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getRecompensaEmEstrela() {
		return recompensaEmEstrela;
	}

	public void setRecompensaEmEstrela(int recompensaEmEstrela) {
		this.recompensaEmEstrela = recompensaEmEstrela;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nomeConquista == null) ? 0 : nomeConquista.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Conquista other = (Conquista) obj;
		if (nomeConquista != other.nomeConquista)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Conquista [nomeConquista=" + nomeConquista + ", descricao=" + descricao + ", recompensaEmEstrela="
				+ recompensaEmEstrela + ", recompensaEmPontos=" + recompensaEmPontos + "]";
	}

}

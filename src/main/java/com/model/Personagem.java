package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.enums.Acao;

@Entity
public class Personagem {

	@Id
	@GeneratedValue
	private Long id;
	@Column(unique = true, nullable = false)
	private String nome;
	@Enumerated(EnumType.STRING)
	private Acao acao;
	@Column(nullable = false)
	private int pontos;
	@Column(nullable = false)
	private int preco;
	@Column(nullable = false)
	private int nivelDesbloqueio;

	public Personagem() {
	}

	public Personagem(String nome, Acao acao, int pontos, int preco, int nivelDesbloqueio) {
		super();
		this.nome = nome;
		this.acao = acao;
		this.pontos = pontos;
		this.preco = preco;
		this.nivelDesbloqueio = nivelDesbloqueio;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Acao getAcao() {
		return acao;
	}

	public void setAcao(Acao acao) {
		this.acao = acao;
	}

	public int getPontos() {
		return pontos;
	}

	public void setPontos(int pontos) {
		this.pontos = pontos;
	}

	public int getPreco() {
		return preco;
	}

	public void setPreco(int preco) {
		this.preco = preco;
	}

	public int getNivelDesbloqueio() {
		return nivelDesbloqueio;
	}

	public void setNivelDesbloqueio(int nivelDesbloqueio) {
		this.nivelDesbloqueio = nivelDesbloqueio;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Personagem other = (Personagem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Personagem [nome=" + nome + ", acao=" + acao + ", pontos=" + pontos + ", preco=" + preco
				+ ", nivelDesbloqueio=" + nivelDesbloqueio + "]";
	}

}

package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Habilidade {

	@Id
	@GeneratedValue
	private Long id;
	@Column(nullable = false)
	private String nome;
	@Column(nullable = false)
	private int preco;
	@Column(nullable = false)
	private int nivelDesbloqueio;
	@Column(nullable = false)
	private int pontos;
	@OneToOne(optional = false)
	private Personagem personagem;

	public Habilidade() {
	}

	public Habilidade(Personagem personagem, String nome, int preco, int nivelDesbloqueio, int pontos) {
		super();
		this.personagem = personagem;
		this.nome = nome;
		this.preco = preco;
		this.nivelDesbloqueio = nivelDesbloqueio;
		this.pontos = pontos;
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

	public Personagem getPersonagem() {
		return personagem;
	}

	public void setPersonagem(Personagem personagem) {
		this.personagem = personagem;
	}

	public int getPontos() {
		return pontos;
	}

	public void setPontos(int pontos) {
		this.pontos = pontos;
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
		Habilidade other = (Habilidade) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Habilidade [nome=" + nome + ", preco=" + preco + ", nivelDesbloqueio=" + nivelDesbloqueio + "]";
	}

}
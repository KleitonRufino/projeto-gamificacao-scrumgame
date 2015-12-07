package com.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Rodada {
	@Id
	@GeneratedValue
	private Long id;
	private String nome;
	private int numero;
	public boolean ativa;

	public Rodada() {
	}

	public Rodada(String nome, int numero, boolean ativa) {
		super();
		this.nome = nome;
		this.numero = numero;
		this.ativa = ativa;
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

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public boolean isAtiva() {
		return ativa;
	}

	public void setAtiva(boolean ativa) {
		this.ativa = ativa;
	}

	@Override
	public String toString() {
		return "Rodada [nome=" + nome + ", numero=" + numero + "]";
	}

}

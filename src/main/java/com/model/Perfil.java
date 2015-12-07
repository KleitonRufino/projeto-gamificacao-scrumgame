package com.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Perfil {

	@Id
	@GeneratedValue
	private Long id;
	private int nivelExperiencia;
	private int ptsTotais;
	private int ptsDeAmigo;
	private int ptsDeConquista;
	private int ptsDeAtividade;
	private int estrelas;
	private int countAtividades;
	private int countCooperacoes;
	@OneToOne
	private Jogador jogador;

	public Perfil() {

	}

	public Perfil(int nivelExperiencia, int ptsTotais, int ptsDeAmigo, int ptsDeConquista, int ptsDeAtividade,
			int estrelas, int countAtividades, int countCooperacoes) {
		super();
		this.nivelExperiencia = nivelExperiencia;
		this.ptsTotais = ptsTotais;
		this.ptsDeAmigo = ptsDeAmigo;
		this.ptsDeConquista = ptsDeConquista;
		this.ptsDeAtividade = ptsDeAtividade;
		this.estrelas = estrelas;
		this.countAtividades = countAtividades;
		this.countCooperacoes = countCooperacoes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getPtsTotais() {
		return ptsTotais;
	}

	public void setPtsTotais(int ptsTotais) {
		this.ptsTotais = ptsTotais;
	}

	public int getPtsDeAmigo() {
		return ptsDeAmigo;
	}

	public void setPtsDeAmigo(int ptsDeAmigo) {
		this.ptsDeAmigo = ptsDeAmigo;
	}

	public int getPtsDeConquista() {
		return ptsDeConquista;
	}

	public void setPtsDeConquista(int ptsDeConquista) {
		this.ptsDeConquista = ptsDeConquista;
	}

	public int getPtsDeAtividade() {
		return ptsDeAtividade;
	}

	public void setPtsDeAtividade(int ptsDeAtividade) {
		this.ptsDeAtividade = ptsDeAtividade;
	}

	public int getEstrelas() {
		return estrelas;
	}

	public void setEstrelas(int estrelas) {
		this.estrelas = estrelas;
	}

	public int getCountAtividades() {
		return countAtividades;
	}

	public void setCountAtividades(int countAtividades) {
		this.countAtividades = countAtividades;
	}

	public int getCountCooperacoes() {
		return countCooperacoes;
	}

	public void setCountCooperacoes(int countCooperacoes) {
		this.countCooperacoes = countCooperacoes;
	}

	public int getNivelExperiencia() {
		return nivelExperiencia;
	}

	public void setNivelExperiencia(int nivelExperiencia) {
		this.nivelExperiencia = nivelExperiencia;
	}

	@Override
	public String toString() {
		return "Perfil [nivelExperiencia=" + nivelExperiencia + ", ptsTotais=" + ptsTotais + ", ptsDeAmigo="
				+ ptsDeAmigo + ", ptsDeConquista=" + ptsDeConquista + ", ptsDeAtividade=" + ptsDeAtividade
				+ ", estrelas=" + estrelas + ", countAtividades=" + countAtividades + ", countCooperacoes="
				+ countCooperacoes + ", nicknameJogador=" + jogador.getNickname() + "]";
	}

	public Jogador getJogador() {
		return jogador;
	}

	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}

}

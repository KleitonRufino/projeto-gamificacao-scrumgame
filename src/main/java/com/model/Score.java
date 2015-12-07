package com.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Score {

	@Id
	@GeneratedValue
	private Long id;
	private int ptsTotaisNaRodada;
	private int ptsDeAmigoNaRodada;
	private int ptsDeConquistaNaRodada;
	private int ptsDeAtividadeNaRodada;
	private int estrelasNaRodada;
	private int countAtividadesNaRodada;
	private int countCooperacoesNaRodada;
	@OneToOne
	private Jogador jogador;
	@OneToOne
	private Rodada rodada;
	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private List<Premiacao> premiacoes = new ArrayList<Premiacao>();

	public Score() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getPtsTotaisNaRodada() {
		return ptsTotaisNaRodada;
	}

	public void setPtsTotaisNaRodada(int ptsTotaisNaRodada) {
		this.ptsTotaisNaRodada = ptsTotaisNaRodada;
	}

	public int getPtsDeAmigoNaRodada() {
		return ptsDeAmigoNaRodada;
	}

	public void setPtsDeAmigoNaRodada(int ptsDeAmigoNaRodada) {
		this.ptsDeAmigoNaRodada = ptsDeAmigoNaRodada;
	}

	public int getPtsDeConquistaNaRodada() {
		return ptsDeConquistaNaRodada;
	}

	public void setPtsDeConquistaNaRodada(int ptsDeConquistaNaRodada) {
		this.ptsDeConquistaNaRodada = ptsDeConquistaNaRodada;
	}

	public int getPtsDeAtividadeNaRodada() {
		return ptsDeAtividadeNaRodada;
	}

	public void setPtsDeAtividadeNaRodada(int ptsDeAtividadeNaRodada) {
		this.ptsDeAtividadeNaRodada = ptsDeAtividadeNaRodada;
	}

	public int getEstrelasNaRodada() {
		return estrelasNaRodada;
	}

	public void setEstrelasNaRodada(int estrelasNaRodada) {
		this.estrelasNaRodada = estrelasNaRodada;
	}

	public int getCountAtividadesNaRodada() {
		return countAtividadesNaRodada;
	}

	public void setCountAtividadesNaRodada(int countAtividadesNaRodada) {
		this.countAtividadesNaRodada = countAtividadesNaRodada;
	}

	public int getCountCooperacoesNaRodada() {
		return countCooperacoesNaRodada;
	}

	public void setCountCooperacoesNaRodada(int countCooperacoesNaRodada) {
		this.countCooperacoesNaRodada = countCooperacoesNaRodada;
	}

	public Rodada getRodada() {
		return rodada;
	}

	public Jogador getJogador() {
		return jogador;
	}

	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}

	public void setRodada(Rodada rodada) {
		this.rodada = rodada;
	}

	public List<Premiacao> getPremiacoes() {
		return premiacoes;
	}

	public void setPremiacoes(List<Premiacao> premiacoes) {
		this.premiacoes = premiacoes;
	}

	@Override
	public String toString() {
		return "Score [ptsTotaisNaRodada=" + ptsTotaisNaRodada + ", ptsDeAmigoNaRodada=" + ptsDeAmigoNaRodada
				+ ", ptsDeConquistaNaRodada=" + ptsDeConquistaNaRodada + ", ptsDeAtividadeNaRodada="
				+ ptsDeAtividadeNaRodada + ", estrelasNaRodada=" + estrelasNaRodada + ", countAtividadesNaRodada="
				+ countAtividadesNaRodada + ", countCooperacoesNaRodada=" + countCooperacoesNaRodada + "]";
	}

}

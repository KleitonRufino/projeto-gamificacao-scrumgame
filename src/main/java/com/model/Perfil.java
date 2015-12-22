package com.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.enums.Avatar;

@Entity
public class Perfil {

	@Id
	@GeneratedValue
	private Long id;
	@OneToOne
	private Nivel nivel;
	private int ptsTotais;
	private int ptsDeAmigo;
	private int ptsDeAtividade;
	private int ptsExtras;
	private int estrelas;
	private int countAtividades;
	private int countCooperacoes;
	@Enumerated(EnumType.STRING)
	private Avatar avatar;
	@OneToOne
	private Jogador jogador;
	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private List<Premiacao> premiacoes = new ArrayList<Premiacao>();
	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "perfil")
	private List<Item> meusItens = new ArrayList<Item>();

	public Perfil() {

	}

	public Perfil(Nivel nivel, int ptsTotais, int ptsDeAmigo, int ptsDeAtividade, int ptsExtras, int estrelas,
			int countAtividades, int countCooperacoes) {
		super();
		this.nivel = nivel;
		this.ptsTotais = ptsTotais;
		this.ptsDeAmigo = ptsDeAmigo;
		this.ptsDeAtividade = ptsDeAtividade;
		this.ptsExtras = ptsExtras;
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

	public Jogador getJogador() {
		return jogador;
	}

	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}

	public List<Premiacao> getPremiacoes() {
		return premiacoes;
	}

	public void setPremiacoes(List<Premiacao> premiacoes) {
		this.premiacoes = premiacoes;
	}

	public int getPtsExtras() {
		return ptsExtras;
	}

	public void setPtsExtras(int ptsExtras) {
		this.ptsExtras = ptsExtras;
	}

	public Nivel getNivel() {
		return nivel;
	}

	public void setNivel(Nivel nivel) {
		this.nivel = nivel;
	}

	public List<Item> getMeusItens() {
		return meusItens;
	}

	public void setMeusItens(List<Item> meusItens) {
		this.meusItens = meusItens;
	}

	@Override
	public String toString() {
		return "Perfil [nivel=" + nivel.getNivel() + ", ptsTotais=" + ptsTotais + ", ptsDeAmigo=" + ptsDeAmigo
				+ ", ptsDeAtividade=" + ptsDeAtividade + ", ptsExtras=" + ptsExtras + ", estrelas=" + estrelas
				+ ", countAtividades=" + countAtividades + ", countCooperacoes=" + countCooperacoes + "]";
	}

	public Avatar getAvatar() {
		return avatar;
	}

	public void setAvatar(Avatar avatar) {
		this.avatar = avatar;
	}

}

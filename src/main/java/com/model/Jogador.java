package com.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Jogador {

	@Id
	@GeneratedValue
	private Long id;
	private String nickname;
	private String senha;
	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "jogador_que_ajudou_id")
	private List<Cooperacao> cooperacoes;

	public Jogador() {
	}

	public Jogador(String nickname) {
		super();
		this.nickname = nickname;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public List<Cooperacao> getCooperacoes() {
		return cooperacoes;
	}

	public void setCooperacoes(List<Cooperacao> cooperacoes) {
		this.cooperacoes = cooperacoes;
	}

	@Override
	public String toString() {
		return "Jogador [nickname=" + nickname + "]";
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}

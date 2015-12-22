package com.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.enums.Avatar;

@Entity
public class Jogador {

	@Id
	@GeneratedValue
	private Long id;
	private String nickname;
	private String senha;
	@Enumerated(EnumType.STRING)
	private Avatar avatar;
	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "jogador_que_ajudou_id")
	private List<Cooperacao> cooperacoes;

	public Jogador() {
	}

	public Jogador(String nickname) {
		super();
		this.nickname = nickname;
	}

	public Jogador(String nickname, String senha) {
		super();
		this.nickname = nickname;
		this.senha = senha;
	}

	public Jogador(String nickname, String senha, Avatar avatar) {
		super();
		this.nickname = nickname;
		this.senha = senha;
		this.avatar = avatar;
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

	public Avatar getAvatar() {
		return avatar;
	}

	public void setAvatar(Avatar avatar) {
		this.avatar = avatar;
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

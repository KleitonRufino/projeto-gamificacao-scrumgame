package com.gerenciador.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gerenciador.GerenciadorJogador;
import com.model.Jogador;
import com.repositorio.JogadorRepositorio;

@Service
public class GerenciadorJogadorImpl implements GerenciadorJogador {

	@Autowired
	private JogadorRepositorio repJogador;

	public GerenciadorJogadorImpl() {
	}

	@Override
	public void salvar(Jogador jogador) {
		this.repJogador.salvar(jogador);
	}

	@Override
	public void atualizar(Jogador jogador) {
		this.repJogador.update(jogador);
	}

	@Override
	public Jogador findByNicknameAndSenha(String nickname, String senha) {
		return this.repJogador.findByNicknameAndSenha(nickname, senha);
	}

	@Override
	public Jogador findByNickname(String nickname) {
		return this.repJogador.findByNickname(nickname);
	}

	@Override
	public Jogador findByNicknameComCooperacoes(String nickname) {
		return this.repJogador.findByNicknameComCooperacoes(nickname);
	}

	@Override
	public List<Jogador> findAll() {
		return this.repJogador.findAll();
	}

	@Override
	public List<Jogador> findAllExceptMe(String nickname) {
		return this.repJogador.findAllExceptMe(nickname);
	}

}

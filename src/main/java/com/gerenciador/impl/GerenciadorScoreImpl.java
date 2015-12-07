package com.gerenciador.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gerenciador.GerenciadorScore;
import com.model.Jogador;
import com.model.Score;
import com.repositorio.ScoreRepositorio;

@Service
public class GerenciadorScoreImpl implements GerenciadorScore {

	@Autowired
	private ScoreRepositorio repScore;

	@Override
	public void salvar(Score score) {
		this.repScore.salvar(score);
	}

	@Override
	public void atualizar(Score score) {
		this.repScore.update(score);
	}

	@Override
	public Score findByRodadaAtivaAndIdJogador(Jogador jogador) {
		return this.repScore.findByRodadaAtivaAndIdJogador(jogador);
	}

	@Override
	public List<Score> findByIdJogador(Jogador jogador) {
		return this.repScore.findByIdJogador(jogador);
	}

}

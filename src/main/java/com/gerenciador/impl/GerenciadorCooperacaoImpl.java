package com.gerenciador.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gerenciador.GerenciadorCooperacao;
import com.model.Cooperacao;
import com.model.Jogador;
import com.model.Pontuacao;
import com.model.Rodada;
import com.model.Score;
import com.repositorio.CooperacaoRepositorio;

@Service
public class GerenciadorCooperacaoImpl implements GerenciadorCooperacao {

	@Autowired
	private CooperacaoRepositorio repCooperacao;

	@Override
	public void salvar(Cooperacao cooperacao) {
		this.repCooperacao.salvar(cooperacao);
	}

	@Override
	public void atualizar(Cooperacao cooperacao) {
		this.repCooperacao.update(cooperacao);
	}

	@Override
	public Cooperacao criar(Jogador jogadorAjudado, Rodada rodada) {
		Cooperacao cooperacao = new Cooperacao();
		cooperacao.setData(new Date());
		cooperacao.setJogadorAjudado(jogadorAjudado);
		cooperacao.setRodada(rodada);
		return cooperacao;
	}

	@Override
	public Score solicitarNovaCooperacao(Score scoreRodada, int novasCooperacoesFeitas) {
		// jogador.getPerfil().setCountCooperacoes(jogador.getPerfil().getCountCooperacoes()
		// + novasCooperacoesFeitas);
		// jogador.getPerfil().setPtsDeAmigo(jogador.getPerfil().getPtsDeAmigo()
		// + (novasCooperacoesFeitas * 10));
		scoreRodada.setCountCooperacoesNaRodada(scoreRodada.getCountCooperacoesNaRodada() + novasCooperacoesFeitas);
		scoreRodada.setPtsDeAmigoNaRodada(
				scoreRodada.getPtsDeAmigoNaRodada() + (novasCooperacoesFeitas * Pontuacao.DEZPTS.getPts()));
		return scoreRodada;
	}

	@Override
	public Score solicitarPtsPedidoDeAjuda(Score scoreRodada) {
		scoreRodada.setPtsDeAmigoNaRodada(scoreRodada.getPtsDeAmigoNaRodada() + Pontuacao.CINCOPTS.getPts());
		return scoreRodada;
	}

}

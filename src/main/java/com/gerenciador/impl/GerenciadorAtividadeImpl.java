package com.gerenciador.impl;

import org.springframework.stereotype.Service;

import com.gerenciador.GerenciadorAtividade;
import com.model.Pontuacao;
import com.model.Score;

@Service
public class GerenciadorAtividadeImpl implements GerenciadorAtividade {

	@Override
	public Score solicitarNovaAtividade(Score scoreRodada, int novasAtividadesFeitas) {
		scoreRodada.setCountAtividadesNaRodada(scoreRodada.getCountAtividadesNaRodada() + novasAtividadesFeitas);
		scoreRodada.setPtsDeAtividadeNaRodada(
				scoreRodada.getPtsDeAtividadeNaRodada() + (novasAtividadesFeitas * Pontuacao.QUINZE.getPts()));

		return scoreRodada;
	}
}

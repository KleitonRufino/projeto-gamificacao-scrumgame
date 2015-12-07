package com.gerenciador;

import java.util.List;

import com.model.Jogador;
import com.model.Score;

public interface GerenciadorScore {

	public void salvar(Score score);

	public void atualizar(Score score);

	public Score findByRodadaAtivaAndIdJogador(Jogador jogador);

	public List<Score> findByIdJogador(Jogador jogador);
	
	public void calcularPtsTotaisNaRodada(Score scoreRodada);

}

package com.gerenciador;

import com.model.Cooperacao;
import com.model.Jogador;
import com.model.Rodada;
import com.model.Score;

public interface GerenciadorCooperacao {

	public void salvar(Cooperacao cooperacao);

	public void atualizar(Cooperacao cooperacao);

	public Cooperacao criar(Jogador jogadorAjudado, Rodada rodada);

	public Score solicitarNovaCooperacao(Score scoreRodada, int novasCooperacoesFeitas);

	public Score solicitarPtsPedidoDeAjuda(Score scoreRodada, int qtdPedidosDeAjuda);
}

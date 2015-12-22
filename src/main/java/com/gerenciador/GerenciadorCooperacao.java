package com.gerenciador;

import com.model.Cooperacao;
import com.model.Jogador;
import com.model.Rodada;

public interface GerenciadorCooperacao {

	public void salvar(Cooperacao cooperacao);

	public void atualizar(Cooperacao cooperacao);

	public Cooperacao criar(Jogador jogadorAjudado, Rodada rodada);

}

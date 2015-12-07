package com.gerenciador;

import java.util.List;

import com.model.Rodada;

public interface GerenciadorRodada {

	public void salvar(Rodada rodada);

	public void atualizar(Rodada rodada);

	public Rodada findByNumero(int numero);

	public Rodada findByAtiva();

	public List<Rodada> findAll();
}

package com.gerenciador.impl;

import com.gerenciador.GerenciadorPremiacao;
import com.model.Premiacao;
import com.repositorio.PremiacaoRepositorio;

public class GerenciadorPremiacaoImpl implements GerenciadorPremiacao {

	private PremiacaoRepositorio repPremiacao = new PremiacaoRepositorio();

	@Override
	public void salvar(Premiacao premiacao) {
		this.repPremiacao.salvar(premiacao);
	}

	@Override
	public void atualizar(Premiacao premiacao) {
		this.repPremiacao.update(premiacao);
	}

}

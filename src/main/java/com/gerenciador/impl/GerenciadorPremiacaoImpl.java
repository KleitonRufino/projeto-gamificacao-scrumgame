package com.gerenciador.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gerenciador.GerenciadorPremiacao;
import com.model.Premiacao;
import com.repositorio.PremiacaoRepositorio;

@Service
public class GerenciadorPremiacaoImpl implements GerenciadorPremiacao {

	@Autowired
	private PremiacaoRepositorio repPremiacao;

	@Override
	public void salvar(Premiacao premiacao) {
		this.repPremiacao.salvar(premiacao);
	}

	@Override
	public void atualizar(Premiacao premiacao) {
		this.repPremiacao.update(premiacao);
	}

}

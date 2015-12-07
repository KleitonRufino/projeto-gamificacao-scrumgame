package com.gerenciador.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gerenciador.GerenciadorRodada;
import com.model.Rodada;
import com.repositorio.RodadaRepositorio;

@Service
public class GerenciadorRodadaImpl implements GerenciadorRodada {

	@Autowired
	private RodadaRepositorio repRodada;

	@Override
	public void salvar(Rodada rodada) {
		this.repRodada.salvar(rodada);
	}

	@Override
	public void atualizar(Rodada rodada) {
		this.repRodada.update(rodada);
	}

	@Override
	public Rodada findByNumero(int numero) {
		return repRodada.findByNumero(numero);
	}

	@Override
	public Rodada findByAtiva() {
		return repRodada.findByAtiva();
	}

	@Override
	public List<Rodada> findAll() {
		return repRodada.findAll();
	}

}

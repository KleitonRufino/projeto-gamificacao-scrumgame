package com.gerenciador.impl;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gerenciador.GerenciadorRecompensa;
import com.model.Caixa;
import com.model.Nivel;
import com.repositorio.RecompensaRepositorio;

@Service
public class GerenciadorRecompensaImpl implements GerenciadorRecompensa {

	@Autowired
	private RecompensaRepositorio recompensaRepositorio;

	@Override
	public void salvar(Caixa caixa) {
		this.recompensaRepositorio.salvar(caixa);
	}

	@Override
	public void atualizar(Caixa caixa) {
		this.recompensaRepositorio.update(caixa);
	}

	@Override
	public List<Caixa> findAll() {
		return this.recompensaRepositorio.findAll();
	}

	@Override
	public Caixa verificarRecompensaParaNovoNivel(Nivel nivel) {
		List<Caixa> caixas = this.findAll();
		Random gerador = new Random();
		int numero = gerador.nextInt(caixas.size());
		return caixas.get(numero);
	}

}

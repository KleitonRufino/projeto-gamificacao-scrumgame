package com.gerenciador.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gerenciador.GerenciadorNivel;
import com.model.Nivel;
import com.model.Perfil;
import com.repositorio.NivelRepositorio;

@Service
public class GerenciadorNivelImpl implements GerenciadorNivel {

	@Autowired
	private NivelRepositorio nivelRepositorio;

	@Override
	public void salvar(Nivel nivel) {
		this.nivelRepositorio.salvar(nivel);
	}

	@Override
	public void atualizar(Nivel nivel) {
		this.nivelRepositorio.update(nivel);
	}

	@Override
	public Nivel findByNivel(int nivel) {
		return this.nivelRepositorio.findByNivel(nivel);
	}

	@Override
	public Nivel findByXp(int xp) {
		return this.nivelRepositorio.findByXp(xp);
	}

	@Override
	public Nivel verificarNivel(Perfil perfil) {
		return findByXp(perfil.getPtsTotais());
	}

	@Override
	public boolean verificarNovoNivel(Nivel antigo, Nivel novo) {
		return antigo.getNivel() < novo.getNivel();
	}

}

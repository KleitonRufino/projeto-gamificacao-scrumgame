package com.gerenciador.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gerenciador.GerenciadorPersonagem;
import com.model.Item;
import com.model.Perfil;
import com.model.Personagem;
import com.repositorio.PersonagemRepositorio;

@Service
public class GerenciadorPersonagemImpl implements GerenciadorPersonagem {

	@Autowired
	private PersonagemRepositorio personagemRepositorio;

	@Override
	public void salvar(Personagem personagem) {
		this.personagemRepositorio.salvar(personagem);
	}

	@Override
	public void atualizar(Personagem personagem) {
		this.personagemRepositorio.update(personagem);
	}

	@Override
	public List<Personagem> findAll() {
		return this.personagemRepositorio.findAll();
	}

	@Override
	public Personagem findByNome(String nome) {
		return this.personagemRepositorio.findByNome(nome);
	}

	@Override
	public List<Personagem> findMyPersonagemByPerfil(Perfil perfil) {
		return this.personagemRepositorio.findMyPersonagemByPerfil(perfil);
	}

	@Override
	public List<Personagem> findNotMyPersonagemByPerfil(Perfil perfil) {
		List<Personagem> personagens = this.personagemRepositorio.findAll();
		for (Item item : perfil.getMeusItens()) {
			Personagem p = item.getPersonagem();
			if(personagens.contains(p)){
				personagens.remove(p);
			}
			
		}
		return personagens;
	}

}

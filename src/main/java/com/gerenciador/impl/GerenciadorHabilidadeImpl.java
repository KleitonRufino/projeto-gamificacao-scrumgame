package com.gerenciador.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gerenciador.GerenciadorHabilidade;
import com.model.Habilidade;
import com.model.Item;
import com.model.Perfil;
import com.model.Personagem;
import com.repositorio.HabilidadeRespositorio;

@Service
public class GerenciadorHabilidadeImpl implements GerenciadorHabilidade {

	@Autowired
	private HabilidadeRespositorio habilidadeRepositorio;

	@Override
	public void salvar(Habilidade habilidade) {
		this.habilidadeRepositorio.salvar(habilidade);
	}

	@Override
	public void atualizar(Habilidade habilidade) {
		this.habilidadeRepositorio.update(habilidade);
	}

	@Override
	public List<Habilidade> findAll() {
		return this.habilidadeRepositorio.findAll();
	}

	@Override
	public Habilidade findByNome(String nome) {
		return this.habilidadeRepositorio.findByNome(nome);
	}

	@Override
	public Habilidade findByPersonagem(Personagem personagem) {
		return this.habilidadeRepositorio.findByPersonagem(personagem);
	}

	@Override
	public List<Habilidade> findMyHabilidadeByPerfil(Perfil perfil) {
		return this.habilidadeRepositorio.findMyHabilidadeByPerfil(perfil);
	}

	@Override
	public List<Habilidade> findNotMyHabilidadeByPerfil(Perfil perfil) {
		List<Habilidade> habilidades = this.habilidadeRepositorio.findAll();
		for (Item item : perfil.getMeusItens()) {
			Habilidade h = item.getHabilidade();
			if(habilidades.contains(h)){
				habilidades.remove(h);
			}
			
		}
		return habilidades;
	}

}

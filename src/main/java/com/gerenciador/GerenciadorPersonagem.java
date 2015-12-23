package com.gerenciador;

import java.util.List;

import com.model.Perfil;
import com.model.Personagem;

public interface GerenciadorPersonagem {

	public void salvar(Personagem personagem);

	public void atualizar(Personagem personagem);

	public List<Personagem> findAll();

	public Personagem findByNome(String nome);

	public List<Personagem> findMyPersonagemByPerfil(Perfil perfil);

	public List<Personagem> findNotMyPersonagemByPerfil(Perfil perfil);
}

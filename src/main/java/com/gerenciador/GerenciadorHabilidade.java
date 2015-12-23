package com.gerenciador;

import java.util.List;

import com.model.Habilidade;
import com.model.Perfil;
import com.model.Personagem;

public interface GerenciadorHabilidade {

	public void salvar(Habilidade habilidade);

	public void atualizar(Habilidade habilidade);

	public List<Habilidade> findAll();

	public Habilidade findByNome(String nome);

	public Habilidade findByPersonagem(Personagem personagem);
	
	public List<Habilidade> findMyHabilidadeByPerfil(Perfil perfil);

	public List<Habilidade> findNotMyHabilidadeByPerfil(Perfil perfil);
	
}

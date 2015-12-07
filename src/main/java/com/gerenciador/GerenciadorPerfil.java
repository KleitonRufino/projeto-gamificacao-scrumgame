package com.gerenciador;

import java.util.List;

import com.model.Jogador;
import com.model.Perfil;
import com.model.Score;

public interface GerenciadorPerfil {

	public void salvar(Perfil perfil);

	public void atualizar(Perfil perfil);

	public Perfil findByIdDoJogador(Jogador jogador);
	
	public Perfil findByNicknameDoJogador(Jogador jogador) ;
	
	public Perfil processarDadosDoPerfil(Perfil perfil, List<Score> scores);
}

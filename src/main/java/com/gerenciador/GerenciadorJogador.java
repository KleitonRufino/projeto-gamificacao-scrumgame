package com.gerenciador;

import java.util.List;

import com.model.Jogador;

public interface GerenciadorJogador {

	public void salvar(Jogador jogador);

	public void atualizar(Jogador jogador);
	
	public Jogador findByNicknameAndSenha(String nickname, String senha);
	
	public Jogador findByNickname(String nickname);

	public Jogador findByNicknameComCooperacoes(String nickname);
	
	public List<Jogador> findAll();
	
	public List<Jogador> findAllExceptMe(String nickname);
}

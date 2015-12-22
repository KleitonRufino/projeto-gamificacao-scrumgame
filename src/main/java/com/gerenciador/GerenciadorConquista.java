package com.gerenciador;

import java.util.List;

import com.enums.NomeConquista;
import com.model.Conquista;
import com.model.Jogador;
import com.model.Perfil;
import com.model.Rodada;

public interface GerenciadorConquista {

	public void salvar(Conquista conquista);

	public void atualizar(Conquista conquista);

	public List<Conquista> verificarNovasConquistasParaAtividade(Perfil perfil, List<Conquista> conquistas);

	public List<Conquista> verificarNovasConquistasParaCooperacao(Jogador jogador, Perfil perfil,
			List<Conquista> conquistas, Rodada rodadAtiva);

	public Conquista findByNome(NomeConquista nomeConquista);

	public boolean verificarSePossuiConquista(Conquista novaConquista, List<Conquista> conquistas);

	public boolean verificarSePossuiConquistaNaRodada(Perfil perfil, Conquista conquista, Rodada rodadaAtiva);

}

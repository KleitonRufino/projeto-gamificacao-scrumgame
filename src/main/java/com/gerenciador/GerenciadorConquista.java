package com.gerenciador;

import java.util.List;

import com.enums.NomeConquista;
import com.model.Conquista;
import com.model.Score;

public interface GerenciadorConquista {

	public void salvar(Conquista conquista);

	public void atualizar(Conquista conquista);

	public List<Conquista> verificarNovasConquistasParaAtividade(Score scoreDaRodada);

	public List<Conquista> verificarNovasConquistasParaCooperacao(Score scoreDaRodada);

	public Score atribuirConquistaERecompensaAoScore(Score scoreDaRodada, List<Conquista> conquistas);

	public Conquista findByNome(NomeConquista nomeConquista);

	public boolean verificarSePossuiConquista(Conquista novaConquista, List<Conquista> conquistas);
}

package com.gerenciador.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gerenciador.GerenciadorPerfil;
import com.model.Jogador;
import com.model.Perfil;
import com.model.Score;
import com.repositorio.PerfilRepositorio;

@Service
public class GerenciadorPerfilImpl implements GerenciadorPerfil {

	@Autowired
	private PerfilRepositorio repPerfil;

	public GerenciadorPerfilImpl() {
	}

	@Override
	public void salvar(Perfil perfil) {
		this.repPerfil.salvar(perfil);
	}

	@Override
	public void atualizar(Perfil perfil) {
		this.repPerfil.update(perfil);
	}

	@Override
	public Perfil findByIdDoJogador(Jogador jogador) {
		return this.repPerfil.findByIdDoJogador(jogador);
	}

	@Override
	public Perfil findByNicknameDoJogador(Jogador jogador) {
		return this.repPerfil.findByNicknameDoJogador(jogador);
	}

	@Override
	public Perfil processarDadosDoPerfil(Perfil perfil, List<Score> scores) {
		int ptsTotais = 0;
		int ptsDeAmigo = 0;
		int ptsDeConquista = 0;
		int ptsDeAtividade = 0;
		int estrelas = 0;
		int countAtividades = 0;
		int countCooperacoes = 0;
		for (Score score : scores) {
			ptsDeAmigo += score.getPtsDeAmigoNaRodada();
			ptsDeConquista += score.getPtsDeConquistaNaRodada();
			ptsDeAtividade += score.getPtsDeAtividadeNaRodada();
			estrelas += score.getEstrelasNaRodada();
			countAtividades += score.getCountAtividadesNaRodada();
			countCooperacoes += score.getCountCooperacoesNaRodada();
			ptsTotais = ptsDeAmigo + ptsDeConquista + ptsDeAtividade;
		}

		int nivelExperiencia = ptsTotais / 50;
		perfil.setPtsTotais(ptsTotais);
		perfil.setPtsDeAmigo(ptsDeAmigo);
		perfil.setPtsDeConquista(ptsDeConquista);
		perfil.setPtsDeAtividade(ptsDeAtividade);
		perfil.setEstrelas(estrelas);
		perfil.setCountAtividades(countAtividades);
		perfil.setCountCooperacoes(countCooperacoes);
		perfil.setNivelExperiencia(nivelExperiencia);
		return perfil;
	}

}

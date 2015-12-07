package com.gerenciador.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enums.NomeConquista;
import com.gerenciador.GerenciadorConquista;
import com.model.Conquista;
import com.model.Pontuacao;
import com.model.Premiacao;
import com.model.Score;
import com.repositorio.ConquistaRepositorio;

@Service
public class GerenciadorConquistaImpl implements GerenciadorConquista {

	@Autowired
	private ConquistaRepositorio repConquista;

	@Override
	public void salvar(Conquista conquista) {
		repConquista.salvar(conquista);
	}

	@Override
	public void atualizar(Conquista conquista) {
		repConquista.update(conquista);
	}

	@Override
	public List<Conquista> verificarNovasConquistasParaAtividade(Score scoreDaRodada) {
		List<NomeConquista> nomeConquistasAdquiridas = new ArrayList<NomeConquista>();
		List<Conquista> minhasConquistas = new ArrayList<>();
		for (Premiacao premiacoes : scoreDaRodada.getPremiacoes()) {
			minhasConquistas.add(premiacoes.getConquista());
		}
		if (scoreDaRodada.getCountAtividadesNaRodada() >= 1) {
			nomeConquistasAdquiridas.add(NomeConquista.CONQI);
			if (scoreDaRodada.getCountAtividadesNaRodada() >= 3) {
				nomeConquistasAdquiridas.add(NomeConquista.CONQII);
				if (scoreDaRodada.getCountAtividadesNaRodada() >= 5) {
					nomeConquistasAdquiridas.add(NomeConquista.CONQIII);
				}
			}
		}
		List<Conquista> novasConquistas = new ArrayList<Conquista>();
		for (NomeConquista nomeConquista : nomeConquistasAdquiridas) {
			Conquista c = new Conquista();
			c.setNomeConquista(nomeConquista);
			if (!verificarSePossuiConquista(c, minhasConquistas)) {
				c = findByNome(nomeConquista);
				novasConquistas.add(c);
			}
		}
		return novasConquistas;
		// jogador = atribuirConquistaERecompensa(jogador, score,
		// nomeConquistasAdquiridas);
		// break;

	}

	@Override
	public List<Conquista> verificarNovasConquistasParaCooperacao(Score scoreDaRodada) {
		List<NomeConquista> nomeConquistasAdquiridas = new ArrayList<NomeConquista>();
		List<Conquista> minhasConquistas = new ArrayList<>();
		for (Premiacao premiacoes : scoreDaRodada.getPremiacoes()) {
			minhasConquistas.add(premiacoes.getConquista());
		}
		if (scoreDaRodada.getCountCooperacoesNaRodada() >= 1) {
			nomeConquistasAdquiridas.add(NomeConquista.AMGAPI);
			if (scoreDaRodada.getCountCooperacoesNaRodada() >= 3) {
				nomeConquistasAdquiridas.add(NomeConquista.AMGAPII);
				if (scoreDaRodada.getCountCooperacoesNaRodada() >= 5) {
					nomeConquistasAdquiridas.add(NomeConquista.AMGAPIII);
				}
			}
		}
		List<Conquista> novasConquistas = new ArrayList<Conquista>();
		for (NomeConquista nomeConquista : nomeConquistasAdquiridas) {
			Conquista c = new Conquista();
			c.setNomeConquista(nomeConquista);
			if (!verificarSePossuiConquista(c, minhasConquistas)) {
				c = findByNome(nomeConquista);
				novasConquistas.add(c);
			}
		}
		return novasConquistas;
	}

	@Override
	public Score atribuirConquistaERecompensaAoScore(Score scoreDaRodada, List<Conquista> conquistas) {

		for (Conquista conquista : conquistas) {
			scoreDaRodada.setEstrelasNaRodada(scoreDaRodada.getEstrelasNaRodada() + conquista.getRecompensaEmEstrela());
			scoreDaRodada.setPtsDeConquistaNaRodada(scoreDaRodada.getPtsDeConquistaNaRodada()
					+ (conquista.getRecompensaEmEstrela() * Pontuacao.CINCOPTS.getPts()));
			scoreDaRodada.getPremiacoes().add(new Premiacao(new Date(), conquista));
		}
		return scoreDaRodada;
	}

	@Override
	public Conquista findByNome(NomeConquista nomeConquista) {
		return repConquista.findByNomeConquista(nomeConquista);
	}

	@Override
	public boolean verificarSePossuiConquista(Conquista novaConquista, List<Conquista> conquistas) {
		return conquistas.contains(novaConquista);
	}

}

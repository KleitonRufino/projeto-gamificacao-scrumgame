package com.gerenciador.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enums.NomeConquista;
import com.gerenciador.GerenciadorConquista;
import com.model.Conquista;
import com.model.Jogador;
import com.model.Perfil;
import com.model.Premiacao;
import com.model.Rodada;
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
	public List<Conquista> verificarNovasConquistasParaAtividade(Perfil perfil, List<Conquista> novasConquistas) {
		List<NomeConquista> nomeConquistasAdquiridas = new ArrayList<NomeConquista>();
		List<Conquista> antigasConquistas = new ArrayList<Conquista>();
		for (Premiacao premiacoes : perfil.getPremiacoes()) {
			antigasConquistas.add(premiacoes.getConquista());
		}
		if (perfil.getCountAtividades() >= NomeConquista.CONQI.getNumero()) {
			nomeConquistasAdquiridas.add(NomeConquista.CONQI);
			if (perfil.getCountAtividades() >= NomeConquista.CONQII.getNumero()) {
				nomeConquistasAdquiridas.add(NomeConquista.CONQII);
				if (perfil.getCountAtividades() >= NomeConquista.CONQIII.getNumero()) {
					nomeConquistasAdquiridas.add(NomeConquista.CONQIII);
					if (perfil.getCountAtividades() >= NomeConquista.CONQIV.getNumero())
						nomeConquistasAdquiridas.add(NomeConquista.CONQIV);
				}
			}
		}
		for (NomeConquista nomeConquista : nomeConquistasAdquiridas) {
			Conquista c = new Conquista();
			c.setNomeConquista(nomeConquista);
			if (!verificarSePossuiConquista(c, antigasConquistas)) {
				c = findByNome(nomeConquista);
				novasConquistas.add(c);
			}
		}
		return novasConquistas;
	}

	@Override
	public List<Conquista> verificarNovasConquistasParaCooperacao(Jogador jogador, Perfil perfil,
			List<Conquista> novasConquistas, Rodada rodadaAtiva) {
		List<NomeConquista> nomeConquistasAdquiridas = new ArrayList<NomeConquista>();
		List<Conquista> antigasConquistas = new ArrayList<Conquista>();
		for (Premiacao premiacoes : perfil.getPremiacoes()) {
			antigasConquistas.add(premiacoes.getConquista());
		}
		if (perfil.getCountCooperacoes() >= NomeConquista.AMGAPI.getNumero()) {
			nomeConquistasAdquiridas.add(NomeConquista.AMGAPI);
			if (perfil.getCountCooperacoes() >= NomeConquista.AMGAPII.getNumero()) {
				nomeConquistasAdquiridas.add(NomeConquista.AMGAPII);
				if (perfil.getCountCooperacoes() >= NomeConquista.AMGAPIII.getNumero()) {
					nomeConquistasAdquiridas.add(NomeConquista.AMGAPIII);
					if (perfil.getCountCooperacoes() >= NomeConquista.AMGAPIV.getNumero()) {
						nomeConquistasAdquiridas.add(NomeConquista.AMGAPIV);
					}
				}
			}
		}

		if (this.repConquista.verificarCountTrabalhoEmEquipeNaRodada(jogador, rodadaAtiva)) {
			nomeConquistasAdquiridas.add(NomeConquista.TRAEQ);
		}

		for (NomeConquista nomeConquista : nomeConquistasAdquiridas) {
			Conquista c = new Conquista();
			c.setNomeConquista(nomeConquista);
			if (c.getNomeConquista().equals(NomeConquista.TRAEQ)
					&& !verificarSePossuiConquistaNaRodada(perfil, c, rodadaAtiva)) {
				c = findByNome(NomeConquista.TRAEQ);
				novasConquistas.add(c);
			} else if (!verificarSePossuiConquista(c, antigasConquistas)) {
				c = findByNome(nomeConquista);
				novasConquistas.add(c);
			}
		}
		return novasConquistas;
	}

	@Override
	public Conquista findByNome(NomeConquista nomeConquista) {
		return repConquista.findByNomeConquista(nomeConquista);
	}

	@Override
	public boolean verificarSePossuiConquista(Conquista novaConquista, List<Conquista> conquistas) {
		return conquistas.contains(novaConquista);
	}

	@Override
	public boolean verificarSePossuiConquistaNaRodada(Perfil perfil, Conquista conquista, Rodada rodadaAtiva) {
		for (Premiacao p : perfil.getPremiacoes()) {
			if (p.getConquista().getNomeConquista().equals(conquista.getNomeConquista())
					&& p.getRodada().getNumero() == rodadaAtiva.getNumero()) {
				return true;
			}
		}
		return false;
	}

}

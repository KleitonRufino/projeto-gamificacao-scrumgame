package com.gerenciador.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enums.Acao;
import com.enums.Pontuacao;
import com.enums.TipoRecompensa;
import com.gerenciador.GerenciadorPerfil;
import com.model.Caixa;
import com.model.Conquista;
import com.model.Habilidade;
import com.model.Item;
import com.model.Jogador;
import com.model.Nivel;
import com.model.Perfil;
import com.model.Personagem;
import com.model.Premiacao;
import com.model.Rodada;
import com.repositorio.PerfilRepositorio;

@Service
public class GerenciadorPerfilImpl implements GerenciadorPerfil {

	@Autowired
	private PerfilRepositorio repPerfil;

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
	public Perfil processarPtsTotais(Perfil perfil) {
		perfil.setPtsTotais(perfil.getPtsDeAmigo() + perfil.getPtsDeAtividade() + perfil.getPtsExtras());
		return perfil;
	}

	@Override
	public Perfil processarNivel(Perfil perfil, Nivel nivel) {
		perfil.setNivel(nivel);
		return perfil;
	}

	@Override
	public Perfil processaRecompensaPorNovoNivel(Perfil perfil, Caixa caixa) {
		if (caixa.getTipoRecompensa().equals(TipoRecompensa.PTS))
			perfil.setPtsExtras(perfil.getPtsExtras() + caixa.getRecompensa());
		else if (caixa.getTipoRecompensa().equals(TipoRecompensa.ESTRELAS))
			perfil.setEstrelas(perfil.getEstrelas() + caixa.getRecompensa());
		return perfil;
	}

	@Override
	public Perfil inserirPtsParaAtividadeNoPerfil(Perfil perfil, int novasAtividadesFeitas) {
		perfil.setCountAtividades(perfil.getCountAtividades() + novasAtividadesFeitas);
		perfil.setPtsDeAtividade(perfil.getPtsDeAtividade() + (novasAtividadesFeitas * Pontuacao.QUINZE.getPts())
				+ perfil.getNivel().getAumentoDePontos());
		return perfil;
	}

	@Override
	public Perfil inserirPtsParaCooperacaoNoPerfil(Perfil perfil, int novasCooperacoesFeitas) {
		perfil.setCountCooperacoes(perfil.getCountCooperacoes() + novasCooperacoesFeitas);
		perfil.setPtsDeAmigo(perfil.getPtsDeAmigo() + (novasCooperacoesFeitas * Pontuacao.DEZPTS.getPts())
				+ perfil.getNivel().getAumentoDePontos());
		return perfil;
	}

	@Override
	public Perfil inserirPtsParaPedidoDeAjudaNoPerfil(Perfil perfil, int pedidosDeAjuda) {
		perfil.setPtsDeAmigo(perfil.getPtsDeAmigo() + (pedidosDeAjuda * Pontuacao.CINCOPTS.getPts())
				+ perfil.getNivel().getAumentoDePontos());
		return perfil;
	}

	@Override
	public Perfil atribuirPremiacaoParaPerfil(Perfil perfil, Rodada rodada, List<Conquista> conquistas) {
		for (Conquista conquista : conquistas) {
			perfil.setEstrelas(perfil.getEstrelas() + conquista.getRecompensaEmEstrela());
			perfil.getPremiacoes().add(new Premiacao(new Date(), conquista, rodada));
		}
		return perfil;
	}

	@Override
	public Perfil obterPersonagemParaPerfil(Perfil perfil, Personagem personagem) {
		if (!verificarSePerfilJaPossuiItem(perfil.getMeusItens(), personagem)
				&& perfil.getNivel().getNivel() >= personagem.getNivelDesbloqueio()
				&& perfil.getEstrelas() >= personagem.getPreco()) {
			perfil.setEstrelas(perfil.getEstrelas() - personagem.getPreco());
			perfil.getMeusItens().add(new Item(personagem, perfil));
			return perfil;
		}
		return null;
	}

	private boolean verificarSePerfilJaPossuiItem(List<Item> itens, Personagem personagem) {
		for (Item item : itens) {
			if (item.getPersonagem().equals(personagem)) {
				return true;
			}
		}
		return false;
	}

	public Perfil obterHabilidadeDoPersonagemParaPerfil(Perfil perfil, Habilidade habilidade) {
		if (perfil.getNivel().getNivel() >= habilidade.getNivelDesbloqueio()
				&& perfil.getEstrelas() >= habilidade.getPreco()) {
			for (Item item : perfil.getMeusItens()) {
				if (item.getPersonagem().getId() == habilidade.getPersonagem().getId()) {
					if (item.getHabilidade() != null && item.getHabilidade().equals(habilidade))
						return null;
					else {
						perfil.setEstrelas(perfil.getEstrelas() - habilidade.getPreco());
						item.setHabilidade(habilidade);
						return perfil;
					}
				}
			}

		}
		return null;
	}

	@Override
	public Perfil inserirPtsExtrasParaCooperacaoNoPerfil(Perfil perfil, int novasCooperacoesFeitas) {
		int ptsExtras = 0;
		for (Item item : perfil.getMeusItens()) {
			if (item.getPersonagem().getAcao().equals(Acao.CONCEDE_AJUDA)) {
				ptsExtras += item.getPersonagem().getPontos() * novasCooperacoesFeitas;
				if (item.getHabilidade() != null)
					ptsExtras += item.getHabilidade().getPontos() * novasCooperacoesFeitas;

			}
		}
		perfil.setPtsExtras(perfil.getPtsExtras() + ptsExtras);
		return perfil;
	}

	@Override
	public Perfil inserirPtsExtrasParaPedidoAjudaNoPerfil(Perfil perfil, int pedidosDeAjuda) {
		int ptsExtras = 0;
		for (Item item : perfil.getMeusItens()) {
			if (item.getPersonagem().getAcao().equals(Acao.PEDIDO_AJUDA)) {
				ptsExtras += item.getPersonagem().getPontos() * pedidosDeAjuda;
				if (item.getHabilidade() != null)
					ptsExtras += item.getHabilidade().getPontos() * pedidosDeAjuda;
			}
		}
		perfil.setPtsExtras(perfil.getPtsExtras() + ptsExtras);
		return perfil;
	}

	@Override
	public Perfil inserirPtsExtrasParaAtividadeNoPerfil(Perfil perfil, int novasAtividadesFeitas) {
		int ptsExtras = 0;
		for (Item item : perfil.getMeusItens()) {
			if (item.getPersonagem().getAcao().equals(Acao.ATIVIDADE)) {
				ptsExtras += item.getPersonagem().getPontos() * novasAtividadesFeitas;
				if (item.getHabilidade() != null)
					ptsExtras += item.getHabilidade().getPontos() * novasAtividadesFeitas;

			}
		}
		perfil.setPtsExtras(perfil.getPtsExtras() + ptsExtras);
		return perfil;
	}

	@Override
	public Perfil obterRecompensaPorNovoNivel(Perfil perfil, Caixa caixa) {
		if (caixa.getTipoRecompensa().equals(TipoRecompensa.ESTRELAS))
			perfil.setEstrelas(perfil.getEstrelas() + caixa.getRecompensa());
		else if (caixa.getTipoRecompensa().equals(TipoRecompensa.PTS)) {
			perfil.setPtsExtras(perfil.getPtsExtras() + caixa.getRecompensa());
			processarPtsTotais(perfil);
		}
		return perfil;
	}

}

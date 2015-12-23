package com.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.enums.Acao;
import com.enums.NivelExperiencia;
import com.enums.Pontuacao;
import com.enums.UrlImgHabilidade;
import com.enums.UrlImgPersonagem;
import com.formandview.FormCooperacao;
import com.formandview.VisualizaConquista;
import com.formandview.VisualizaHabilidade;
import com.formandview.VisualizaHistorico;
import com.formandview.VisualizaPersonagem;
import com.gerenciador.GerenciadorAtividade;
import com.gerenciador.GerenciadorConquista;
import com.gerenciador.GerenciadorCooperacao;
import com.gerenciador.GerenciadorHabilidade;
import com.gerenciador.GerenciadorJogador;
import com.gerenciador.GerenciadorNivel;
import com.gerenciador.GerenciadorPerfil;
import com.gerenciador.GerenciadorPersonagem;
import com.gerenciador.GerenciadorRecompensa;
import com.gerenciador.GerenciadorRodada;
import com.model.Caixa;
import com.model.Conquista;
import com.model.Cooperacao;
import com.model.Habilidade;
import com.model.Item;
import com.model.Jogador;
import com.model.Nivel;
import com.model.Perfil;
import com.model.Personagem;
import com.model.Rodada;

@Transactional
@Controller
@Scope("session")
public class JogadorController implements Serializable {

	private static final long serialVersionUID = -9112224550923999935L;
	// Gerenciadores
	@Autowired
	private GerenciadorJogador gerenciadorJogador;
	@Autowired
	private GerenciadorPerfil gerenciadorPerfil;
	@Autowired
	private GerenciadorConquista gerenciadorConquista;
	@Autowired
	private GerenciadorAtividade gerenciadorAtividade;
	@Autowired
	private GerenciadorCooperacao gerenciadorCooperacao;
	@Autowired
	private GerenciadorRodada gerenciadorRodada;
	@Autowired
	private GerenciadorNivel gerenciadorNivel;
	@Autowired
	private GerenciadorRecompensa gerenciadorRecompensa;
	@Autowired
	private GerenciadorPersonagem gerenciadorPersonagem;
	@Autowired
	private GerenciadorHabilidade gerenciadorHabilidade;

	// View
	private Jogador jogador;
	private Perfil perfil;
	private VisualizaConquista visualizaConquista;
	private List<VisualizaHistorico> historico;
	private List<VisualizaPersonagem> personagensAdquiridos;
	private List<VisualizaPersonagem> personagensNaoAdquiridos;
	private List<VisualizaHabilidade> habilidadesAdquiridas;
	private List<VisualizaHabilidade> habilidadesNaoAdquiridas;
	private int porcentagemNivel;
	// Forms
	private List<Jogador> outrosJogadores;
	private FormCooperacao formCooperacao;
	private int qtdAtividade;

	// MSG
	private boolean info = false;
	private List<String> msg;

	public JogadorController() {
		this.visualizaConquista = new VisualizaConquista();
		this.formCooperacao = new FormCooperacao();
		this.personagensAdquiridos = new ArrayList<VisualizaPersonagem>();
		this.personagensNaoAdquiridos = new ArrayList<VisualizaPersonagem>();
		this.habilidadesAdquiridas = new ArrayList<VisualizaHabilidade>();
		this.habilidadesNaoAdquiridas = new ArrayList<VisualizaHabilidade>();
	}

	// public String historicoConquistas() {
	// List<Score> scores = this.gerenciadorScore.findByIdJogador(this.jogador);
	// this.historico = new ArrayList<VisualizaHistorico>();
	// for (Score score : scores) {
	// VisualizaConquista visualizaConquista = new VisualizaConquista();
	// visualizaConquista.preencheConquistas(score.getPremiacoes());
	// VisualizaHistorico h = new VisualizaHistorico(score.getRodada(),
	// visualizaConquista);
	// this.historico.add(h);
	// }
	//
	// this.historico.sort(Comparator.comparing(s ->
	// s.getRodada().getNumero()));
	// return "/historicoConquistas?faces-redirect=true";
	// }

	public void atualizaInformacoesDoJogador() {
		this.perfil = this.gerenciadorPerfil.findByNicknameDoJogador(jogador);
		this.outrosJogadores = this.gerenciadorJogador.findAllExceptMe(this.jogador.getNickname());
		this.visualizaConquista.preencheConquistas(perfil.getPremiacoes());

		this.personagensAdquiridos = new ArrayList<VisualizaPersonagem>();
		this.personagensNaoAdquiridos = new ArrayList<VisualizaPersonagem>();
		this.habilidadesAdquiridas = new ArrayList<VisualizaHabilidade>();
		this.habilidadesNaoAdquiridas = new ArrayList<VisualizaHabilidade>();

		this.personagensAdquiridos = this.listarPersonagem(gerenciadorPersonagem.findMyPersonagemByPerfil(perfil));
		this.personagensNaoAdquiridos = this
				.listarPersonagem(gerenciadorPersonagem.findNotMyPersonagemByPerfil(perfil));
		this.habilidadesAdquiridas = this.listarHabilidade(gerenciadorHabilidade.findMyHabilidadeByPerfil(perfil));
		this.habilidadesNaoAdquiridas = this
				.listarHabilidade(gerenciadorHabilidade.findNotMyHabilidadeByPerfil(perfil));

		this.porcentagemNivel = (int) (this.perfil.getNivel().getNivel() * 7.3);
		if (this.porcentagemNivel > 100)
			this.porcentagemNivel = 100;
		this.info = false;
	}

	public String cadastrarCooperacao() {

		Rodada rodadaAtiva = gerenciadorRodada.findByAtiva();

		Jogador jogadorAjudado = gerenciadorJogador.findByNickname(formCooperacao.getJogadorQueAjudei());

		Perfil perfilJogadorQueAjudou = gerenciadorPerfil.findByIdDoJogador(this.jogador);
		Perfil perfilJogadorAjudado = gerenciadorPerfil.findByIdDoJogador(jogadorAjudado);

		for (int i = 0; i < formCooperacao.getQtd(); i++) {
			Cooperacao cooperacao = gerenciadorCooperacao.criar(jogadorAjudado, rodadaAtiva);
			this.jogador.getCooperacoes().add(cooperacao);
		}
		gerenciadorJogador.atualizar(this.jogador);

		perfilJogadorAjudado = gerenciadorPerfil.inserirPtsParaPedidoDeAjudaNoPerfil(perfilJogadorAjudado,
				formCooperacao.getQtd());
		perfilJogadorAjudado = gerenciadorPerfil.inserirPtsExtrasParaPedidoAjudaNoPerfil(perfilJogadorAjudado,
				formCooperacao.getQtd());
		perfilJogadorAjudado = gerenciadorPerfil.processarPtsTotais(perfilJogadorAjudado);

		gerenciadorPerfil.atualizar(perfilJogadorAjudado);

		perfilJogadorQueAjudou = gerenciadorPerfil.inserirPtsParaCooperacaoNoPerfil(perfilJogadorQueAjudou,
				formCooperacao.getQtd());
		perfilJogadorQueAjudou = gerenciadorPerfil.inserirPtsExtrasParaPedidoAjudaNoPerfil(perfilJogadorQueAjudou,
				formCooperacao.getQtd());
		perfilJogadorQueAjudou = gerenciadorPerfil.processarPtsTotais(perfilJogadorQueAjudou);

		List<Conquista> novasConquistas = new ArrayList<Conquista>();
		novasConquistas = gerenciadorConquista.verificarNovasConquistasParaCooperacao(this.jogador,
				perfilJogadorQueAjudou, novasConquistas, rodadaAtiva);

		perfilJogadorQueAjudou = gerenciadorPerfil.atribuirPremiacaoParaPerfil(perfilJogadorQueAjudou, rodadaAtiva,
				novasConquistas);

		Nivel novo = gerenciadorNivel.verificarNivel(perfilJogadorQueAjudou);
		Nivel antigo = this.perfil.getNivel();
		boolean passouDeNivel = gerenciadorNivel.verificarNovoNivel(antigo, novo);

		Caixa recompensa = null;
		if (passouDeNivel) {
			perfilJogadorQueAjudou = gerenciadorPerfil.processarNivel(perfilJogadorQueAjudou, novo);
			recompensa = gerenciadorRecompensa.verificarRecompensaParaNovoNivel(novo);
			perfilJogadorAjudado = gerenciadorPerfil.obterRecompensaPorNovoNivel(perfilJogadorQueAjudou, recompensa);
		}

		this.perfil = perfilJogadorQueAjudou;

		msg = new ArrayList<String>();
		msg.add("UHU!!! nova ajuda(s) efetuada(s), continue assim!!! ");
		msg.add("Você ganhou + " + this.verificarPontosDeAmigoObtidos(formCooperacao.getQtd(), this.perfil)
				+ " Pontos de Amigo");
		int ptsExtras = this.verificarPontosExtrasObtidosParaAtividade(qtdAtividade, this.perfil);
		if (ptsExtras > 0) {
			msg.add("O Poder de seu personagem foi ativado e você conquistou " + ptsExtras
					+ " Pontos Extras por concluir nova(s) atividade(s)!!!");
		}
		if (novasConquistas.size() > 0) {
			String s = "YEAH!!! Você desbloqueou " + novasConquistas.size() + " nova(s) conquista(s): ";
			for (Conquista c : novasConquistas) {
				s += c.getNomeConquista().getNome() + " - ";
			}
			msg.add(s);
			msg.add("Você ganhou +" + this.verificarEstrelasConquistas(novasConquistas) + " estrelas");
		}

		if (passouDeNivel) {
			msg.add(this.perfil.getNivel().getDescricao());
			msg.add("Que bela suspresa!!!" + recompensa.getDescricao());
		}

		this.perfil = perfilJogadorQueAjudou;
		this.atualizaInformacoesDoJogador();
		this.formCooperacao = new FormCooperacao();
		info = true;
		this.visualizaConquista.preencheConquistas(perfil.getPremiacoes());
		return "/index?faces-redirect=true";
	}

	public String cadastrarAtividade() {

		Rodada rodadaAtiva = gerenciadorRodada.findByAtiva();
		Perfil perfil = this.gerenciadorPerfil.findByIdDoJogador(this.jogador);

		perfil = gerenciadorPerfil.inserirPtsParaAtividadeNoPerfil(perfil, qtdAtividade);
		perfil = gerenciadorPerfil.inserirPtsExtrasParaAtividadeNoPerfil(perfil, qtdAtividade);
		perfil = gerenciadorPerfil.processarPtsTotais(perfil);

		List<Conquista> novasConquistas = new ArrayList<Conquista>();
		novasConquistas = gerenciadorConquista.verificarNovasConquistasParaAtividade(perfil, novasConquistas);
		this.perfil = gerenciadorPerfil.atribuirPremiacaoParaPerfil(perfil, rodadaAtiva, novasConquistas);

		Nivel novo = gerenciadorNivel.verificarNivel(perfil);
		boolean passouDeNivel = gerenciadorNivel.verificarNovoNivel(perfil.getNivel(), novo);

		Caixa recompensa = null;
		if (passouDeNivel) {
			perfil = gerenciadorPerfil.processarNivel(perfil, novo);
			recompensa = gerenciadorRecompensa.verificarRecompensaParaNovoNivel(novo);
			perfil = gerenciadorPerfil.obterRecompensaPorNovoNivel(perfil, recompensa);
		}

		this.gerenciadorPerfil.atualizar(perfil);

		msg = new ArrayList<String>();
		msg.add("UHU!!! nova(s) atividade(s) concluida(s)");
		msg.add("Você ganhou + " + this.verificarPontosDeAtividadeObtidos(qtdAtividade, this.perfil)
				+ " Pontos de Atividade");

		int ptsExtras = this.verificarPontosExtrasObtidosParaAtividade(qtdAtividade, this.perfil);
		if (ptsExtras > 0) {
			msg.add("O Poder de seu personagem foi ativiado e você conquistou " + ptsExtras
					+ " Pontos Extras nesta ação!!!");
		}
		if (novasConquistas.size() > 0) {
			String s = "Você desbloqueou " + novasConquistas.size() + " conquista(s)" + "; " + "Nova(s) Conquista(s): ";
			for (Conquista c : novasConquistas) {
				s += c.getNomeConquista().getNome() + "- ";
			}
			msg.add(s);
			msg.add("Você ganhou +" + this.verificarEstrelasConquistas(novasConquistas) + " estrelas");
		}

		if (passouDeNivel) {
			msg.add(this.perfil.getNivel().getDescricao());
			msg.add("Que bela suspresa!!! " + recompensa.getDescricao());
		}

		this.perfil = perfil;
		this.atualizaInformacoesDoJogador();
		this.qtdAtividade = 0;
		this.info = true;
		this.visualizaConquista.preencheConquistas(perfil.getPremiacoes());
		return "/index?faces-redirect=true";
	}

	public String obterPersonagem(Personagem personagem) {
		Perfil perfil = this.perfil;
		perfil = gerenciadorPerfil.obterPersonagemParaPerfil(perfil, personagem);
		if (perfil == null) {
			msg = new ArrayList<String>();
			msg.add("Que Pena! você não pode obter este personagem");
			msg.add("Razões:");
			msg.add("1. Você já possui este personagem");
			msg.add("2. Seu nível é inferior ao nível de desbloqueio do personagem ");
			msg.add("3. Você não possui estrelas suficientes");
		} else {
			this.perfil = perfil;
			gerenciadorPerfil.atualizar(perfil);

			this.personagensAdquiridos = new ArrayList<VisualizaPersonagem>();
			this.personagensNaoAdquiridos = new ArrayList<VisualizaPersonagem>();
			this.personagensAdquiridos = this.listarPersonagem(gerenciadorPersonagem.findMyPersonagemByPerfil(perfil));
			this.personagensNaoAdquiridos = this
					.listarPersonagem(gerenciadorPersonagem.findNotMyPersonagemByPerfil(perfil));

			msg = new ArrayList<String>();
			msg.add("Muito Bem!!! Você obteve um novo personagem");
			msg.add("Habilidades:");
			msg.add("Você agora ganha " + personagem.getPontos()
					+ " pontos extras sempre quando tiver um ação relacionada a " + personagem.getAcao());
		}
		this.atualizaInformacoesDoJogador();
		info = true;
		return "/index?faces-redirect=true";
	}

	public String obterHabilidade(Habilidade habilidade) {
		Perfil perfil = this.perfil;
		perfil = gerenciadorPerfil.obterHabilidadeDoPersonagemParaPerfil(perfil, habilidade);
		if (perfil == null) {
			msg = new ArrayList<String>();
			msg.add("Que Pena! você não pode obter esta habilidade");
			msg.add("Razões:");
			msg.add("1. Você não possui o personagem que contém esta habilidade");
			msg.add("2. Seu nível é inferior ao nível de desbloqueio da habilidade");
			msg.add("3. Você não possui estrelas suficientes");
		} else {
			this.perfil = perfil;
			gerenciadorPerfil.atualizar(perfil);
			msg = new ArrayList<String>();
			msg.add("Muito Bem!!! Você obteve a habilidade do  personagem " + habilidade.getPersonagem().getNome());
			msg.add("Habilidades:");
			msg.add("Seu personagem agora tem o poder aumentado em " + habilidade.getPontos()
					+ " pontos extras sempre quando");
		}

		this.atualizaInformacoesDoJogador();
		this.info = true;
		return "/index?faces-redirect=true";
	}

	public int verificarPontosDeAmigoObtidos(int novasCooperacoesFeitas, Perfil perfil) {
		return (novasCooperacoesFeitas * Pontuacao.DEZPTS.getPts()) + perfil.getNivel().getAumentoDePontos();
	}

	public int verificarPontosDeAtividadeObtidos(int novasAtividades, Perfil perfil) {
		return (novasAtividades * Pontuacao.QUINZE.getPts()) + perfil.getNivel().getAumentoDePontos();
	}

	public int verificarEstrelasConquistas(List<Conquista> conquistas) {
		int estrelas = 0;
		for (Conquista conquista : conquistas) {
			estrelas += conquista.getRecompensaEmEstrela();
		}
		return estrelas;
	}

	public int verificarPontosExtrasObtidosParaAtividade(int novasAtividadesFeitas, Perfil perfil) {
		int ptsExtras = 0;
		for (Item item : perfil.getMeusItens()) {
			if (item.getPersonagem().getAcao().equals(Acao.ATIVIDADE)) {
				ptsExtras += item.getPersonagem().getPontos() * novasAtividadesFeitas;
				if (item.getHabilidade() != null)
					ptsExtras += item.getHabilidade().getPontos() * novasAtividadesFeitas;

			}
		}
		return ptsExtras;
	}

	public int verificarPontosExtrasObtidosParaCooperacao(int novasCooperacoes, Perfil perfil) {
		int ptsExtras = 0;
		for (Item item : perfil.getMeusItens()) {
			if (item.getPersonagem().getAcao().equals(Acao.CONCEDE_AJUDA)) {
				ptsExtras += item.getPersonagem().getPontos() * novasCooperacoes;
				if (item.getHabilidade() != null)
					ptsExtras += item.getHabilidade().getPontos() * novasCooperacoes;

			}
		}
		return ptsExtras;
	}

	public int verificarPontosExtrasObtidosParaPedidoDeAjuda(int pedidoDeAjuda, Perfil perfil) {
		int ptsExtras = 0;
		for (Item item : perfil.getMeusItens()) {
			if (item.getPersonagem().getAcao().equals(Acao.PEDIDO_AJUDA)) {
				ptsExtras += item.getPersonagem().getPontos() * pedidoDeAjuda;
				if (item.getHabilidade() != null)
					ptsExtras += item.getHabilidade().getPontos() * pedidoDeAjuda;

			}
		}
		return ptsExtras;
	}

	public boolean verificarSeAtingiuNovoNivelDeExperiencia(NivelExperiencia ne1, NivelExperiencia ne2) {
		return ne1.getNome().equals(ne2.getNome());
	}

	public List<VisualizaPersonagem> listarPersonagem(List<Personagem> personagens) {
		List<VisualizaPersonagem> list = new ArrayList<>();
		for (Personagem personagem : personagens) {
			list.add(new VisualizaPersonagem(personagem,
					UrlImgPersonagem.identificarUrlPersonagem(personagem.getNome())));
		}
		return list;
	}

	public List<VisualizaHabilidade> listarHabilidade(List<Habilidade> habilidades) {
		List<VisualizaHabilidade> list = new ArrayList<>();
		for (Habilidade habilidade : habilidades) {
			list.add(new VisualizaHabilidade(UrlImgHabilidade.identificarUrlHabilidade(habilidade.getNome()),
					habilidade));
		}
		return list;
	}

	public Jogador getJogador() {
		return jogador;
	}

	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}

	public GerenciadorJogador getGerenciadorJogador() {
		return gerenciadorJogador;
	}

	public void setGerenciadorJogador(GerenciadorJogador gerenciadorJogador) {
		this.gerenciadorJogador = gerenciadorJogador;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public GerenciadorPerfil getGerenciadorPerfil() {
		return gerenciadorPerfil;
	}

	public void setGerenciadorPerfil(GerenciadorPerfil gerenciadorPerfil) {
		this.gerenciadorPerfil = gerenciadorPerfil;
	}

	public List<Jogador> getOutrosJogadores() {
		return outrosJogadores;
	}

	public void setOutrosJogadores(List<Jogador> outrosJogadores) {
		this.outrosJogadores = outrosJogadores;
	}

	public FormCooperacao getFormCooperacao() {
		return formCooperacao;
	}

	public void setFormCooperacao(FormCooperacao formCooperacao) {
		this.formCooperacao = formCooperacao;
	}

	public int getQtdAtividade() {
		return qtdAtividade;
	}

	public void setQtdAtividade(int qtdAtividade) {
		this.qtdAtividade = qtdAtividade;
	}

	public GerenciadorConquista getGerenciadorConquista() {
		return gerenciadorConquista;
	}

	public GerenciadorRecompensa getGerenciadorRecompensa() {
		return gerenciadorRecompensa;
	}

	public void setGerenciadorRecompensa(GerenciadorRecompensa gerenciadorRecompensa) {
		this.gerenciadorRecompensa = gerenciadorRecompensa;
	}

	public GerenciadorNivel getGerenciadorNivel() {
		return gerenciadorNivel;
	}

	public void setGerenciadorNivel(GerenciadorNivel gerenciadorNivel) {
		this.gerenciadorNivel = gerenciadorNivel;
	}

	public void setGerenciadorConquista(GerenciadorConquista gerenciadorConquista) {
		this.gerenciadorConquista = gerenciadorConquista;
	}

	public GerenciadorAtividade getGerenciadorAtividade() {
		return gerenciadorAtividade;
	}

	public void setGerenciadorAtividade(GerenciadorAtividade gerenciadorAtividade) {
		this.gerenciadorAtividade = gerenciadorAtividade;
	}

	public GerenciadorCooperacao getGerenciadorCooperacao() {
		return gerenciadorCooperacao;
	}

	public void setGerenciadorCooperacao(GerenciadorCooperacao gerenciadorCooperacao) {
		this.gerenciadorCooperacao = gerenciadorCooperacao;
	}

	public GerenciadorRodada getGerenciadorRodada() {
		return gerenciadorRodada;
	}

	public void setGerenciadorRodada(GerenciadorRodada gerenciadorRodada) {
		this.gerenciadorRodada = gerenciadorRodada;
	}

	public boolean isInfo() {
		return info;
	}

	public void setInfo(boolean info) {
		this.info = info;
	}

	public List<String> getMsg() {
		return msg;
	}

	public void setMsg(List<String> msg) {
		this.msg = msg;
	}

	public List<VisualizaHistorico> getHistorico() {
		return historico;
	}

	public void setHistorico(List<VisualizaHistorico> historico) {
		this.historico = historico;
	}

	public VisualizaConquista getVisualizaConquista() {
		return visualizaConquista;
	}

	public void setVisualizaConquista(VisualizaConquista visualizaConquista) {
		this.visualizaConquista = visualizaConquista;
	}

	public GerenciadorPersonagem getGerenciadorPersonagem() {
		return gerenciadorPersonagem;
	}

	public void setGerenciadorPersonagem(GerenciadorPersonagem gerenciadorPersonagem) {
		this.gerenciadorPersonagem = gerenciadorPersonagem;
	}

	public GerenciadorHabilidade getGerenciadorHabilidade() {
		return gerenciadorHabilidade;
	}

	public void setGerenciadorHabilidade(GerenciadorHabilidade gerenciadorHabilidade) {
		this.gerenciadorHabilidade = gerenciadorHabilidade;
	}

	public List<VisualizaPersonagem> getPersonagensAdquiridos() {
		return personagensAdquiridos;
	}

	public void setPersonagensAdquiridos(List<VisualizaPersonagem> personagensAdquiridos) {
		this.personagensAdquiridos = personagensAdquiridos;
	}

	public List<VisualizaPersonagem> getPersonagensNaoAdquiridos() {
		return personagensNaoAdquiridos;
	}

	public void setPersonagensNaoAdquiridos(List<VisualizaPersonagem> personagensNaoAdquiridos) {
		this.personagensNaoAdquiridos = personagensNaoAdquiridos;
	}

	public List<VisualizaHabilidade> getHabilidadesAdquiridas() {
		return habilidadesAdquiridas;
	}

	public void setHabilidadesAdquiridas(List<VisualizaHabilidade> habilidadesAdquiridas) {
		this.habilidadesAdquiridas = habilidadesAdquiridas;
	}

	public List<VisualizaHabilidade> getHabilidadesNaoAdquiridas() {
		return habilidadesNaoAdquiridas;
	}

	public void setHabilidadesNaoAdquiridas(List<VisualizaHabilidade> habilidadesNaoAdquiridas) {
		this.habilidadesNaoAdquiridas = habilidadesNaoAdquiridas;
	}

	public int getPorcentagemNivel() {
		return porcentagemNivel;
	}

	public void setPorcentagemNivel(int porcentagemNivel) {
		this.porcentagemNivel = porcentagemNivel;
	}

}

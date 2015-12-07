package com.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.formandview.FormCooperacao;
import com.formandview.VisualizaConquista;
import com.formandview.VisualizaHistorico;
import com.gerenciador.GerenciadorAtividade;
import com.gerenciador.GerenciadorConquista;
import com.gerenciador.GerenciadorCooperacao;
import com.gerenciador.GerenciadorJogador;
import com.gerenciador.GerenciadorPerfil;
import com.gerenciador.GerenciadorRodada;
import com.gerenciador.GerenciadorScore;
import com.model.Conquista;
import com.model.Cooperacao;
import com.model.Jogador;
import com.model.Perfil;
import com.model.Rodada;
import com.model.Score;

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
	private GerenciadorScore gerenciadorScore;
	@Autowired
	private GerenciadorConquista gerenciadorConquista;
	@Autowired
	private GerenciadorAtividade gerenciadorAtividade;
	@Autowired
	private GerenciadorCooperacao gerenciadorCooperacao;
	@Autowired
	private GerenciadorRodada gerenciadorRodada;

	// View
	private Jogador jogador;
	private Perfil perfil;
	private VisualizaConquista visualizaConquistaRodadaAtual;
	private List<VisualizaHistorico> historico;

	// Forms
	private List<Jogador> outrosJogadores;
	private FormCooperacao formCooperacao;
	private int qtdAtividade;

	// MSG
	private boolean info = false;
	private String msg = "";

	public JogadorController() {
		this.visualizaConquistaRodadaAtual = new VisualizaConquista();
		this.formCooperacao = new FormCooperacao();
	}

	public String historicoConquistas() {
		List<Score> scores = this.gerenciadorScore.findByIdJogador(this.jogador);
		this.historico = new ArrayList<VisualizaHistorico>();
		for (Score score : scores) {
			VisualizaConquista visualizaConquista = new VisualizaConquista();
			visualizaConquista.preencheConquistas(score.getPremiacoes());
			VisualizaHistorico h = new VisualizaHistorico(score.getRodada(), visualizaConquista);
			this.historico.add(h);
		}

		this.historico.sort(Comparator.comparing(s -> s.getRodada().getNumero()));
		return "/historicoConquistas?faces-redirect=true";
	}

	public void atualizaInformacoesDoJogador() {
		this.perfil = this.gerenciadorPerfil.findByNicknameDoJogador(jogador);
		this.outrosJogadores = this.gerenciadorJogador.findAllExceptMe(this.jogador.getNickname());
		Score scoreRodadaAtual = this.gerenciadorScore.findByRodadaAtivaAndIdJogador(jogador);
		visualizaConquistaRodadaAtual.preencheConquistas(scoreRodadaAtual.getPremiacoes());
	}

	public String cadastrarCooperacao() {
		Rodada rodadaAtiva = this.gerenciadorRodada.findByAtiva();
		Jogador jogadorQueAjudou = gerenciadorJogador.findByNicknameComCooperacoes(this.jogador.getNickname());
		Jogador jogadorAjudado = this.gerenciadorJogador.findByNickname(formCooperacao.getJogadorQueAjudei());

		Perfil perfilJogadorQueAjudou = gerenciadorPerfil.findByIdDoJogador(jogadorQueAjudou);
		Score scoreDaRodadaJogadorQueAjudou = gerenciadorScore.findByRodadaAtivaAndIdJogador(jogadorQueAjudou);

		Perfil perfilJogadorAjudado = gerenciadorPerfil.findByIdDoJogador(jogadorAjudado);
		Score scoreDaRodadaJogadorAjudado = gerenciadorScore.findByRodadaAtivaAndIdJogador(jogadorAjudado);

		Cooperacao cooperacao = gerenciadorCooperacao.criar(jogadorAjudado, rodadaAtiva);
		jogadorQueAjudou.getCooperacoes().add(cooperacao);

		gerenciadorJogador.atualizar(jogadorQueAjudou);

		scoreDaRodadaJogadorAjudado = gerenciadorCooperacao.solicitarPtsPedidoDeAjuda(scoreDaRodadaJogadorAjudado);
		gerenciadorScore.atualizar(scoreDaRodadaJogadorAjudado);
		List<Score> scoresJogadorAjudado = gerenciadorScore.findByIdJogador(jogadorAjudado);
		perfilJogadorAjudado = gerenciadorPerfil.processarDadosDoPerfil(perfilJogadorAjudado, scoresJogadorAjudado);
		gerenciadorPerfil.atualizar(perfilJogadorAjudado);

		scoreDaRodadaJogadorQueAjudou = gerenciadorCooperacao.solicitarNovaCooperacao(scoreDaRodadaJogadorQueAjudou,
				formCooperacao.getQtd());
		List<Conquista> novasConquistas = gerenciadorConquista
				.verificarNovasConquistasParaCooperacao(scoreDaRodadaJogadorQueAjudou);
		scoreDaRodadaJogadorQueAjudou = gerenciadorConquista
				.atribuirConquistaERecompensaAoScore(scoreDaRodadaJogadorQueAjudou, novasConquistas);
		gerenciadorScore.atualizar(scoreDaRodadaJogadorQueAjudou);
		List<Score> scoresJogadorQueAjudou = gerenciadorScore.findByIdJogador(jogadorQueAjudou);
		perfilJogadorQueAjudou = gerenciadorPerfil.processarDadosDoPerfil(perfilJogadorQueAjudou,
				scoresJogadorQueAjudou);
		gerenciadorPerfil.atualizar(perfilJogadorQueAjudou);

		this.perfil = perfilJogadorQueAjudou;
		this.formCooperacao = new FormCooperacao();

		msg = "AJUDA CADASTRADA; ";
		if (novasConquistas.size() > 0) {
			msg += "Você desbloqueou " + novasConquistas.size() + " conquista(s)" + "; " + "Nova(s) Conquista(s): ";
			for (Conquista c : novasConquistas) {
				msg += c.getNomeConquista().getNome() + " | ";
			}
		}

		info = true;
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(null, msg));

		return null;
	}

	public String cadastrarAtividade() {

		Perfil perfil = this.gerenciadorPerfil.findByIdDoJogador(this.jogador);
		Score scoreDaRodada = this.gerenciadorScore.findByRodadaAtivaAndIdJogador(this.jogador);

		scoreDaRodada = this.gerenciadorAtividade.solicitarNovaAtividade(scoreDaRodada, this.qtdAtividade);
		List<Conquista> novasConquistas = this.gerenciadorConquista
				.verificarNovasConquistasParaAtividade(scoreDaRodada);
		scoreDaRodada = this.gerenciadorConquista.atribuirConquistaERecompensaAoScore(scoreDaRodada, novasConquistas);

		gerenciadorScore.atualizar(scoreDaRodada);

		List<Score> scores = this.gerenciadorScore.findByIdJogador(jogador);
		perfil = this.gerenciadorPerfil.processarDadosDoPerfil(perfil, scores);

		this.gerenciadorPerfil.atualizar(perfil);

		this.perfil = perfil;
		this.qtdAtividade = 0;

		msg = "ATIVIDADE CADASTRADA; ";
		if (novasConquistas.size() > 0) {
			msg += "Você desbloqueou " + novasConquistas.size() + " conquista(s)" + "; " + "Nova(s) Conquista(s): ";
			for (Conquista c : novasConquistas) {
				msg += c.getNomeConquista().getNome() + " | ";
			}
		}

		info = true;

		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(null, msg));

		return null;
	}

	public void atualizaMsg() {
		this.info = false;
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

	public GerenciadorScore getGerenciadorScore() {
		return gerenciadorScore;
	}

	public void setGerenciadorScore(GerenciadorScore gerenciadorScore) {
		this.gerenciadorScore = gerenciadorScore;
	}

	public VisualizaConquista getVisualizaConquistaRodadaAtual() {
		return visualizaConquistaRodadaAtual;
	}

	public void setVisualizaConquistaRodadaAtual(VisualizaConquista visualizaConquistaRodadaAtual) {
		this.visualizaConquistaRodadaAtual = visualizaConquistaRodadaAtual;
	}

	public GerenciadorConquista getGerenciadorConquista() {
		return gerenciadorConquista;
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

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<VisualizaHistorico> getHistorico() {
		return historico;
	}

	public void setHistorico(List<VisualizaHistorico> historico) {
		this.historico = historico;
	}

}

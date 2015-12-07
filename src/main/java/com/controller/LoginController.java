package com.controller;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gerenciador.GerenciadorJogador;
import com.gerenciador.impl.GerenciadorJogadorImpl;
import com.model.Jogador;

@Controller
@Scope("request")
public class LoginController {

	private String message;
	private Jogador jogador;
	@Autowired
	private GerenciadorJogador gerenciadorJogador;
	@Autowired
	private JogadorController jogadorController;

	LoginController() {
		this.jogador = new Jogador();
		this.gerenciadorJogador = new GerenciadorJogadorImpl();
	}

	public String login() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		jogador = this.gerenciadorJogador.findByNicknameAndSenha(jogador.getNickname(), jogador.getSenha());
		if (jogador != null) {
			if (session == null) {
				session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			}
			session.setAttribute("jogador", jogador);
			jogadorController.setJogador(jogador);
			jogadorController.atualizaInformacoesDoJogador();
			return "/index?faces-redirect=true";
		} else {
			reload();
			if (session != null) {
				session.invalidate();
			}
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Login Falhou", "username e/ou senha inválido(s)"));
		}
		return null;
	}

	public String logout() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		request.getSession().invalidate();
		return "/login?faces-redirect=true";
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public GerenciadorJogador getGerenciadorJogador() {
		return gerenciadorJogador;
	}

	public void setGerenciadorJogador(GerenciadorJogador gerenciadorJogador) {
		this.gerenciadorJogador = gerenciadorJogador;
	}

	public Jogador getJogador() {
		return jogador;
	}

	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}

	public void reload() {
		this.jogador = new Jogador();
	}

	public JogadorController getJogadorController() {
		return jogadorController;
	}

	public void setJogadorController(JogadorController jogadorController) {
		this.jogadorController = jogadorController;
	}

}

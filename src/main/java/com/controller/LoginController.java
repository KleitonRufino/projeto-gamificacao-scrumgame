package com.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.springframework.stereotype.Component;

import com.mensagens.Message;

@Component
@ManagedBean
@RequestScoped
public class LoginController {
	private String nickname;
	private String senha;
	private boolean info;
	private String message;

	public LoginController() {
		this.info = false;
	}

	public String login() {

		if (nickname != null && nickname.equals("admin") && senha != null && senha.equals("admin")) {
			return "/index?faces-redirect=true";
		} else {
			info = true;
			message = Message.messageLoginWarning;
		}

		return null;
	}

	public String logout() {
		return "/login?faces-redirect=true";
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean isInfo() {
		return info;
	}

	public void setInfo(boolean info) {
		this.info = info;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}

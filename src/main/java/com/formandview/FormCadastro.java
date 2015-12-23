package com.formandview;

public class FormCadastro {

	private String nickname;
	private String senha;
	private String confirmaSenha;
	private int avatar;

	public FormCadastro() {
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

	public int getAvatar() {
		return avatar;
	}

	public void setAvatar(int avatar) {
		this.avatar = avatar;
	}

	public String getConfirmaSenha() {
		return confirmaSenha;
	}

	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}

	@Override
	public String toString() {
		return "FormCadastro [nickname=" + nickname + ", senha=" + senha + ", confirmaSenha=" + confirmaSenha
				+ ", avatar=" + avatar + "]";
	}

}

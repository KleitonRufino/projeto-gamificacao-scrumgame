package com.controller;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.enums.Acao;
import com.enums.Avatar;
import com.enums.NomeConquista;
import com.enums.Recompensa;
import com.enums.TipoRecompensa;
import com.formandview.FormCadastro;
import com.gerenciador.GerenciadorConquista;
import com.gerenciador.GerenciadorHabilidade;
import com.gerenciador.GerenciadorJogador;
import com.gerenciador.GerenciadorNivel;
import com.gerenciador.GerenciadorPerfil;
import com.gerenciador.GerenciadorPersonagem;
import com.gerenciador.GerenciadorRecompensa;
import com.gerenciador.GerenciadorRodada;
import com.gerenciador.impl.GerenciadorJogadorImpl;
import com.model.Caixa;
import com.model.Conquista;
import com.model.Habilidade;
import com.model.Jogador;
import com.model.Nivel;
import com.model.Perfil;
import com.model.Personagem;
import com.model.Rodada;

@Transactional
@Controller
@Scope("request")
public class LoginController {

	private String message;
	private Jogador jogador;
	@Autowired
	private GerenciadorJogador gerenciadorJogador;
	@Autowired
	private JogadorController jogadorController;
	@Autowired
	private GerenciadorNivel gerenciadorNivel;
	@Autowired
	private GerenciadorPerfil gerenciadorPerfil;
	@Autowired
	private GerenciadorConquista gerenciadorConquista;
	@Autowired
	private GerenciadorRodada gerenciadorRodada;
	@Autowired
	private GerenciadorPersonagem gerenciadorPersonagem;
	@Autowired
	private GerenciadorHabilidade gerenciadorHabilidade;
	@Autowired
	private GerenciadorRecompensa gerenciadorRecompensa;
	private FormCadastro formCadastro;

	LoginController() {
		this.jogador = new Jogador();
		this.gerenciadorJogador = new GerenciadorJogadorImpl();
		this.formCadastro = new FormCadastro();
	}

	public String login() {

		if (jogador.getNickname().equals("admin") && jogador.getSenha().equals("admin")) {
			return "/admin?faces-redirect=true";
		}

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

	public String cadastrar() {
		if (!formCadastro.getNickname().isEmpty() && !formCadastro.getSenha().isEmpty()
				&& !formCadastro.getConfirmaSenha().isEmpty() && formCadastro.getAvatar() != 0) {
			if (formCadastro.getSenha().equals(formCadastro.getConfirmaSenha())) {
				Nivel nivel = this.gerenciadorNivel.findByNivel(0);
				Jogador jogador = new Jogador(formCadastro.getNickname(), formCadastro.getConfirmaSenha(),
						Avatar.getAvatar(formCadastro.getAvatar()));
				Perfil perfil = new Perfil(nivel, 0, 0, 0, 0, 0, 0, 0);
				perfil.setJogador(jogador);

				this.gerenciadorJogador.salvar(jogador);
				this.gerenciadorPerfil.salvar(perfil);
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Cadastro Realizado!",
						"O jogador " + formCadastro.getNickname() + " foi cadastrado!"));
				this.formCadastro = new FormCadastro();

			} else {
				this.formCadastro.setSenha("");
				this.formCadastro.setConfirmaSenha("");
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Senhas diferentes!", "preencha a mesma senha"));
			}

		} else {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Dados incompletos!", "preencha todos os dados"));

		}

		return null;
	}

	public String logout() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		request.getSession().invalidate();
		return "/login?faces-redirect=true";
	}

	public String iniciar() {
		this.rodada();
		this.conquista();
		this.addPersonagemAndHabilidade();
		this.nivel();
		this.addCaixa();
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Sucesso!"));
		return null;
	}

	public void rodada() {
		Rodada r1 = new Rodada("Rodada 1", 1, true);
		Rodada r2 = new Rodada("Rodada 2", 2, false);
		Rodada r3 = new Rodada("Rodada 3", 3, false);

		gerenciadorRodada.salvar(r1);
		gerenciadorRodada.salvar(r2);
		gerenciadorRodada.salvar(r3);

	}

	public void nivel() {
		Nivel nivel0 = new Nivel("", 0, 0, 49, 0, Recompensa.NENHUMA);
		Nivel nivel1 = new Nivel(
				"Parabéns!!! Você chegou ao nível 1, suas ações agora valem 1 ponto a mais. Confira na loja os personagens e habilidades desbloqueaveis para esse nível, adquira-os e ganhe mais pontos nas suas ações.",
				1, 50, 109, 1, Recompensa.CAIXA);
		Nivel nivel2 = new Nivel(
				"Parabéns!!! Você chegou ao nível 2. Confira na loja os personagens e habilidades desbloqueaveis para esse nível, adquira-os e ganhe mais pontos nas suas ações.",
				2, 110, 169, 1, Recompensa.CAIXA);
		Nivel nivel3 = new Nivel(
				"Parabéns!!! Você chegou ao nível 3, suas ações agora valem 2 pontos a mais. Confira na loja os personagens e habilidades desbloqueaveis para esse nível, adquira-os e ganhe mais pontos nas suas ações.",
				3, 170, 229, 2, Recompensa.CAIXA);
		Nivel nivel4 = new Nivel(
				"Parabéns!!! Você chegou ao nível 4. Confira na loja os personagens e habilidades desbloqueaveis para esse nível, adquira-os e ganhe mais pontos nas suas ações.",
				4, 230, 299, 2, Recompensa.CAIXA);
		Nivel nivel5 = new Nivel("Parabéns!!! Você chegou ao nível 5, suas ações agora valem 3 pontos a mais", 5, 300,
				369, 3, Recompensa.CAIXA);
		Nivel nivel6 = new Nivel("Parabéns!!! Você chegou ao nível 6.", 6, 370, 439, 3, Recompensa.CAIXA);
		Nivel nivel7 = new Nivel("Parabéns!!! Você chegou ao nível 7, suas ações agora valem 4 pontos a mais.", 7, 440,
				519, 4, Recompensa.CAIXA);
		Nivel nivel8 = new Nivel("Parabéns!!! Você chegou ao nível 8.", 8, 520, 599, 4, Recompensa.CAIXA);
		Nivel nivel9 = new Nivel("Parabéns!!! Você chegou ao nível 9.", 9, 600, 679, 4, Recompensa.CAIXA);
		Nivel nivel10 = new Nivel("Parabéns!!! Você chegou ao nível 10, suas ações agora valem 5 pontos a mais.", 10,
				680, 769, 5, Recompensa.CAIXA);
		Nivel nivel11 = new Nivel("Parabéns!!! Você chegou ao nível 11.", 11, 770, 859, 5, Recompensa.CAIXA);
		Nivel nivel12 = new Nivel("Parabéns!!! Você chegou ao nível 12.", 12, 860, 939, 5, Recompensa.CAIXA);
		Nivel nivel13 = new Nivel("Parabéns!!! Você chegou ao nível 13.", 13, 940, 1039, 5, Recompensa.CAIXA);
		Nivel nivel14 = new Nivel("Parabéns!!! Você chegou ao nível 14, suas ações agora valem 6 pontos a mais.", 14,
				1040, Integer.MAX_VALUE, 6, Recompensa.CAIXA);

		gerenciadorNivel.salvar(nivel0);
		gerenciadorNivel.salvar(nivel1);
		gerenciadorNivel.salvar(nivel2);
		gerenciadorNivel.salvar(nivel3);
		gerenciadorNivel.salvar(nivel4);
		gerenciadorNivel.salvar(nivel5);
		gerenciadorNivel.salvar(nivel6);
		gerenciadorNivel.salvar(nivel7);
		gerenciadorNivel.salvar(nivel8);
		gerenciadorNivel.salvar(nivel9);
		gerenciadorNivel.salvar(nivel10);
		gerenciadorNivel.salvar(nivel11);
		gerenciadorNivel.salvar(nivel12);
		gerenciadorNivel.salvar(nivel13);
		gerenciadorNivel.salvar(nivel14);

	}

	public void conquista() {
		Conquista conquista1 = new Conquista(NomeConquista.AMGAPI, "Coopere uma vez com sua equipe",
				NomeConquista.AMGAPI.getRecompensa(), 0);
		Conquista conquista2 = new Conquista(NomeConquista.AMGAPII, "Coopere cinco vezes com sua equipe",
				NomeConquista.AMGAPII.getRecompensa(), 0);
		Conquista conquista3 = new Conquista(NomeConquista.AMGAPIII, "Coopere nove vezes com sua equipe",
				NomeConquista.AMGAPIII.getRecompensa(), 0);
		Conquista conquista4 = new Conquista(NomeConquista.AMGAPIV, "Coopere treze vezes com sua equipe",
				NomeConquista.AMGAPIV.getRecompensa(), 0);
		Conquista conquista5 = new Conquista(NomeConquista.CONQI, "Conclua uma atividade",
				NomeConquista.CONQI.getRecompensa(), 0);
		Conquista conquista6 = new Conquista(NomeConquista.CONQII, "Conclua quatro atividades",
				NomeConquista.CONQII.getRecompensa(), 0);
		Conquista conquista7 = new Conquista(NomeConquista.CONQIII, "Conclua sete atividades",
				NomeConquista.CONQIII.getRecompensa(), 0);

		Conquista conquista8 = new Conquista(NomeConquista.CONQIV, "Conclua dez atividades",
				NomeConquista.CONQIV.getRecompensa(), 0);
		Conquista conquista9 = new Conquista(NomeConquista.HERSPRINT, "Conclua todas as atividades da Sprint",
				NomeConquista.HERSPRINT.getRecompensa(), 0);
		Conquista conquista10 = new Conquista(NomeConquista.TRAEQ, "Coopere com todos de sua equipe",
				NomeConquista.TRAEQ.getRecompensa(), 0);

		gerenciadorConquista.salvar(conquista1);
		gerenciadorConquista.salvar(conquista2);
		gerenciadorConquista.salvar(conquista3);
		gerenciadorConquista.salvar(conquista4);
		gerenciadorConquista.salvar(conquista5);
		gerenciadorConquista.salvar(conquista6);
		gerenciadorConquista.salvar(conquista7);
		gerenciadorConquista.salvar(conquista8);
		gerenciadorConquista.salvar(conquista9);
		gerenciadorConquista.salvar(conquista10);

	}

	public void addPersonagemAndHabilidade() {

		Personagem p1 = new Personagem("Person 1", Acao.CONCEDE_AJUDA, 5, 5, 1);
		Personagem p2 = new Personagem("Person 2", Acao.PEDIDO_AJUDA, 5, 7, 2);
		Personagem p3 = new Personagem("Person 3", Acao.ATIVIDADE, 5, 9, 3);

		Habilidade h1 = new Habilidade(p1, "Habilidade Person 1", 4, 2, 5);
		Habilidade h2 = new Habilidade(p2, "Habilidade Person 2", 6, 3, 5);
		Habilidade h3 = new Habilidade(p3, "Habilidade Person 3", 8, 4, 5);

		gerenciadorPersonagem.salvar(p1);
		gerenciadorPersonagem.salvar(p2);
		gerenciadorPersonagem.salvar(p3);

		gerenciadorHabilidade.salvar(h1);
		gerenciadorHabilidade.salvar(h2);
		gerenciadorHabilidade.salvar(h3);
	}

	public void addCaixa() {
		Caixa caixa1 = new Caixa("Você ganhou + 2 estrelas", TipoRecompensa.ESTRELAS, 2);
		Caixa caixa2 = new Caixa("Você ganhou + 3 estrelas", TipoRecompensa.ESTRELAS, 3);
		Caixa caixa3 = new Caixa("Você ganhou + 15 Pontos Extras", TipoRecompensa.PTS, 15);
		Caixa caixa4 = new Caixa("Você ganhou + 20 Pontos Extras", TipoRecompensa.PTS, 20);
		gerenciadorRecompensa.salvar(caixa1);
		gerenciadorRecompensa.salvar(caixa2);
		gerenciadorRecompensa.salvar(caixa3);
		gerenciadorRecompensa.salvar(caixa4);

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

	public FormCadastro getFormCadastro() {
		return formCadastro;
	}

	public void setFormCadastro(FormCadastro formCadastro) {
		this.formCadastro = formCadastro;
	}

	public GerenciadorNivel getGerenciadorNivel() {
		return gerenciadorNivel;
	}

	public void setGerenciadorNivel(GerenciadorNivel gerenciadorNivel) {
		this.gerenciadorNivel = gerenciadorNivel;
	}

	public GerenciadorPerfil getGerenciadorPerfil() {
		return gerenciadorPerfil;
	}

	public void setGerenciadorPerfil(GerenciadorPerfil gerenciadorPerfil) {
		this.gerenciadorPerfil = gerenciadorPerfil;
	}

	public GerenciadorConquista getGerenciadorConquista() {
		return gerenciadorConquista;
	}

	public void setGerenciadorConquista(GerenciadorConquista gerenciadorConquista) {
		this.gerenciadorConquista = gerenciadorConquista;
	}

	public GerenciadorRodada getGerenciadorRodada() {
		return gerenciadorRodada;
	}

	public void setGerenciadorRodada(GerenciadorRodada gerenciadorRodada) {
		this.gerenciadorRodada = gerenciadorRodada;
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

	public GerenciadorRecompensa getGerenciadorRecompensa() {
		return gerenciadorRecompensa;
	}

	public void setGerenciadorRecompensa(GerenciadorRecompensa gerenciadorRecompensa) {
		this.gerenciadorRecompensa = gerenciadorRecompensa;
	}

}

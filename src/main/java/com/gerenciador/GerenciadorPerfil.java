package com.gerenciador;

import java.util.List;

import com.model.Caixa;
import com.model.Conquista;
import com.model.Habilidade;
import com.model.Jogador;
import com.model.Nivel;
import com.model.Perfil;
import com.model.Personagem;
import com.model.Rodada;

public interface GerenciadorPerfil {

	public void salvar(Perfil perfil);

	public void atualizar(Perfil perfil);

	public Perfil findByIdDoJogador(Jogador jogador);

	public Perfil findByNicknameDoJogador(Jogador jogador);

	public Perfil processarPtsTotais(Perfil perfil);

	public Perfil processarNivel(Perfil perfil, Nivel nivel);

	public Perfil processaRecompensaPorNovoNivel(Perfil perfil, Caixa caixa);

	public Perfil inserirPtsParaAtividadeNoPerfil(Perfil perfil, int novasAtividadesFeitas);

	public Perfil inserirPtsParaCooperacaoNoPerfil(Perfil perfil, int novasCooperacoesFeitas);

	public Perfil inserirPtsParaPedidoDeAjudaNoPerfil(Perfil perfil, int pedidosDeAjuda);

	public Perfil atribuirPremiacaoParaPerfil(Perfil perfil, Rodada rodada, List<Conquista> conquistas);

	public Perfil obterPersonagemParaPerfil(Perfil perfil, Personagem personagem);

	public Perfil obterHabilidadeDoPersonagemParaPerfil(Perfil perfil, Habilidade habilidade);

	public Perfil inserirPtsExtrasParaCooperacaoNoPerfil(Perfil perfil, int novasCooperacoesFeitas);

	public Perfil inserirPtsExtrasParaPedidoAjudaNoPerfil(Perfil perfil, int pedidosDeAjuda);

	public Perfil inserirPtsExtrasParaAtividadeNoPerfil(Perfil perfil, int novasAtividadesFeitas);

	public Perfil obterRecompensaPorNovoNivel(Perfil perfil, Caixa caixa);

}

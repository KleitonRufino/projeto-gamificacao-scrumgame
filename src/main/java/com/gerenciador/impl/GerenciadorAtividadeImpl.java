package com.gerenciador.impl;

import org.springframework.stereotype.Service;

import com.enums.Pontuacao;
import com.gerenciador.GerenciadorAtividade;
import com.model.Perfil;

@Service
public class GerenciadorAtividadeImpl implements GerenciadorAtividade {

	@Override
	public Perfil solicitarNovaAtividade(Perfil perfil, int novasAtividadesFeitas) {
		perfil.setCountAtividades(perfil.getCountAtividades() + novasAtividadesFeitas);
		perfil.setPtsDeAtividade(perfil.getPtsDeAtividade() + (novasAtividadesFeitas * Pontuacao.QUINZE.getPts()));
		return perfil;
	}

}

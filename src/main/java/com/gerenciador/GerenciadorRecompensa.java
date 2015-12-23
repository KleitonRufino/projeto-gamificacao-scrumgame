package com.gerenciador;

import java.util.List;

import com.model.Caixa;
import com.model.Nivel;

public interface GerenciadorRecompensa {

	public void salvar(Caixa caixa);

	public void atualizar(Caixa caixa);

	public List<Caixa> findAll();

	public Caixa verificarRecompensaParaNovoNivel(Nivel nivel);

}

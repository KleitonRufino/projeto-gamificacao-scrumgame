package com.gerenciador;

import com.model.Nivel;
import com.model.Perfil;

public interface GerenciadorNivel {
	public void salvar(Nivel nivel);

	public void atualizar(Nivel nivel);

	public Nivel findByNivel(int nivel);

	public Nivel findByXp(int xp);

	public Nivel verificarNivel(Perfil perfil);
	
	public boolean verificarNovoNivel(Nivel antigo, Nivel novo);
}

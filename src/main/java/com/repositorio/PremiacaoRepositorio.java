package com.repositorio;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.model.Premiacao;

@Repository
public class PremiacaoRepositorio {

	@PersistenceContext
	private EntityManager em;

	public void salvar(Premiacao premiacao) {
		em.persist(premiacao);
	}

	public void update(Premiacao premiacao) {
		em.merge(premiacao);
	}

}

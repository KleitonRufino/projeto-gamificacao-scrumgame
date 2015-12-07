package com.repositorio;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.model.Cooperacao;

@Repository
public class CooperacaoRepositorio {

	@PersistenceContext
	private EntityManager em;

	public void salvar(Cooperacao cooperacao) {
		em.persist(cooperacao);
	}

	public void update(Cooperacao cooperacao) {
		em.merge(cooperacao);
	}

}

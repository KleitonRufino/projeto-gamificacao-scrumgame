package com.repositorio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.model.Caixa;

@Repository
public class RecompensaRepositorio {

	@PersistenceContext
	private EntityManager em;

	public void salvar(Caixa caixa) {
		em.persist(caixa);
	}

	public void update(Caixa caixa) {
		em.merge(caixa);
	}

	public List<Caixa> findAll() {
		TypedQuery<Caixa> query = em.createQuery("SELECT c from Caixa c", Caixa.class);
		List<Caixa> recompensas;
		try {
			recompensas = query.getResultList();
		} catch (NoResultException exception) {
			return null;
		}
		return recompensas;
	}

}

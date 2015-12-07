package com.repositorio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.model.Rodada;

@Repository
public class RodadaRepositorio {

	@PersistenceContext
	private EntityManager em;

	public void salvar(Rodada rodada) {
		em.persist(rodada);
	}

	public void update(Rodada rodada) {
		em.merge(rodada);
	}

	public Rodada findByNumero(int numero) {
		TypedQuery<Rodada> query = em.createQuery("from Rodada r where r.numero =:numero", Rodada.class);
		query.setParameter("numero", numero);
		Rodada r;
		try {
			r = query.getSingleResult();
		} catch (NoResultException exception) {
			return null;
		}
		return r;
	}

	public Rodada findByAtiva() {
		TypedQuery<Rodada> query = em.createQuery("from Rodada r where r.ativa =true", Rodada.class);
		Rodada r;
		try {
			r = query.getSingleResult();
		} catch (NoResultException exception) {
			return null;
		}
		return r;
	}

	public List<Rodada> findAll() {
		TypedQuery<Rodada> query = em.createQuery("from Rodada r", Rodada.class);
		List<Rodada> rodadas;
		try {
			rodadas = query.getResultList();
		} catch (NoResultException exception) {
			return null;
		}
		return rodadas;
	}

}

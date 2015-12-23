package com.repositorio;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.model.Nivel;

@Repository
public class NivelRepositorio {
	@PersistenceContext
	private EntityManager em;

	public void salvar(Nivel nivel) {
		em.persist(nivel);
	}

	public void update(Nivel nivel) {
		em.merge(nivel);
	}

	public Nivel findByNivel(int nivel) {
		TypedQuery<Nivel> query = em.createQuery("select n from Nivel n where n.nivel =:nivel", Nivel.class);
		query.setParameter("nivel", nivel);
		Nivel n = query.getSingleResult();
		return n;
	}

	public Nivel findByXp(int xp) {
		TypedQuery<Nivel> query = em.createQuery("select n from Nivel n where :xp >= n.xpMin and :xp <= n.xpMax",
				Nivel.class);
		query.setParameter("xp", xp);
		Nivel n = query.getSingleResult();
		return n;
	}

}

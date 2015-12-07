package com.repositorio;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.enums.NomeConquista;
import com.model.Conquista;

@Repository
public class ConquistaRepositorio {

	@PersistenceContext
	private EntityManager em;

	public void salvar(Conquista conquista) {
		em.getTransaction().begin();
		em.persist(conquista);
	}

	public void update(Conquista conquista) {
		em.merge(conquista);
	}

	public Conquista findByNomeConquista(NomeConquista nomeConquista) {
		TypedQuery<Conquista> query = em.createQuery("from Conquista c where c.nomeConquista =:nomeConquista",
				Conquista.class);
		query.setParameter("nomeConquista", nomeConquista);
		Conquista c;
		try {
			c = query.getSingleResult();
		} catch (NoResultException exception) {
			return null;
		}
		return c;
	}
}

package com.repositorio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.model.Perfil;
import com.model.Personagem;

@Repository
public class PersonagemRepositorio {

	@PersistenceContext
	private EntityManager em;

	public void salvar(Personagem personagem) {
		em.persist(personagem);
	}

	public void update(Personagem personagem) {
		em.merge(personagem);
	}

	public List<Personagem> findAll() {
		TypedQuery<Personagem> query = em.createQuery("SELECT p from Personagem p order by p.nome", Personagem.class);
		List<Personagem> personagens;
		try {
			personagens = query.getResultList();
		} catch (NoResultException exception) {
			return null;
		}
		return personagens;
	}

	public Personagem findByNome(String nome) {
		TypedQuery<Personagem> query = em.createQuery("SELECT p from Personagem p where p.nome=:nome",
				Personagem.class);
		query.setParameter("nome", nome);
		Personagem personagem;
		try {
			personagem = query.getSingleResult();
		} catch (NoResultException exception) {
			return null;
		}
		return personagem;
	}

	public List<Personagem> findMyPersonagemByPerfil(Perfil perfil) {
		TypedQuery<Personagem> query = em.createQuery(
				"SELECT p from Personagem p,Item i where i.perfil.id=:id and p.id = i.personagem.id order by p.nome",
				Personagem.class);
		query.setParameter("id", perfil.getId());
		List<Personagem> personagens = new ArrayList<Personagem>();
		try {
			personagens = query.getResultList();
		} catch (NoResultException exception) {
			return new ArrayList<Personagem>();
		}
		return personagens;
	}


}

package com.repositorio;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.model.Jogador;
import com.model.Perfil;

@Repository
public class PerfilRepositorio {

	@PersistenceContext
	private EntityManager em;

	public PerfilRepositorio() {
	}

	public void salvar(Perfil perfil) {
		em.persist(perfil);
	}

	public void update(Perfil perfil) {
		em.merge(perfil);
	}

	public Perfil findByIdDoJogador(Jogador jogador) {
		TypedQuery<Perfil> query = em.createQuery(
				"select p from Perfil p, Jogador j where j.id =:idJogador and p.jogador.id = j.id", Perfil.class);
		query.setParameter("idJogador", jogador.getId());
		Perfil p;
		try {
			p = query.getSingleResult();
		} catch (NoResultException exception) {
			return null;
		}
		return p;
	}

	public Perfil findByNicknameDoJogador(Jogador jogador) {
		TypedQuery<Perfil> query = em.createQuery(
				"select p from Perfil p, Jogador j where j.nickname =:nickname and p.jogador.id = j.id", Perfil.class);
		query.setParameter("nickname", jogador.getNickname());
		Perfil p;
		try {
			p = query.getSingleResult();
		} catch (NoResultException exception) {
			return null;
		}
		return p;
	}
}

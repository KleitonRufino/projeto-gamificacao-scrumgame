package com.repositorio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.model.Jogador;
import com.model.Score;

@Repository
public class ScoreRepositorio {

	@PersistenceContext
	private EntityManager em;

	public void salvar(Score score) {
		em.persist(score);
	}

	public void update(Score score) {
		em.merge(score);
	}

	public Score findByRodadaAtivaAndIdJogador(Jogador jogador) {
		TypedQuery<Score> query = em.createQuery(
				"select s from Score s, Rodada r where r.ativa = true and s.jogador.id =:idJogador and s.rodada.id = r.id ",
				Score.class);
		query.setParameter("idJogador", jogador.getId());
		Score p;
		try {
			p = query.getSingleResult();
		} catch (NoResultException exception) {
			return null;
		}
		return p;
	}

	public List<Score> findByIdJogador(Jogador jogador) {
		TypedQuery<Score> query = em.createQuery("select s from Score s where s.jogador.id =:idJogador", Score.class);
		query.setParameter("idJogador", jogador.getId());
		List<Score> scores;
		try {
			scores = query.getResultList();
		} catch (NoResultException exception) {
			return null;
		}
		return scores;
	}
}

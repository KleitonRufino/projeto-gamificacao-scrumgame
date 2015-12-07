package com.repositorio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.model.Jogador;

@Repository
public class JogadorRepositorio {

	@PersistenceContext
	private EntityManager em;

	public void salvar(Jogador jogador) {
		em.persist(jogador);
	}

	public void update(Jogador jogador) {
		em.merge(jogador);
	}

	public Jogador findByNicknameAndSenha(String nickname, String senha) {
		TypedQuery<Jogador> query = em.createQuery(
				"SELECT DISTINCT j FROM Jogador j LEFT JOIN FETCH j.cooperacoes WHERE j.nickname = :nickname and j.senha = :senha",
				Jogador.class);
		query.setParameter("nickname", nickname);
		query.setParameter("senha", senha);
		Jogador j;
		try {
			j = query.getSingleResult();
		} catch (NoResultException exception) {
			return null;
		}
		return j;
	}

	public Jogador findByNickname(String nickname) {
		TypedQuery<Jogador> query = em.createQuery("select j from Jogador j where j.nickname =:nickname",
				Jogador.class);
		query.setParameter("nickname", nickname);
		Jogador j;
		try {
			j = query.getSingleResult();
		} catch (NoResultException exception) {
			return null;
		}
		return j;
	}

	public Jogador findByNicknameComCooperacoes(String nickname) {
		TypedQuery<Jogador> query = em.createQuery(
				"SELECT DISTINCT j FROM Jogador j LEFT JOIN FETCH j.cooperacoes WHERE j.nickname = :nickname",
				Jogador.class);
		query.setParameter("nickname", nickname);
		Jogador j;
		try {
			j = query.getSingleResult();
		} catch (NoResultException exception) {
			return null;
		}
		return j;
	}

	public List<Jogador> findAll() {
		TypedQuery<Jogador> query = em.createQuery("SELECT * from Jogador", Jogador.class);
		List<Jogador> jogadores;
		try {
			jogadores = query.getResultList();
		} catch (NoResultException exception) {
			return null;
		}

		return jogadores;
	}

	public List<Jogador> findAllExceptMe(String nickname) {
		TypedQuery<Jogador> query = em.createQuery("SELECT j from Jogador j where j.nickname <> :nickname",
				Jogador.class);
		query.setParameter("nickname", nickname);
		List<Jogador> jogadores;
		try {
			jogadores = query.getResultList();
		} catch (NoResultException exception) {
			return null;
		}

		return jogadores;
	}

}

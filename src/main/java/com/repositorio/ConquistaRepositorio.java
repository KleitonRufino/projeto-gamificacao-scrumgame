package com.repositorio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.enums.NomeConquista;
import com.model.Conquista;
import com.model.Jogador;
import com.model.Rodada;

@Repository
public class ConquistaRepositorio {

	@PersistenceContext
	private EntityManager em;

	public void salvar(Conquista conquista) {
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

	public boolean verificarCountTrabalhoEmEquipeNaRodada(Jogador jogador, Rodada rodadaAtiva) {
		Query query = em.createNativeQuery(
				"select j.nickname, count(c.jogadorajudado_id)  from jogador j left join cooperacao c  on   c.rodada_id  = :rodada and c.jogadorajudado_id = j.id group by j.nickname");
		query.setParameter("rodada", rodadaAtiva.getId());
		@SuppressWarnings("unchecked")
		List<Object[]> resultList = query.getResultList();
		int min = Integer.MAX_VALUE;
		for (Object[] obj : resultList) {
			if (!String.valueOf(obj[0]).equals(jogador.getNickname())) {
				String s = String.valueOf(obj[1]);
				if (Integer.parseInt(s) < min) {
					min = Integer.parseInt(s);
				}
			}
		}
		if (min > 0)
			return true;
		return false;
	}
}

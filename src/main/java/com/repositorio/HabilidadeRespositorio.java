package com.repositorio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.model.Habilidade;
import com.model.Perfil;
import com.model.Personagem;

@Repository
public class HabilidadeRespositorio {

	@PersistenceContext
	private EntityManager em;

	public void salvar(Habilidade habilidade) {
		em.persist(habilidade);
	}

	public void update(Habilidade habilidade) {
		em.merge(habilidade);
	}

	public List<Habilidade> findAll() {
		TypedQuery<Habilidade> query = em.createQuery("SELECT h from Habilidade h order by h.nome", Habilidade.class);
		List<Habilidade> habilidades;
		try {
			habilidades = query.getResultList();
		} catch (NoResultException exception) {
			return null;
		}
		return habilidades;
	}

	public Habilidade findByNome(String nome) {
		TypedQuery<Habilidade> query = em.createQuery("SELECT h from Habilidade h where h.nome=:nome",
				Habilidade.class);
		query.setParameter("nome", nome);
		Habilidade habilidade;
		try {
			habilidade = query.getSingleResult();
		} catch (NoResultException exception) {
			return null;
		}
		return habilidade;
	}

	public Habilidade findByPersonagem(Personagem personagem) {
		TypedQuery<Habilidade> query = em.createQuery("SELECT h from Habilidade h where h.personagem.id=:id",
				Habilidade.class);
		query.setParameter("id", personagem.getId());
		Habilidade habilidade;
		try {
			habilidade = query.getSingleResult();
		} catch (NoResultException exception) {
			return null;
		}

		return habilidade;
	}

	public List<Habilidade> findMyHabilidadeByPerfil(Perfil perfil) {
		TypedQuery<Habilidade> query = em.createQuery(
				"SELECT h from Habilidade h,Item i where i.perfil.id=:id and h.id = i.habilidade.id order by h.nome",
				Habilidade.class);
		query.setParameter("id", perfil.getId());
		List<Habilidade> habilidades = new ArrayList<Habilidade>();
		try {
			habilidades = query.getResultList();
		} catch (NoResultException exception) {
			return new ArrayList<Habilidade>();
		}
		return habilidades;
	}
}

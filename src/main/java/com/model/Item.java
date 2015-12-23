package com.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Item {

	@Id
	@GeneratedValue
	private Long id;
	@OneToOne(optional = false)
	private Personagem personagem;
	@OneToOne(optional = true)
	private Habilidade habilidade;
	@OneToOne(optional = false)
	private Perfil perfil;

	public Item() {
	}

	public Item(Personagem personagem, Perfil perfil) {
		super();
		this.personagem = personagem;
		this.perfil = perfil;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Personagem getPersonagem() {
		return personagem;
	}

	public void setPersonagem(Personagem personagem) {
		this.personagem = personagem;
	}

	public Habilidade getHabilidade() {
		return habilidade;
	}

	public void setHabilidade(Habilidade habilidade) {
		this.habilidade = habilidade;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	@Override
	public String toString() {
		return "Item [personagem=" + personagem + ", habilidade=" + habilidade + ", perfil=" + perfil + "]";
	}

}

package com.formandview;

import java.util.ArrayList;
import java.util.List;

import com.enums.NomeConquista;
import com.enums.UrlImg;
import com.model.Conquista;
import com.model.Premiacao;

public class VisualizaConquista {

	private String amigoEmApurosI;
	private String amigoEmApurosII;
	private String amigoEmApurosIII;
	private String amigoEmApurosIV;
	private String conquistadorI;
	private String conquistadorII;
	private String conquistadorIII;
	private String conquistadorIV;
	private List<String> heroiDaSprint;
	private List<String> trabalhandoComAEquipe;

	public void preencheConquistas(List<Premiacao> premiacoes) {
		amigoEmApurosI = UrlImg.AMGAPI_NoComplete.getUrl();
		amigoEmApurosII = UrlImg.AMGAPII_NoComplete.getUrl();
		amigoEmApurosIII = UrlImg.AMGAPIII_NoComplete.getUrl();
		amigoEmApurosIV = UrlImg.AMGAPIV_NoComplete.getUrl();
		conquistadorI = UrlImg.CONQI_NoComplete.getUrl();
		conquistadorII = UrlImg.CONQII_NoComplete.getUrl();
		conquistadorIII = UrlImg.CONQIII_NoComplete.getUrl();
		conquistadorIV = UrlImg.CONQIV_NoComplete.getUrl();
		trabalhandoComAEquipe = new ArrayList<String>();
		heroiDaSprint = new ArrayList<String>();

		for (Premiacao premiacao : premiacoes) {
			Conquista conquista = premiacao.getConquista();
			if (NomeConquista.AMGAPI.equals(conquista.getNomeConquista()))
				amigoEmApurosI = UrlImg.AMGAPI_Complete.getUrl();
			else if (NomeConquista.AMGAPII.equals(conquista.getNomeConquista()))
				amigoEmApurosII = UrlImg.AMGAPII_Complete.getUrl();
			else if (NomeConquista.AMGAPIII.equals(conquista.getNomeConquista()))
				amigoEmApurosIII = UrlImg.AMGAPIII_Complete.getUrl();
			else if (NomeConquista.AMGAPIV.equals(conquista.getNomeConquista()))
				amigoEmApurosIV = UrlImg.AMGAPIV_Complete.getUrl();
			else if (NomeConquista.CONQI.equals(conquista.getNomeConquista()))
				conquistadorI = UrlImg.CONQI_Complete.getUrl();
			else if (NomeConquista.CONQII.equals(conquista.getNomeConquista()))
				conquistadorII = UrlImg.CONQII_Complete.getUrl();
			else if (NomeConquista.CONQIII.equals(conquista.getNomeConquista()))
				conquistadorIII = UrlImg.CONQIII_Complete.getUrl();
			else if (NomeConquista.CONQIV.equals(conquista.getNomeConquista()))
				conquistadorIV = UrlImg.CONQIV_Complete.getUrl();
			else if (NomeConquista.TRAEQ.equals(conquista.getNomeConquista())) {
				trabalhandoComAEquipe.add(UrlImg.TRAEQ_Complete.getUrl());
			} else if (NomeConquista.HERSPRINT.equals(conquista.getNomeConquista())) {
				heroiDaSprint.add(UrlImg.HERSPRINT_Complete.getUrl());
			}
		}

		if (heroiDaSprint.size() == 0)
			heroiDaSprint.add(UrlImg.HERSPRINT_NoComplete.getUrl());

		if (trabalhandoComAEquipe.size() == 0)
			trabalhandoComAEquipe.add(UrlImg.TRAEQ_NoComplete.getUrl());
	}

	public String getAmigoEmApurosI() {
		return amigoEmApurosI;
	}

	public void setAmigoEmApurosI(String amigoEmApurosI) {
		this.amigoEmApurosI = amigoEmApurosI;
	}

	public String getAmigoEmApurosII() {
		return amigoEmApurosII;
	}

	public void setAmigoEmApurosII(String amigoEmApurosII) {
		this.amigoEmApurosII = amigoEmApurosII;
	}

	public String getAmigoEmApurosIII() {
		return amigoEmApurosIII;
	}

	public void setAmigoEmApurosIII(String amigoEmApurosIII) {
		this.amigoEmApurosIII = amigoEmApurosIII;
	}

	public String getConquistadorI() {
		return conquistadorI;
	}

	public void setConquistadorI(String conquistadorI) {
		this.conquistadorI = conquistadorI;
	}

	public String getConquistadorII() {
		return conquistadorII;
	}

	public void setConquistadorII(String conquistadorII) {
		this.conquistadorII = conquistadorII;
	}

	public String getConquistadorIII() {
		return conquistadorIII;
	}

	public void setConquistadorIII(String conquistadorIII) {
		this.conquistadorIII = conquistadorIII;
	}

	public List<String> getHeroiDaSprint() {
		return heroiDaSprint;
	}

	public void setHeroiDaSprint(List<String> heroiDaSprint) {
		this.heroiDaSprint = heroiDaSprint;
	}

	public List<String> getTrabalhandoComAEquipe() {
		return trabalhandoComAEquipe;
	}

	public void setTrabalhandoComAEquipe(List<String> trabalhandoComAEquipe) {
		this.trabalhandoComAEquipe = trabalhandoComAEquipe;
	}

	public String getAmigoEmApurosIV() {
		return amigoEmApurosIV;
	}

	public void setAmigoEmApurosIV(String amigoEmApurosIV) {
		this.amigoEmApurosIV = amigoEmApurosIV;
	}

	public String getConquistadorIV() {
		return conquistadorIV;
	}

	public void setConquistadorIV(String conquistadorIV) {
		this.conquistadorIV = conquistadorIV;
	}

}

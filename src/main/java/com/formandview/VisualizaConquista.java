package com.formandview;

import java.util.List;

import com.enums.NomeConquista;
import com.enums.UrlImg;
import com.model.Conquista;
import com.model.Premiacao;

public class VisualizaConquista {

	private String amigoEmApurosI;
	private String amigoEmApurosII;
	private String amigoEmApurosIII;
	private String conquistadorI;
	private String conquistadorII;
	private String conquistadorIII;
	private String heroiDaSprint;
	private String trabalhandoComAEquipe;

	public void preencheConquistas(List<Premiacao> premiacoes) {
		amigoEmApurosI = UrlImg.AMGAPI_NoComplete.getUrl();
		amigoEmApurosII = UrlImg.AMGAPII_NoComplete.getUrl();
		amigoEmApurosIII = UrlImg.AMGAPIII_NoComplete.getUrl();
		conquistadorI = UrlImg.CONQI_NoComplete.getUrl();
		conquistadorII = UrlImg.CONQII_NoComplete.getUrl();
		conquistadorIII = UrlImg.CONQIII_NoComplete.getUrl();
		heroiDaSprint = UrlImg.HERSPRINT_NoComplete.getUrl();
		trabalhandoComAEquipe = UrlImg.TRAEQ_NoComplete.getUrl();
		for (Premiacao premiacao : premiacoes) {
			Conquista conquista = premiacao.getConquista();
			if (NomeConquista.AMGAPI.equals(conquista.getNomeConquista()))
				amigoEmApurosI = UrlImg.AMGAPI_Complete.getUrl();
			else if (NomeConquista.AMGAPII.equals(conquista.getNomeConquista()))
				amigoEmApurosII = UrlImg.AMGAPII_Complete.getUrl();
			else if (NomeConquista.AMGAPIII.equals(conquista.getNomeConquista()))
				amigoEmApurosIII = UrlImg.AMGAPIII_Complete.getUrl();
			else if (NomeConquista.CONQI.equals(conquista.getNomeConquista()))
				conquistadorI = UrlImg.CONQI_Complete.getUrl();
			else if (NomeConquista.CONQII.equals(conquista.getNomeConquista()))
				conquistadorII = UrlImg.CONQII_Complete.getUrl();
			else if (NomeConquista.CONQIII.equals(conquista.getNomeConquista()))
				conquistadorIII = UrlImg.CONQIII_Complete.getUrl();
			else if (NomeConquista.TRAEQ.equals(conquista.getNomeConquista()))
				trabalhandoComAEquipe = UrlImg.TRAEQ_Complete.getUrl();
			else if (NomeConquista.HERSPRINT.equals(conquista.getNomeConquista()))
				heroiDaSprint = UrlImg.HERSPRINT_Complete.getUrl();
		}
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

	public String getHeroiDaSprint() {
		return heroiDaSprint;
	}

	public void setHeroiDaSprint(String heroiDaSprint) {
		this.heroiDaSprint = heroiDaSprint;
	}

	public String getTrabalhandoComAEquipe() {
		return trabalhandoComAEquipe;
	}

	public void setTrabalhandoComAEquipe(String trabalhandoComAEquipe) {
		this.trabalhandoComAEquipe = trabalhandoComAEquipe;
	}

}

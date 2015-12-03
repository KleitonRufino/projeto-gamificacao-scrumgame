package com.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.springframework.stereotype.Component;

@Component
@ManagedBean
@RequestScoped
public class JogadorController {
	
	public String historicoConquistas(){
		return "/historicoConquistas?faces-redirect=true";
	}
}

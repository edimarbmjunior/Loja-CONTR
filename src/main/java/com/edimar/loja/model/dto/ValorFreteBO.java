package com.edimar.loja.model.dto;

import java.io.Serializable;

public class ValorFreteBO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Double valorFrete;
	public ValorFreteBO() {
		// TODO Auto-generated constructor stub
	}
	public ValorFreteBO(Double valorFrete) {
		super();
		this.valorFrete = valorFrete;
	}
	public Double getValorFrete() {
		return valorFrete;
	}
	public void setValorFrete(Double valorFrete) {
		this.valorFrete = valorFrete;
	}
}

package com.edimar.loja.model.dto;

import java.io.Serializable;

public class ItemPedidoBO implements Serializable{

	private static final long serialVersionUID = 1L;
	private ProdutoBO produto;
	private Integer qtde;
	private Double valorTotalItensPedido;
	
	public ItemPedidoBO() {
		// TODO Auto-generated constructor stub
	}

	public ItemPedidoBO(ProdutoBO produto, Integer qtde) {
		super();
		this.produto = produto;
		this.qtde = qtde;
	}

	public ProdutoBO getProdutoBO() {
		return produto;
	}

	public void setProdutoBO(ProdutoBO produto) {
		this.produto = produto;
	}

	public Integer getQtde() {
		return qtde;
	}

	public void setQtde(Integer qtde) {
		this.qtde = qtde;
	}

	public Double getValorTotalItensPedido() {
		return this.valorTotalItensPedido = produto.getPreco()==null?0d:produto.getPreco() * qtde;
	}

	public void setValorTotalItensPedido(Double valorTotalItensPedido) {
		this.valorTotalItensPedido = produto.getPreco()==null?0d:produto.getPreco() * qtde;
	}
}

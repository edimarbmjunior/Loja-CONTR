package com.edimar.loja.model.dto.response;

import java.io.Serializable;

import com.edimar.loja.services.Util.RecursosUtil;

public class ItemPedidoDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private ProdutoDTO produto;
	private Integer qtde;
	private Double valorTotalItensPedido;
	
	public ItemPedidoDTO() {
		// TODO Auto-generated constructor stub
	}

	public ItemPedidoDTO(ProdutoDTO produto, Integer qtde) {
		super();
		this.produto = produto;
		this.qtde = qtde;
	}

	public ProdutoDTO getProdutoDTO() {
		return produto;
	}

	public void setProdutoDTO(ProdutoDTO produto) {
		this.produto = produto;
	}

	public Integer getQtde() {
		return qtde;
	}

	public void setQtde(Integer qtde) {
		this.qtde = qtde;
	}

	public Double getValorTotalItensPedido() {
		return RecursosUtil.casasDecimais(valorTotalItensPedido);
	}

	public void setValorTotalItensPedido(Double valorTotalItensPedido) {
		this.valorTotalItensPedido = produto.getPreco() * qtde;
	}
}

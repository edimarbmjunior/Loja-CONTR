package com.edimar.loja.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class ItemPedido implements Serializable{

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ItemPedidoPK id = new ItemPedidoPK();

	private Integer qtde;
	private Double valorTotalItensPedido;
	
	public ItemPedido() {
		// TODO Auto-generated constructor stub
	}

	public ItemPedido(Pedido pedido, Produto produto, Integer qtde) {
		super();
		this.id.setPedido(pedido);
		this.id.setProduto(produto);
		this.qtde = qtde;
	}
	
	public Pedido getPedido() {
		return id.getPedido();
	}

	public Produto getProduto() {
		return id.getProduto();
	}

	public ItemPedidoPK getId() {
		return id;
	}

	public void setId(ItemPedidoPK id) {
		this.id = id;
	}

	public Integer getQtde() {
		return qtde;
	}

	public void setQtde(Integer qtde) {
		this.qtde = qtde;
	}

	public Double getValorTotalItensPedido() {
		System.out.println();
		System.out.println(" getPedido -> " + getPedido().toString());
		System.out.println();
		return getPedido() == null ? null : getPedido().getValorTotalPedido() * qtde;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemPedido other = (ItemPedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ItemPedido [id=" + id + ", qtde=" + qtde + ", valorTotalItensPedido=" + valorTotalItensPedido + "]";
	}
}

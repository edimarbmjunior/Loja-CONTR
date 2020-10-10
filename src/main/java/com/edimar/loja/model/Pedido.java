package com.edimar.loja.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Pedido implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Date dataPedido;
	private Long numPedido;
	
	private Double valorTotalPedido;
	private Double valorFrete;
	
	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name = "clienteId")
	private Cliente cliente;

	@OneToMany(mappedBy = "id.pedido")
	private Set<ItemPedido> itemPedidos = new HashSet<>();
	
	public Pedido() {
		// TODO Auto-generated constructor stub
	}

	public Pedido(Integer id, Date dataPedido, Long numPedido, Cliente cliente, Double valorFrete) {
		super();
		this.id = id;
		this.dataPedido = dataPedido;
		this.numPedido = numPedido;
		this.cliente = cliente;
		this.valorFrete = valorFrete;
	}
	
	public List<Produto> getProduto(){
		List<Produto> listProdutos = new ArrayList<>();
		for(ItemPedido item : itemPedidos) {
			listProdutos.add(item.getProduto());
		}
		return listProdutos;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}

	public Long getNumPedido() {
		return numPedido;
	}

	public void setNumPedido(Long numPedido) {
		this.numPedido = numPedido;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Double getValorTotalPedido() {
		System.out.println();
		System.out.println(" itemPedidos -> " + itemPedidos.isEmpty());
		System.out.println();
		this.valorTotalPedido = 0d;
		if(!itemPedidos.isEmpty()) {
			for(ItemPedido item : itemPedidos) {
				this.valorTotalPedido = this.valorTotalPedido + item.getProduto().getPreco();
			}
		}
		return valorTotalPedido;
	}

	public void setValorTotalPedido(Double valorTotalPedido) {
		this.valorTotalPedido = valorTotalPedido;
	}

	public Double getValorFrete() {
		return valorFrete;
	}

	public void setValorFrete(Double valorFrete) {
		this.valorFrete = valorFrete;
	}

	public Set<ItemPedido> getItemPedidos() {
		return itemPedidos;
	}

	public void setItemPedidos(Set<ItemPedido> itemPedidos) {
		this.itemPedidos = itemPedidos;
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
		Pedido other = (Pedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Pedido [id=" + id + ", dataPedido=" + dataPedido + ", numPedido=" + numPedido + ", valorTotalPedido="
				+ valorTotalPedido + ", valorFrete=" + valorFrete + ", cliente=" + cliente + ", itemPedidos="
				+ itemPedidos + "]";
	}
}

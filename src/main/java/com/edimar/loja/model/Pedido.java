package com.edimar.loja.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.edimar.loja.model.convert.ClienteConvert;
import com.edimar.loja.model.convert.ItemPedidoConvert;
import com.edimar.loja.model.dto.PedidoBO;
import com.edimar.loja.services.Util.RecursosUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Pedido implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@JsonFormat(pattern = "dd/MM/YYY")
	private Date dataPedido;
	
	private Long numPedido;

	@ManyToOne
	@JoinColumn(name = "clienteId")
	private Cliente cliente;

	@OneToMany(mappedBy = "id.pedido")
	private Set<ItemPedido> itensPedidos = new HashSet<>();
	
	public Pedido() {
		// TODO Auto-generated constructor stub
	}

	public Pedido(Integer id, Date dataPedido, Long numPedido, Cliente cliente) {
		super();
		this.id = id;
		this.dataPedido = dataPedido;
		this.numPedido = numPedido;
		this.cliente = cliente;
	}
	
	public Pedido(PedidoBO pedidoBO) {
		super();
		this.id = pedidoBO.getId();
		this.dataPedido = RecursosUtil.converterStringToDate(pedidoBO.getDataPedido());
		this.numPedido = pedidoBO.getNumPedido();
		this.cliente = ClienteConvert.converterToClienteFromClienteBO(pedidoBO.getClienteBO());
		if(pedidoBO.getItemPedidos() != null && (!pedidoBO.getItemPedidos().isEmpty() && pedidoBO.getItemPedidos().size() > 0)) {
			this.itensPedidos = pedidoBO.getItemPedidos().stream().map(itemPedido -> ItemPedidoConvert.converterToItemPedidoFromItemPedidoBO(itemPedido, pedidoBO.getId())).collect(Collectors.toSet());
		}
	}
	
	@JsonIgnore
	public List<Produto> getProduto(){
		List<Produto> listProdutos = new ArrayList<>();
		for(ItemPedido item : itensPedidos) {
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

	public Set<ItemPedido> getItemPedidos() {
		return itensPedidos;
	}

	public void setItemPedidos(Set<ItemPedido> itensPedidos) {
		this.itensPedidos = itensPedidos;
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
		return "Pedido [id=" + id + ", dataPedido=" + dataPedido + ", numPedido=" + numPedido + ", cliente=" + cliente
				+ ", itensPedidos=" + itensPedidos + "]";
	}
}

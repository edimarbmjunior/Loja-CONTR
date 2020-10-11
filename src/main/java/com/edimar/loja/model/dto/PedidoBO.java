package com.edimar.loja.model.dto;

import java.io.Serializable;
import java.util.Set;

import com.edimar.loja.model.Pedido;
import com.edimar.loja.model.convert.ClienteConvert;
import com.edimar.loja.services.Util.DataUtil;

public class PedidoBO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String dataPedido;
	private Long numPedido;
	private Double valorTotalProdutos;
	private Double valorFrete;
	private ClienteBO clienteBO;
	private Set<ItemPedidoBO> itemPedidos;
	
	public PedidoBO() {
		// TODO Auto-generated constructor stub
	}

	public PedidoBO(Integer id, String dataPedido, Long numPedido, Double valorTotalProdutos, Double valorFrete,
			ClienteBO clienteDTO, Set<ItemPedidoBO> itemPedidos) {
		super();
		this.id = id;
		this.dataPedido = dataPedido;
		this.numPedido = numPedido;
		this.valorTotalProdutos = valorTotalProdutos;
		this.valorFrete = valorFrete;
		this.clienteBO = clienteDTO;
		this.itemPedidos = itemPedidos;
	}
	
	public PedidoBO(Pedido pedido) {
		super();
		this.id = pedido.getId();
		this.dataPedido = DataUtil.converterDateToString(pedido.getDataPedido());
		this.numPedido = pedido.getNumPedido();
		this.clienteBO = ClienteConvert.converterToClienteBoFromCliente(pedido.getCliente());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(String dataPedido) {
		this.dataPedido = dataPedido;
	}

	public Long getNumPedido() {
		return numPedido;
	}

	public void setNumPedido(Long numPedido) {
		this.numPedido = numPedido;
	}

	public Double getValorTotalProdutos() {
		this.valorTotalProdutos = null;
		if(itemPedidos!=null && (!itemPedidos.isEmpty() && itemPedidos.size() > 0)) {
			this.valorTotalProdutos = 0d;
			itemPedidos.stream().forEach((itemPedido) -> {
				this.valorTotalProdutos = itemPedido.getProdutoBO().getPreco()==null? this.valorTotalProdutos : this.valorTotalProdutos + (itemPedido.getProdutoBO().getPreco() * itemPedido.getQtde());
			});
		}
		return valorTotalProdutos;
	}

	public void setValorTotalProdutos(Double valorTotalPedido) {
		this.valorTotalProdutos = getValorTotalProdutos();
	}

	public Double getValorFrete() {
		return valorFrete;
	}

	public void setValorFrete(Double valorFrete) {
		this.valorFrete = valorFrete;
	}

	public ClienteBO getClienteBO() {
		return clienteBO;
	}

	public void setClienteBO(ClienteBO cliente) {
		this.clienteBO = cliente;
	}

	public Set<ItemPedidoBO> getItemPedidos() {
		return itemPedidos;
	}

	public void setItemPedidos(Set<ItemPedidoBO> itemPedidos) {
		this.itemPedidos = itemPedidos;
	}
}

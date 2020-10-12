package com.edimar.loja.model.dto.response;

import java.io.Serializable;
import java.util.Set;

import com.edimar.loja.services.Util.RecursosUtil;

public class PedidoDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String dataPedido;
	private Long numPedido;
	private Double valorTotalProdutos;
	private Double valorFrete;
	private ClienteDTO clienteDTO;
	private Set<ItemPedidoDTO> itemPedidos;
	
	public PedidoDTO() {
		// TODO Auto-generated constructor stub
	}

	public PedidoDTO(Integer id, String dataPedido, Long numPedido, Double valorTotalProdutos, Double valorFrete,
			ClienteDTO clienteDTO, Set<ItemPedidoDTO> itemPedidos) {
		super();
		this.id = id;
		this.dataPedido = dataPedido;
		this.numPedido = numPedido;
		this.valorTotalProdutos = valorTotalProdutos;
		this.valorFrete = valorFrete;
		this.clienteDTO = clienteDTO;
		this.itemPedidos = itemPedidos;
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
		this.valorTotalProdutos = 0d;
		if(itemPedidos!=null && (!itemPedidos.isEmpty() && itemPedidos.size() > 0)) {
			itemPedidos.stream().forEach((itemPedido) -> {
				this.valorTotalProdutos = this.valorTotalProdutos + (itemPedido.getProdutoDTO().getPreco() * itemPedido.getQtde());
			});
		}
		return RecursosUtil.casasDecimais(valorTotalProdutos);
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

	public ClienteDTO getClienteDTO() {
		return clienteDTO;
	}

	public void setClienteDTO(ClienteDTO cliente) {
		this.clienteDTO = cliente;
	}

	public Set<ItemPedidoDTO> getItemPedidos() {
		return itemPedidos;
	}

	public void setItemPedidos(Set<ItemPedidoDTO> itemPedidos) {
		this.itemPedidos = itemPedidos;
	}
}

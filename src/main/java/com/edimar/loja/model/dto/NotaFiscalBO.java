package com.edimar.loja.model.dto;

import java.io.Serializable;
import java.util.Set;

public class NotaFiscalBO implements Serializable{

	private static final long serialVersionUID = 1L;
	private String nomeCliente;
	private String cpfCliente;
	private Integer cepCliente;
	private Long numPedido;
	private Set<ItemPedidoBO> itensPedidos;
	private String ufCliente;
	private String nomeEstadoCliente;
	private Double fretePedido;
	
	public NotaFiscalBO() {
		// TODO Auto-generated constructor stub
	}

	public NotaFiscalBO(String nomeCliente, String cpfCliente, Integer cepCliente, Long numPedido,
			Set<ItemPedidoBO> itensPedidos, String ufCliente, String nomeEstadoCliente, Double fretePedido) {
		super();
		this.nomeCliente = nomeCliente;
		this.cpfCliente = cpfCliente;
		this.cepCliente = cepCliente;
		this.numPedido = numPedido;
		this.itensPedidos = itensPedidos;
		this.ufCliente = ufCliente;
		this.nomeEstadoCliente = nomeEstadoCliente;
		this.fretePedido = fretePedido;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getCpfCliente() {
		return cpfCliente;
	}

	public void setCpfCliente(String cpfCliente) {
		this.cpfCliente = cpfCliente;
	}

	public Integer getCepCliente() {
		return cepCliente;
	}

	public void setCepCliente(Integer cepCliente) {
		this.cepCliente = cepCliente;
	}

	public Long getNumPedido() {
		return numPedido;
	}

	public void setNumPedido(Long numPedido) {
		this.numPedido = numPedido;
	}

	public Set<ItemPedidoBO> getItensPedidos() {
		return itensPedidos;
	}

	public void setItensPedidos(Set<ItemPedidoBO> itensPedidos) {
		this.itensPedidos = itensPedidos;
	}

	public String getUfCliente() {
		return ufCliente;
	}

	public void setUfCliente(String ufCliente) {
		this.ufCliente = ufCliente;
	}

	public String getNomeEstadoCliente() {
		return nomeEstadoCliente;
	}

	public void setNomeEstadoCliente(String nomeEstadoCliente) {
		this.nomeEstadoCliente = nomeEstadoCliente;
	}

	public Double getFretePedido() {
		return fretePedido;
	}

	public void setFretePedido(Double fretePedido) {
		this.fretePedido = fretePedido;
	}
}

package com.edimar.loja.model.convert;

import java.util.stream.Collectors;

import com.edimar.loja.model.Cliente;
import com.edimar.loja.model.dto.ClienteBO;

public class ClienteConvert {
	
	public static ClienteBO converterToClienteBoFromCliente(Cliente cliente) {
		ClienteBO clienteBO = new ClienteBO();
		clienteBO.setId(cliente.getId());
		clienteBO.setBairro(cliente.getBairro());
		clienteBO.setCep(cliente.getCep());
		clienteBO.setCidade(cliente.getCidade());
		clienteBO.setCpf(cliente.getCpf());
		clienteBO.setEndereco(cliente.getEndereco());
		clienteBO.setId(cliente.getId());
		clienteBO.setNome(cliente.getNome());
		clienteBO.setTelefeone(cliente.getTelefeone());
		clienteBO.setUf(cliente.getUf());
		clienteBO.setListPedidos(cliente.getListPedidos().stream().map(pedido -> PedidoConvert.converterToPedidoBoFromPedido(pedido)).collect(Collectors.toList()));
		return clienteBO;
	}
	
	public static ClienteBO converterToClienteBoFromClienteTipo2(Cliente cliente) {
		ClienteBO clienteBO = new ClienteBO();
		clienteBO.setBairro(cliente.getBairro());
		clienteBO.setCep(cliente.getCep());
		clienteBO.setCidade(cliente.getCidade());
		clienteBO.setCpf(cliente.getCpf());
		clienteBO.setEndereco(cliente.getEndereco());
		clienteBO.setId(cliente.getId());
		clienteBO.setNome(cliente.getNome());
		clienteBO.setTelefeone(cliente.getTelefeone());
		clienteBO.setUf(cliente.getUf());
		return clienteBO;
	}
	
	public static Cliente converterToClienteFromClienteBO(ClienteBO clienteBO) {
		Cliente cliente = new Cliente();
		cliente.setBairro(clienteBO.getBairro());
		cliente.setCep(clienteBO.getCep());
		cliente.setCidade(clienteBO.getCidade());
		cliente.setCpf(clienteBO.getCpf());
		cliente.setEndereco(clienteBO.getEndereco());
		cliente.setId(clienteBO.getId());
		cliente.setNome(clienteBO.getNome());
		cliente.setTelefeone(clienteBO.getTelefeone());
		cliente.setUf(clienteBO.getUf());
		cliente.setListPedidos(clienteBO.getListPedidos().stream().map(pedidoDTO -> PedidoConvert.converterToPedidoFromPedidoBO(pedidoDTO)).collect(Collectors.toList()));
		return cliente;
	}
	
	public static Cliente converterToClienteFromClienteBOTipo2(ClienteBO clienteBO) {
		Cliente cliente = new Cliente();
		cliente.setBairro(clienteBO.getBairro());
		cliente.setCep(clienteBO.getCep());
		cliente.setCidade(clienteBO.getCidade());
		cliente.setCpf(clienteBO.getCpf());
		cliente.setEndereco(clienteBO.getEndereco());
		cliente.setId(clienteBO.getId());
		cliente.setNome(clienteBO.getNome());
		cliente.setTelefeone(clienteBO.getTelefeone());
		cliente.setUf(clienteBO.getUf());
		return cliente;
	}
}

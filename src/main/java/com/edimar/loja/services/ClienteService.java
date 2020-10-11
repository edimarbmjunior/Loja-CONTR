package com.edimar.loja.services;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edimar.loja.model.Cliente;
import com.edimar.loja.model.convert.ClienteConvert;
import com.edimar.loja.model.dto.ClienteBO;
import com.edimar.loja.repositories.ClienteRepository;
import com.edimar.loja.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	private static Logger logger = LoggerFactory.getLogger(ClienteService.class);
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public ClienteBO buscarClientePorId(Integer id) {
		Cliente cliente = clienteRepository
				.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado! ID: " + id +"."));
		ClienteBO retorno = ClienteConvert.converterToClienteBoFromCliente(cliente);
		return retorno;
	}
	
	public List<ClienteBO> litarClientes(){
		List<Cliente> clientes = clienteRepository.findAll();
		List<ClienteBO> retorno = clientes.stream().map(cliente -> ClienteConvert.converterToClienteBoFromCliente(cliente)).collect(Collectors.toList());
		return retorno.isEmpty() ? Arrays.asList() : retorno;
	}
	
	public void salvarClientes(List<Cliente> clientes) {
		clienteRepository.saveAll(clientes);
	}
	
	public Cliente salvarCliente(Cliente cliente) {
		cliente.setId(null);
		return clienteRepository.save(cliente);
	}
	
	public Integer incluirCliente(ClienteBO clienteDTO) {
		Cliente cliente = ClienteConvert.converterToClienteFromClienteBO(clienteDTO);
		return salvarCliente(cliente).getId();
	}
	
	public ClienteBO atualizarPedido(ClienteBO clienteDTO) {
		buscarClientePorId(clienteDTO.getId());
		Cliente cliente = ClienteConvert.converterToClienteFromClienteBO(clienteDTO);
		return ClienteConvert.converterToClienteBoFromCliente(clienteRepository.save(cliente));
	}
}

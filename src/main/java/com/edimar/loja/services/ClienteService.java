package com.edimar.loja.services;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.edimar.loja.model.Cliente;
import com.edimar.loja.model.convert.ClienteConvert;
import com.edimar.loja.model.dto.ClienteBO;
import com.edimar.loja.model.dto.ValorFreteBO;
import com.edimar.loja.repositories.ClienteRepository;
import com.edimar.loja.service.validator.ValidacaoCliente;
import com.edimar.loja.services.Util.CalculoFrete;
import com.edimar.loja.services.exceptions.GenericExcpetion;
import com.edimar.loja.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	private static Logger logger = LoggerFactory.getLogger(ClienteService.class);
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public ClienteBO buscarClientePorId(Integer id) {
		ValidacaoCliente.validacaoConsultar(id);
		Cliente cliente = clienteRepository
				.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado! ID: " + id +"."));
		ClienteBO retorno = ClienteConvert.converterToClienteBoFromCliente(cliente);
		return retorno;
	}
	
	public ClienteBO buscarClientePorCpf(String cpf, Integer tipoPesquisa) {
		ValidacaoCliente.validacaoConsultarCpf(cpf);
		Cliente cliente = clienteRepository
				.buscarPorCpf(cpf);
		if("2".equals(tipoPesquisa.toString().trim())) {
			if(cliente == null) {
				return new ClienteBO();
			}else {
				ClienteBO retorno = ClienteConvert.converterToClienteBoFromCliente(cliente);
				return retorno;
			}
		}else {
			if(cliente == null) {
				throw new ObjectNotFoundException("Cliente não encontrado! CPF: " + cpf +".");
			}else {
				ClienteBO retorno = ClienteConvert.converterToClienteBoFromCliente(cliente);
				return retorno;
			}
		}
	}
	
	public ValorFreteBO buscarValorFrete(Integer cep) {
		CalculoFrete frete = CalculoFrete.verificarCepDados(cep);
		if(frete==null) {
			throw new ObjectNotFoundException("CEP é inválido para o calculo!");
		}
		ValorFreteBO valorFrete = new ValorFreteBO(frete.getValorFrete());
		return valorFrete;
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
	
	public Integer incluirCliente(ClienteBO clienteBO) {
		ValidacaoCliente.validacaoAtualizar(clienteBO);
		ClienteBO clientePesquisa = buscarClientePorCpf(clienteBO.getCpf(), 2);
		if(clientePesquisa.getCpf() != null) {
			throw new GenericExcpetion("CPF é inválido!");
		}
		Cliente cliente = ClienteConvert.converterToClienteFromClienteBO(clienteBO);
		return salvarCliente(cliente).getId();
	}
	
	public Integer atualizarCliente(ClienteBO clienteBO) {
		ValidacaoCliente.validacaoAtualizar(clienteBO);
		
		buscarClientePorId(clienteBO.getId());
		Cliente cliente = ClienteConvert.converterToClienteFromClienteBO(clienteBO);
		return ClienteConvert.converterToClienteBoFromCliente(clienteRepository.save(cliente)).getId();
	}
	
	public void deletarCliente(Integer identificador) {
		ValidacaoCliente.validacaoConsultar(identificador);
		ClienteBO cliente = buscarClientePorId(identificador);
		try {
			clienteRepository.deleteById(cliente.getId());
		} catch (DataIntegrityViolationException e) {
			throw new com.edimar.loja.services.exceptions.DataIntegrityViolationException("Não é possíve excluir cliente que tenha pedidos.");
		} catch (Exception e) {
			throw new GenericExcpetion("Erro ao ao tentar excluir -> " + identificador + " - Motivo: " + e.getMessage());
		}
	}
}

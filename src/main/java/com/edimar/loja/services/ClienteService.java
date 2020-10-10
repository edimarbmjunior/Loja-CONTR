package com.edimar.loja.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edimar.loja.model.Cliente;
import com.edimar.loja.repositories.ClienteRepository;

@Service
public class ClienteService {
	
	private static Logger logger = LoggerFactory.getLogger(ClienteService.class);
	
	@Autowired
	private ClienteRepository clienteRepository;

	public Cliente buscarClientePorId(Integer id) throws Exception {
		Cliente c = null;
		try {
			c = clienteRepository.findById(id).get();
		}catch (NoSuchElementException noSuchElementException) {
			logger.warn("Não foi encontrado cliente");
			return c;
		} catch (Exception eXception) {
			logger.error("Error ao acessar a base de informações, " + eXception.getMessage());
			throw new Exception("Error na tentativa de busca do usuario");
		}
		return c;
	}
	
	public void salvarClientes(List<Cliente> clientes) {
		clienteRepository.saveAll(clientes);
	}
	
	public void salvarCliente(Cliente cliente) {
		clienteRepository.save(cliente);
	}
}

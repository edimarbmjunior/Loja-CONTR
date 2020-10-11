package com.edimar.loja.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.edimar.loja.model.dto.ClienteBO;
import com.edimar.loja.services.ClienteService;

@RestController
@RequestMapping(value="/cliente")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> buscarClienteporId(@PathVariable Integer id) throws Exception {
		ClienteBO c = clienteService.buscarClientePorId(id);
		return ResponseEntity.ok().body(c);
	}
	
	@RequestMapping(value="/todos", method = RequestMethod.GET)
	public ResponseEntity<List<ClienteBO>> litarClientes() {
		List<ClienteBO> pedidos = clienteService.litarClientes();
		return ResponseEntity.ok().body(pedidos);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> incluirCliente(@RequestBody ClienteBO cliente){
		Integer identificador = clienteService.incluirCliente(cliente);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(identificador)
				.toUri();
		return ResponseEntity.created(uri).build();
	}
}

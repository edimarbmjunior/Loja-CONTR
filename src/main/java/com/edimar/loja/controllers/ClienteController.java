package com.edimar.loja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.edimar.loja.model.Cliente;
import com.edimar.loja.services.ClienteService;

@RestController
@RequestMapping(value="/cliente")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> buscarClienteporId(@PathVariable Integer id) throws Exception {
		Cliente c = clienteService.buscarClientePorId(id);
		return ResponseEntity.ok().body(c);
	}
}

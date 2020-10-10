package com.edimar.loja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.edimar.loja.model.Pedido;
import com.edimar.loja.services.PedidoService;

@RestController
@RequestMapping(value="/pedido")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> buscarClienteporId(@PathVariable Integer id) throws Exception {
		Pedido c = pedidoService.buscarPedidoPorId(id);
		return ResponseEntity.ok().body(c);
	}
}

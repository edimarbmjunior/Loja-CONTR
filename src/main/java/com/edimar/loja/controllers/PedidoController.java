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

import com.edimar.loja.model.dto.PedidoBO;
import com.edimar.loja.services.PedidoService;

@RestController
@RequestMapping(value="/pedido")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> buscarPedidoPorId(@PathVariable Integer id) throws Exception {
		PedidoBO p = pedidoService.buscarPedidoPorId(id);
		return ResponseEntity.ok().body(p);
	}
	
	@RequestMapping(value="/todos", method = RequestMethod.GET)
	public ResponseEntity<List<PedidoBO>> buscarProdutos() {
		List<PedidoBO> pedidos = pedidoService.litarPedidos();
		return ResponseEntity.ok().body(pedidos);
	}
	
	@RequestMapping(value= "semProduto", method = RequestMethod.POST)
	public ResponseEntity<Void> salvarPedidoSemProduto(@RequestBody PedidoBO pedido){
		Integer identificadorPedido = pedidoService.incluirPedidoSemProduto(pedido);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(identificadorPedido)
				.toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value= "comProduto", method = RequestMethod.POST)
	public ResponseEntity<Void> salvarPedidoComProduto(@RequestBody PedidoBO pedido){
		Integer identificadorPedido = pedidoService.incluirPedidoComProduto(pedido);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(identificadorPedido)
				.toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Void> atualizarPedido(@RequestBody PedidoBO pedido){
		pedido = pedidoService.atualizarPedido(pedido);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletarPedidoPorId(@PathVariable Integer id) {
		pedidoService.deletarPedidoPorId(id);
		return ResponseEntity.noContent().build();
	}
}

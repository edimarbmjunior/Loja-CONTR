package com.edimar.loja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.edimar.loja.model.dto.ItemPedidoBO;
import com.edimar.loja.model.dto.PedidoBO;
import com.edimar.loja.services.ItemPedidoService;

@RestController
@RequestMapping(value="/itempedido")
public class ItemPedidoController {
	
	@Autowired
	private ItemPedidoService itemPedidoService;

	@RequestMapping(value="/{idNumpedido}/{idProduto}", method = RequestMethod.GET)
	public ResponseEntity<?> buscarPedidoPorId(@PathVariable Integer idNumpedido, @PathVariable Integer idProduto) throws Exception {
		ItemPedidoBO itemPedidoBO = itemPedidoService.buscarItemPedido(idNumpedido, idProduto);
		return ResponseEntity.ok().body(itemPedidoBO);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> salvarPedidoComProduto(@RequestBody PedidoBO pedido){
		itemPedidoService.incluirItemPedido(pedido);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Void> atualizarPedido(@RequestBody PedidoBO pedido){
		pedido = itemPedidoService.atualizarItemPedido(pedido);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{idNumpedido}/{idProduto}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletarPedidoPorId(@PathVariable Integer idNumpedido, @PathVariable Integer idProduto) {
		itemPedidoService.deletarItemPedidoPorId(idNumpedido, idProduto);
		return ResponseEntity.noContent().build();
	}
}

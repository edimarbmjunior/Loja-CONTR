package com.edimar.loja.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.edimar.loja.model.dto.NotaFiscalBO;
import com.edimar.loja.model.dto.PedidoBO;
import com.edimar.loja.services.PedidoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin
@RequestMapping(value="/pedido")
@Api(tags = "Pedido", description = "API de pedidos")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "Busca pedido pelo codigo")
	public ResponseEntity<?> buscarPedidoPorId(@PathVariable Integer id) {
		PedidoBO p = pedidoService.buscarPedidoPorId(id);
		return ResponseEntity.ok().body(p);
	}
	
	@RequestMapping(value="/numpedido/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "Busca pedido pelo numPedido")
	public ResponseEntity<?> buscarPedidoPorNumPedido(@PathVariable Long id) {
		PedidoBO p = pedidoService.buscarPedidoPorNumPedido(id);
		return ResponseEntity.ok().body(p);
	}
	
	@RequestMapping(value="/notafiscal/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "Retorna dados da nota fiscal pelo número pedido")
	public ResponseEntity<?> montaNotaFiscal(@PathVariable Long id) {
		NotaFiscalBO p = pedidoService.montaNotaFiscal(id);
		return ResponseEntity.ok().body(p);
	}
	
	@RequestMapping(value="/todos", method = RequestMethod.GET)
	@ApiOperation(value = "Busca pedidos")
	public ResponseEntity<List<PedidoBO>> buscarProdutos() {
		List<PedidoBO> pedidos = pedidoService.litarPedidos();
		return ResponseEntity.ok().body(pedidos);
	}

	@RequestMapping(value= "semProduto", method = RequestMethod.POST)
	@ApiOperation(value = "Salva um pedido sem item de pedido")
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
	@ApiOperation(value = "Salva um pedido com item de pedido")
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
	@ApiOperation(value = "Atualiza um pedido")
	public ResponseEntity<Void> atualizarPedido(@RequestBody PedidoBO pedido){
		pedido = pedidoService.atualizarPedido(pedido);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	@ApiOperation(value = "Deleta um pedido pelo identificador")
	public ResponseEntity<Void> deletarPedidoPorId(@PathVariable Integer id) {
		pedidoService.deletarPedidoPorId(id);
		return ResponseEntity.noContent().build();
	}
}

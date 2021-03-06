package com.edimar.loja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.edimar.loja.model.dto.ItemPedidoBO;
import com.edimar.loja.model.dto.PedidoBO;
import com.edimar.loja.services.ItemPedidoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin
@RequestMapping(value="/itempedido")
@Api(tags = "ItemPedido", description = "API de itens pedidos")
public class ItemPedidoController {
	
	@Autowired
	private ItemPedidoService itemPedidoService;

	@RequestMapping(value="/{idNumpedido}/{idProduto}", method = RequestMethod.GET)
	@ApiOperation(value = "Busca pelo item pedido, passando identificador do pedido e identificador do produto")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Busca realizado com sucesso"),
			@ApiResponse(code = 404, message = "Não foi possível localizar"),
			@ApiResponse(code = 500, message = "Houve algum erro no processamento da informação passada")
	})
	public ResponseEntity<?> buscarPedidoPorId(@PathVariable Integer idNumpedido, @PathVariable Integer idProduto) throws Exception {
		ItemPedidoBO itemPedidoBO = itemPedidoService.buscarItemPedido(idNumpedido, idProduto);
		return ResponseEntity.ok().body(itemPedidoBO);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "Inclui um item pedido")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Inclusão realizado com sucesso"),
			@ApiResponse(code = 500, message = "Houve algum erro no processamento da informação passada")
	})
	public ResponseEntity<Void> salvarPedidoComProduto(@RequestBody PedidoBO pedido){
		itemPedidoService.incluirItemPedido(pedido);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	@ApiOperation(value = "Atualiza um item pedido")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Inclusão realizado com sucesso"),
			@ApiResponse(code = 500, message = "Houve algum erro no processamento da informação passada")
	})
	public ResponseEntity<Void> atualizarPedido(@RequestBody PedidoBO pedido){
		pedido = itemPedidoService.atualizarItemPedido(pedido);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{idNumpedido}/{idProduto}", method = RequestMethod.DELETE)
	@ApiOperation(value = "Deleta um item pedido, passando identificador do pedido e identificador do produto")
	@ApiResponses(value = {
			@ApiResponse(code = 204, message = "Exclusão realizado com sucesso"),
			@ApiResponse(code = 500, message = "Houve algum erro no processamento da informação passada")
	})
	public ResponseEntity<Void> deletarPedidoPorId(@PathVariable Integer idNumpedido, @PathVariable Integer idProduto) {
		itemPedidoService.deletarItemPedidoPorId(idNumpedido, idProduto);
		return ResponseEntity.noContent().build();
	}
}

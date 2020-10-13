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

import com.edimar.loja.model.dto.ProdutoBO;
import com.edimar.loja.services.ProdutoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin
@RequestMapping(value="/produto")
@Api(tags = "Produtos", description = "API de produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "Busca produto pelo codigo")
	public ResponseEntity<ProdutoBO> buscarProdutoPorId(@PathVariable Integer id) {
		ProdutoBO produto = produtoService.buscarProdutoPorId(id);
		return ResponseEntity.ok().body(produto);
	}
	
	@RequestMapping(value="/todos", method = RequestMethod.GET)
	@ApiOperation(value = "Busca lista de produtos existentes")
	public ResponseEntity<List<ProdutoBO>> buscarProdutos() {
		List<ProdutoBO> produtos = produtoService.litarProdutos();
		return ResponseEntity.ok().body(produtos);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "Incuir produto na base de dados")
	public ResponseEntity<Void> salvarProduto(@RequestBody ProdutoBO produtoBO){
		ProdutoBO retorno = produtoService.salvarProduto(produtoBO);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(retorno.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	@ApiOperation(value = "Atualizar produto pela base de dados")
	public ResponseEntity<Void> atualizarProduto(@RequestBody ProdutoBO produto){
		produtoService.atualizarProduto(produto);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	@ApiOperation(value = "Apaga produto pelo identificador")
	public ResponseEntity<Void> deletarProdutoPorId(@PathVariable Integer id) {
		produtoService.deletarProdutoPorId(id);
		return ResponseEntity.noContent().build();
	}
}

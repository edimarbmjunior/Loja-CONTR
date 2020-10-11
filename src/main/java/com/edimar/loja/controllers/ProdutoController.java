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

import com.edimar.loja.model.Produto;
import com.edimar.loja.model.dto.ProdutoBO;
import com.edimar.loja.services.ProdutoService;

@RestController
@RequestMapping(value="/produto")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<ProdutoBO> buscarProdutoPorId(@PathVariable Integer id) {
		ProdutoBO produto = produtoService.buscarProdutoPorId(id);
		return ResponseEntity.ok().body(produto);
	}
	
	@RequestMapping(value="/todos", method = RequestMethod.GET)
	public ResponseEntity<List<ProdutoBO>> buscarProdutos() {
		List<ProdutoBO> produtos = produtoService.litarProdutos();
		return ResponseEntity.ok().body(produtos);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> salvarProduto(@RequestBody ProdutoBO produtoBO){
		Produto retorno = produtoService.salvarProduto(produtoBO);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(retorno.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Void> atualizarProduto(@RequestBody ProdutoBO produto){
		produtoService.atualizarProduto(produto);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletarProdutoPorId(@PathVariable Integer id) {
		produtoService.deletarProdutoPorId(id);
		return ResponseEntity.noContent().build();
	}
}

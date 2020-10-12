package com.edimar.loja.services;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.edimar.loja.model.Produto;
import com.edimar.loja.model.convert.ProdutoConvert;
import com.edimar.loja.model.dto.ProdutoBO;
import com.edimar.loja.repositories.ProdutoRepository;
import com.edimar.loja.services.exceptions.GenericExcpetion;
import com.edimar.loja.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {

	private static Logger logger = LoggerFactory.getLogger(PedidoService.class);
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public List<ProdutoBO> litarProdutos(){
		List<Produto> produtos = produtoRepository.findAll();
		List<ProdutoBO> produtosBO = produtos.stream().map(produto -> ProdutoConvert.converterToProdutoBOFromProduto(produto)).collect(Collectors.toList());
		return produtosBO.isEmpty() ? Arrays.asList() : produtosBO;
	}

	public ProdutoBO buscarProdutoPorId(Integer id) {
		Produto produto = produtoRepository
				.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Produto não encontrado! ID: " + id +"."));
		ProdutoBO produtoBO = ProdutoConvert.converterToProdutoBOFromProduto(produto);
		return produtoBO;
	}
	
	public void salvarProdutos(List<Produto> produtos) {
		produtoRepository.saveAll(produtos);
	}
	
	public Produto salvarProduto(ProdutoBO produtoBO) {
		Produto produto = ProdutoConvert.converterToProdutoFromProdutoBO(produtoBO);
		produto.setId(null);
		return produtoRepository.save(produto);
	}
	
	public Produto atualizarProduto(ProdutoBO produtoBO) {
		buscarProdutoPorId(produtoBO.getId());
		Produto produto = ProdutoConvert.converterToProdutoFromProdutoBO(produtoBO);
		return produtoRepository.save(produto);
	}
	
	public void deletarProdutoPorId(Integer id) {
		buscarProdutoPorId(id);
		try {
			produtoRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new com.edimar.loja.services.exceptions.DataIntegrityViolationException("Não é possíve excluir produtos que que possuem pedidos");
		} catch (Exception e) {
			throw new GenericExcpetion("Erro ao ao tentar excluir -> " + id + " - Motivo: " + e.getMessage());
		}
	}
}

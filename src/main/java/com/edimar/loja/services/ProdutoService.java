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
import com.edimar.loja.service.validator.ValidacaoProduto;
import com.edimar.loja.services.exceptions.GenericExcpetion;
import com.edimar.loja.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {

	private static Logger logger = LoggerFactory.getLogger(PedidoService.class);
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public List<ProdutoBO> litarProdutos(){
		logger.info("BUSCANDO DOS PRODUTOS");
		List<Produto> produtos = produtoRepository.findAll();
		List<ProdutoBO> produtosBO = produtos.stream().map(produto -> ProdutoConvert.converterToProdutoBOFromProduto(produto)).collect(Collectors.toList());
		return produtosBO.isEmpty() ? Arrays.asList() : produtosBO;
	}

	public ProdutoBO buscarProdutoPorId(Integer id) {
		ValidacaoProduto.validacaoConsultar(id);
		Produto produto = produtoRepository
				.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Produto não encontrado! ID: " + id +"."));
		ProdutoBO produtoBO = ProdutoConvert.converterToProdutoBOFromProduto(produto);
		return produtoBO;
	}
	
	public void salvarProdutos(List<Produto> produtos) {
		produtoRepository.saveAll(produtos);
	}
	
	public ProdutoBO salvarProduto(ProdutoBO produtoBO) {
		ValidacaoProduto.validacaoIncluirProduto(produtoBO);
		Produto produto = ProdutoConvert.converterToProdutoFromProdutoBO(produtoBO);
		produto.setId(null);
		return ProdutoConvert.converterToProdutoBOFromProduto(produtoRepository.save(produto));
	}
	
	public ProdutoBO atualizarProduto(ProdutoBO produtoBO) {
		ValidacaoProduto.validacaoAtualziarProduto(produtoBO);
		buscarProdutoPorId(produtoBO.getId());
		Produto produto = ProdutoConvert.converterToProdutoFromProdutoBO(produtoBO);
		return ProdutoConvert.converterToProdutoBOFromProduto(produtoRepository.save(produto));
	}
	
	public void deletarProdutoPorId(Integer id) {
		ValidacaoProduto.validacaoConsultar(id);
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

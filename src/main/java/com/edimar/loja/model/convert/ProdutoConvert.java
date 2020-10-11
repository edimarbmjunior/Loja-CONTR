package com.edimar.loja.model.convert;

import com.edimar.loja.model.Produto;
import com.edimar.loja.model.dto.ProdutoBO;

public class ProdutoConvert {

	public static ProdutoBO converterToProdutoBOFromProduto(Produto produto) {
		ProdutoBO produtoBO = new ProdutoBO();
		produtoBO.setId(produto.getId());
		produtoBO.setCategoria(produto.getCategoria());
		produtoBO.setDescricao(produto.getDescricao());
		produtoBO.setPreco(produto.getPreco());
		return produtoBO;
	}
	
	public static Produto converterToProdutoFromProdutoBO(ProdutoBO produtoBO) {
		Produto produto = new Produto();
		produto.setId(produtoBO.getId());
		produto.setDescricao(produtoBO.getDescricao());
		produto.setCategoria(produtoBO.getCategoria());
		produto.setPreco(produtoBO.getPreco());
		return produto;
	}
}

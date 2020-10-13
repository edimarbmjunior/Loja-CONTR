package com.edimar.loja.builder;

import com.edimar.loja.model.Produto;
import com.edimar.loja.model.dto.ProdutoBO;

public class ProdutoBOBuilder {

	private ProdutoBO produtoBO;
	private Produto produto;
	
	private ProdutoBOBuilder() {}
	
	public static ProdutoBOBuilder montaEntityProdutoBOTipo1() {
		ProdutoBOBuilder dto = new ProdutoBOBuilder();
		dto.produtoBO = new ProdutoBO();
		dto.produtoBO.setId(1);
		dto.produtoBO.setCategoria("Alimento");;
		dto.produtoBO.setDescricao("Arroz");
		dto.produtoBO.setPreco(29.50d);
		return dto;
	}
	
	public static ProdutoBOBuilder montaEntityProdutoTipo1() {
		ProdutoBOBuilder dto = new ProdutoBOBuilder();
		dto.produto = new Produto();
		dto.produto.setId(1);
		dto.produto.setCategoria("Alimento");;
		dto.produto.setDescricao("Arroz");
		dto.produto.setPreco(29.50d);
		return dto;
	}
	
	public static ProdutoBOBuilder montaEntityProdutoBOTipo2() {
		ProdutoBOBuilder dto = new ProdutoBOBuilder();
		dto.produtoBO = new ProdutoBO();
		dto.produtoBO.setId(2);
		dto.produtoBO.setCategoria("Alimento");;
		dto.produtoBO.setDescricao("Feijao");
		dto.produtoBO.setPreco(10.25d);
		return dto;
	}
	
	public ProdutoBO retornoEntityProdutoBO() {
		return produtoBO;
	}
	
	public Produto retornoEntityProduto() {
		return produto;
	}
}

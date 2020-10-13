package com.edimar.loja.builder;

import com.edimar.loja.model.dto.ProdutoBO;

public class ProdutoBOBuilder {

	private ProdutoBO produtoBO;
	
	private ProdutoBOBuilder() {}
	
	public static ProdutoBOBuilder montaEntityTipo1() {
		ProdutoBOBuilder dto = new ProdutoBOBuilder();
		dto.produtoBO = new ProdutoBO();
		dto.produtoBO.setId(1);
		dto.produtoBO.setCategoria("Alimento");;
		dto.produtoBO.setDescricao("Arroz");
		dto.produtoBO.setPreco(29.50d);
		return dto;
	}
	
	public static ProdutoBOBuilder montaEntityTipo2() {
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
}

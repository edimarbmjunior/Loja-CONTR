package com.edimar.loja.service.validator;

import com.edimar.loja.model.dto.ProdutoBO;
import com.edimar.loja.services.exceptions.GenericExcpetion;

public class ValidacaoProduto {
	
	public static void validacaoConsultar(Integer id) {
		isIdentificadorValido(id);
	}
	
	public static void validacaoIncluirProduto(ProdutoBO produtoBO) {
		campoObrigatorio(isNULL(produtoBO.getCategoria()), "categoria");
		campoObrigatorio(isNULL(produtoBO.getDescricao()), "descrição");
		validacaoDescricao(produtoBO.getDescricao());
		campoObrigatorio(isNULL(produtoBO.getPreco()), "preço");
		validaPreco(produtoBO.getPreco());
	}
	
	public static void validacaoAtualziarProduto(ProdutoBO produtoBO) {
		isIdentificadorValido(produtoBO.getId());
		campoObrigatorio(isNULL(produtoBO.getCategoria()), "categoria");
		campoObrigatorio(isNULL(produtoBO.getDescricao()), "descrição");
		validacaoDescricao(produtoBO.getDescricao());
		campoObrigatorio(isNULL(produtoBO.getPreco()), "preço");
		validaPreco(produtoBO.getPreco());
	}

	private static void isIdentificadorValido(Integer id) {
		if(null==id
				|| id <= 0) {
			throw new GenericExcpetion("Identificador enviado é inválido!");
		}
	}
	
	private static void validacaoDescricao(String descricao) {
		if(descricao != null && descricao.length() <= 5) {
			throw new GenericExcpetion("Descrição é inválido. Quantidade mínima de carateres é 6!");
		}
	}
	
	private static void validaPreco(Double preco) {
		if(preco >= 0) {
			throw new GenericExcpetion("Preço é inválido. Valor deve ser maior que zero!");
		}
	}

	private static Boolean isNULL(Object obj) {
		if(obj==null) {
			return true;
		}
		return false;
	}
	
	private static void campoObrigatorio(Boolean fazCritica, String nomeCampo) {
		if(fazCritica) throw new GenericExcpetion("Campo " + nomeCampo + " é obrigatório");
	}
}

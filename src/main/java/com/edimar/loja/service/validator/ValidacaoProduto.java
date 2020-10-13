package com.edimar.loja.service.validator;

import com.edimar.loja.model.dto.PedidoBO;
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
	
	private static void validacaoDataPedido(String dataPedido) {
		if(dataPedido != null && dataPedido.length() != 10) {
			throw new GenericExcpetion("Data do pedido é inválido.");
		}
		if(!isInteiro(dataPedido.substring(0, 1))
				|| !isInteiro(dataPedido.substring(6, 10))
				|| !isInteiro(dataPedido.substring(3, 4))
				|| "/".equals(dataPedido.substring(2, 2))
				|| "/".equals(dataPedido.substring(5, 5))) {
			throw new GenericExcpetion("Data do pedido é inválido.");
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
	
	private static void campoNaoPodeAlterar(Boolean fazCritica, String nomeCampo) {
		if(fazCritica) throw new GenericExcpetion("Campo " + nomeCampo + " não pode ser informado!");
	}
	
	private static void validaItemPedidoNaoPodeVir(PedidoBO pedidoBO) {
		if(pedidoBO.getItemPedidos() != null && (!pedidoBO.getItemPedidos().isEmpty() && pedidoBO.getItemPedidos().size() > 0)) {
			throw new GenericExcpetion("Não pode ser enviado dados de pedidos");
		}
	}
	
	private static void validaItemPedidoObrigatorio(PedidoBO pedidoBO) {
		if(pedidoBO.getItemPedidos() == null || (pedidoBO.getItemPedidos().isEmpty() || pedidoBO.getItemPedidos().size() == 0)) {
			throw new GenericExcpetion("Deve conter dados de pedidos");
		}
	}
	
	private static boolean isInteiro(String s) {
		// cria um array de char
		char[] c = s.toCharArray();
		boolean d = true;

		for ( int i = 0; i < c.length; i++ ) {
		    // verifica se o char não é um dígito
		    if ( !Character.isDigit( c[ i ] ) ) {
		        d = false;
		        break;
		    }
		}

		return d;
	}
}

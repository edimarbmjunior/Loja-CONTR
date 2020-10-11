package com.edimar.loja.model.convert;

import com.edimar.loja.model.ItemPedido;
import com.edimar.loja.model.ItemPedidoPK;
import com.edimar.loja.model.Pedido;
import com.edimar.loja.model.dto.ItemPedidoBO;
import com.edimar.loja.model.dto.PedidoBO;

public class ItemPedidoConvert {

	public static ItemPedido converterToItemPedidoFromItemPedidoBO(ItemPedidoBO itemPedidoDTO, Integer identificadorPedido) {
		ItemPedido itemPedido = new ItemPedido();
		ItemPedidoPK itemPedidoPK = new ItemPedidoPK();
		Pedido pedido = new Pedido();
		pedido.setId(identificadorPedido);
		itemPedidoPK.setPedido(pedido);
		itemPedidoPK.setProduto(ProdutoConvert.converterToProdutoFromProdutoBO(itemPedidoDTO.getProdutoBO()));
		itemPedido.setId(itemPedidoPK);
		itemPedido.setQtde(itemPedidoDTO.getQtde());
		return itemPedido;
	}
	
	public static ItemPedido converterToItemPedidoFromItemPedidoBOTipo3(ItemPedidoBO itemPedidoDTO, Integer identificadorPedido) {
		ItemPedido itemPedido = new ItemPedido();
		ItemPedidoPK itemPedidoPK = new ItemPedidoPK();
		Pedido pedido = new Pedido();
		pedido.setId(identificadorPedido);
		itemPedidoPK.setPedido(pedido);
		itemPedidoPK.setProduto(ProdutoConvert.converterToProdutoFromProdutoBO(itemPedidoDTO.getProdutoBO()));
		itemPedido.setId(itemPedidoPK);
		itemPedido.setQtde(itemPedidoDTO.getQtde());
		return itemPedido;
	}
	
	public static ItemPedidoBO converterToItemPedidoBoFromItemPedido(ItemPedido itemPedido) {
		ItemPedidoBO itemPedidoDTO = new ItemPedidoBO();
		itemPedidoDTO.setProdutoBO(ProdutoConvert.converterToProdutoBOFromProduto(itemPedido.getProduto()));
		itemPedidoDTO.setQtde(itemPedido.getQtde());
		itemPedidoDTO.setValorTotalItensPedido(itemPedido.getProduto().getPreco() == null ? 0d : itemPedidoDTO.getValorTotalItensPedido());
		return itemPedidoDTO;
	}
	
	public static ItemPedido converterToItemPedidoFromItemPedidoBO(ItemPedidoBO itemPedidoDTO, PedidoBO pedidoBO) {
		ItemPedido itemPedido = new ItemPedido();
		ItemPedidoPK itemPedidoPK = new ItemPedidoPK();
		itemPedidoPK.setPedido(PedidoConvert.converterToPedidoFromPedidoBO(pedidoBO));
		itemPedidoPK.setProduto(ProdutoConvert.converterToProdutoFromProdutoBO(itemPedidoDTO.getProdutoBO()));
		itemPedido.setId(itemPedidoPK);
		itemPedido.setQtde(itemPedidoDTO.getQtde());
		return itemPedido;
	}
}

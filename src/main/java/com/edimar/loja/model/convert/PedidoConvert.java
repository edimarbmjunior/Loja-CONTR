package com.edimar.loja.model.convert;

import java.util.stream.Collectors;

import com.edimar.loja.model.Pedido;
import com.edimar.loja.model.dto.PedidoBO;
import com.edimar.loja.services.Util.CalculoFrete;
import com.edimar.loja.services.Util.DataUtil;

public class PedidoConvert {

	public static Pedido converterToPedidoFromPedidoBO(PedidoBO pedidoBO) {
		Pedido pedido = new Pedido();
		pedido.setId(pedidoBO.getId());
		pedido.setCliente(ClienteConvert.converterToClienteFromClienteBOTipo2(pedidoBO.getClienteBO()));
		pedido.setDataPedido(DataUtil.converterStringToDate(pedidoBO.getDataPedido()));
		pedido.setNumPedido(pedidoBO.getNumPedido());
		pedido.setItemPedidos(pedidoBO.getItemPedidos() == null ? null : pedidoBO.getItemPedidos().stream().map(itemPedido -> ItemPedidoConvert.converterToItemPedidoFromItemPedidoBO(itemPedido, pedidoBO.getId())).collect(Collectors.toSet()));
		return pedido;
	}
	
	public static Pedido converterToPedidoFromPedidoBOTipo2(PedidoBO pedidoBO) {
		Pedido pedido = new Pedido();
		pedido.setId(pedidoBO.getId());
		pedido.setCliente(ClienteConvert.converterToClienteFromClienteBOTipo2(pedidoBO.getClienteBO()));
		pedido.setDataPedido(DataUtil.converterStringToDate(pedidoBO.getDataPedido()));
		pedido.setNumPedido(pedidoBO.getNumPedido());
		pedido.setItemPedidos(pedidoBO.getItemPedidos().stream().map(itemPedido -> ItemPedidoConvert.converterToItemPedidoFromItemPedidoBO(itemPedido, pedidoBO)).collect(Collectors.toSet()));
		return pedido;
	}
	
	public static Pedido converterToPedidoFromPedidoBOTipo3(PedidoBO pedidoBO) {
		Pedido pedido = new Pedido();
		pedido.setId(pedidoBO.getId());
		pedido.setCliente(ClienteConvert.converterToClienteFromClienteBOTipo2(pedidoBO.getClienteBO()));
		pedido.setDataPedido(DataUtil.converterStringToDate(pedidoBO.getDataPedido()));
		pedido.setNumPedido(pedidoBO.getNumPedido());
		pedido.setItemPedidos(pedidoBO.getItemPedidos().stream().map(itemPedido -> ItemPedidoConvert.converterToItemPedidoFromItemPedidoBO(itemPedido, pedidoBO.getId())).collect(Collectors.toSet()));
		return pedido;
	}
	
	public static PedidoBO converterToPedidoBoFromPedido(Pedido pedido) {
		PedidoBO pedidoBO = new PedidoBO();
		pedidoBO.setId(pedido.getId());
		pedidoBO.setDataPedido(DataUtil.converterDateToString(pedido.getDataPedido()));
		pedidoBO.setNumPedido(pedido.getNumPedido());
		pedidoBO.setClienteBO(ClienteConvert.converterToClienteBoFromClienteTipo2(pedido.getCliente()));
		pedidoBO.setItemPedidos(pedido.getItemPedidos() == null ? null : pedido.getItemPedidos().stream().map(itemPedido -> ItemPedidoConvert.converterToItemPedidoBoFromItemPedido(itemPedido)).collect(Collectors.toSet()));
		pedidoBO.setValorTotalProdutos(pedidoBO.getValorTotalProdutos());
		Double valorFrete = CalculoFrete.verificarCepDados(pedidoBO.getClienteBO().getCep()) == null ?
				null
				:
				CalculoFrete.verificarCepDados(pedidoBO.getClienteBO().getCep()).getValorFrete();
		pedidoBO.setValorFrete(valorFrete);
		
		return pedidoBO;
	}
}

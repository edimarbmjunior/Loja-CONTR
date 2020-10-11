package com.edimar.loja.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edimar.loja.model.ItemPedido;
import com.edimar.loja.repositories.ItemPedidoRepository;
import com.edimar.loja.services.exceptions.ObjectNotFoundException;

@Service
public class ItemPedidoService {

	private static Logger logger = LoggerFactory.getLogger(PedidoService.class);
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public ItemPedido buscarProdutoPorId(Integer id) throws Exception {
		ItemPedido itemPedido = itemPedidoRepository
				.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("ItemPedido não encontrado! ID: " + id +"."));
		return itemPedido;
	}
	
	public void salvarItensPedidos(List<ItemPedido> itensPedidos) {
		itemPedidoRepository.saveAll(itensPedidos);
	}
	
	public ItemPedido salvarItemPedido(ItemPedido itemPedido) {
		return itemPedidoRepository.save(itemPedido);
	}
}

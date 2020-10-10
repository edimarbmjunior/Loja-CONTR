package com.edimar.loja.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edimar.loja.model.ItemPedido;
import com.edimar.loja.repositories.ItemPedidoRepository;

@Service
public class ItemPedidoService {

private static Logger logger = LoggerFactory.getLogger(PedidoService.class);
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public ItemPedido buscarProdutoPorId(Integer id) throws Exception {
		ItemPedido c = null;
		try {
			c = itemPedidoRepository.findById(id).get();
		}catch (NoSuchElementException noSuchElementException) {
			logger.warn("Não foi encontrado produto");
			return c;
		} catch (Exception eXception) {
			logger.error("Error ao acessar a base de informações, " + eXception.getMessage());
			throw new Exception("Error na tentativa de busca do usuario");
		}
		return c;
	}
	
	public void salvarItensPedidos(List<ItemPedido> itensPedidos) {
		itemPedidoRepository.saveAll(itensPedidos);
	}
	
	public void salvarItemPedido(ItemPedido itemPedido) {
		itemPedidoRepository.save(itemPedido);
	}
}

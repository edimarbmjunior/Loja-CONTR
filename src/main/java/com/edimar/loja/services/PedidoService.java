package com.edimar.loja.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edimar.loja.model.Pedido;
import com.edimar.loja.repositories.PedidoRepository;

@Service
public class PedidoService {
	
	private static Logger logger = LoggerFactory.getLogger(PedidoService.class);
	
	@Autowired
	private PedidoRepository pedidoRepository;

	public Pedido buscarPedidoPorId(Integer id) throws Exception {
		Pedido c = null;
		try {
			c = pedidoRepository.findById(id).get();
		}catch (NoSuchElementException noSuchElementException) {
			logger.warn("Não foi encontrado pedido");
			return c;
		} catch (Exception eXception) {
			logger.error("Error ao acessar a base de informações, " + eXception.getMessage());
			throw new Exception("Error na tentativa de busca do usuario");
		}
		return c;
	}
	
	public void salvarPedidos(List<Pedido> pedidos) {
		pedidoRepository.saveAll(pedidos);
	}
	
	public void salvarPedido(Pedido pedido) {
		pedidoRepository.save(pedido);
	}
}

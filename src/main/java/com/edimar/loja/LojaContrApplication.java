package com.edimar.loja;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.edimar.loja.model.Cliente;
import com.edimar.loja.model.ItemPedido;
import com.edimar.loja.model.Pedido;
import com.edimar.loja.model.Produto;
import com.edimar.loja.services.ClienteService;
import com.edimar.loja.services.ItemPedidoService;
import com.edimar.loja.services.PedidoService;
import com.edimar.loja.services.ProdutoService;

@SpringBootApplication
public class LojaContrApplication implements CommandLineRunner {
// public class LojaContrApplication{
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private ItemPedidoService itemPedidoService;

	public static void main(String[] args) {
		SpringApplication.run(LojaContrApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//Para incluir automaticamente logo após instanciar
		Cliente c1 = new Cliente(null, "Juan", "95481086077", "Rua 1", 24750370, "Zona 1", "São Paulo", "SP", "21981494070");
		Cliente c2 = new Cliente(null, "Michael", "84978743079", "Rua 2", 24750370, "Zona 2", "São Paulo", "SP", "21981494071");
		clienteService.salvarClientes(Arrays.asList(c1, c2));
		// c1 = clienteService.buscarClientePorId(1);
		// c2 = clienteService.buscarClientePorId(2);
		
		Produto prd1 = new Produto(null, "Arroz", "Alimento", 29.50);
		Produto prd2 = new Produto(null, "Feijao", "Alimento", 10.25);
		Produto prd3 = new Produto(null, "Macarrao", "Alimento", 4.48);
		Produto prd4 = new Produto(null, "Oleo", "Alimento", 7.71);
		produtoService.salvarProdutos(Arrays.asList(prd1, prd2, prd3, prd4));
		// prd1 = produtoService.buscarProdutoPorId(1);
		// prd2 = produtoService.buscarProdutoPorId(2);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		// sdf.parse("30/12/2020 23:59");

		Pedido ped1 = new Pedido(null, sdf.parse("05/10/2020 13:58"), 1l, c1);
		Pedido ped2 = new Pedido(null, sdf.parse("05/10/2020 15:09"), 2l, c2);
		Pedido ped3 = new Pedido(null, sdf.parse("05/10/2020 15:09"), 3l, c2);
		pedidoService.salvarPedidos(Arrays.asList(ped1, ped2, ped3));
		// ped1 = pedidoService.buscarPedidoPorId(1);
		// ped2 = pedidoService.buscarPedidoPorId(2);
		// ped3 = pedidoService.buscarPedidoPorId(3);
		
		ItemPedido item1 = new ItemPedido(ped1, prd1, 1);
		ItemPedido item2 = new ItemPedido(ped2, prd2, 3);
		ItemPedido item3 = new ItemPedido(ped2, prd4, 2);
		ItemPedido item4 = new ItemPedido(ped3, prd2, 2);
		ItemPedido item5 = new ItemPedido(ped3, prd3, 5);
		ItemPedido item6 = new ItemPedido(ped1, prd1, 2);
		itemPedidoService.salvarItensPedidos(Arrays.asList(item1, item2, item3, item4, item5, item6));
		System.out.println("Fim das inclusões!");
	}
}

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
		Cliente c1 = new Cliente(null, "Juan", "00000000001", "Rua 1", 24750370, "Zona 1", "São Paulo", "SP", "21981494070");
		Cliente c2 = new Cliente(null, "Juan", "00000000002", "Rua 2", 24750370, "Zona 2", "São Paulo", "SP", "21981494071");
		clienteService.salvarClientes(Arrays.asList(c1, c2));
		
		Produto prd1 = new Produto(null, "Arroz", "Alimento", 29.50);
		Produto prd2 = new Produto(null, "Feijao", "Alimento", 10.25);
		produtoService.salvarProdutos(Arrays.asList(prd1, prd2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		// sdf.parse("30/12/2020 23:59");

		Pedido ped1 = new Pedido(null, sdf.parse("05/10/2020 13:58"), 1l, c1, 0d);
		Pedido ped2 = new Pedido(null, sdf.parse("05/10/2020 15:09"), 2l, c2, 0d);
		Pedido ped3 = new Pedido(null, sdf.parse("05/10/2020 15:09"), 2l, c2, 0d);
		pedidoService.salvarPedidos(Arrays.asList(ped1, ped2, ped3));
		
		ItemPedido item1 = new ItemPedido(ped1, prd1, 2);
		item1.getValorTotalItensPedido();
		ItemPedido item2 = new ItemPedido(ped2, prd2, 5);
		itemPedidoService.salvarItensPedidos(Arrays.asList(item1, item2));
	}
}

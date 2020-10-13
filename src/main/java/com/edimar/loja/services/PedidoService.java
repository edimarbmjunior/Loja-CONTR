package com.edimar.loja.services;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.edimar.loja.model.ItemPedido;
import com.edimar.loja.model.Pedido;
import com.edimar.loja.model.convert.PedidoConvert;
import com.edimar.loja.model.convert.ProdutoConvert;
import com.edimar.loja.model.dto.ItemPedidoBO;
import com.edimar.loja.model.dto.NotaFiscalBO;
import com.edimar.loja.model.dto.PedidoBO;
import com.edimar.loja.repositories.PedidoRepository;
import com.edimar.loja.service.validator.ValidacaoPedido;
import com.edimar.loja.services.Util.CalculoFrete;
import com.edimar.loja.services.exceptions.GenericExcpetion;
import com.edimar.loja.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	private static Logger logger = LoggerFactory.getLogger(PedidoService.class);
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ClienteService clienteService;

	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ItemPedidoService itemPedidoService;

	public PedidoBO buscarPedidoPorId(Integer id) {
		ValidacaoPedido.validacaoConsultar(id);
		Pedido p = pedidoRepository
				.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Pedido não encontrado! ID: " + id +"."));
		PedidoBO retorno = PedidoConvert.converterToPedidoBoFromPedido(p);
		return retorno;
	}
	
	public PedidoBO buscarPedidoPorNumPedido(Long numPedido) {
		ValidacaoPedido.validacaoConsultarNumPedido(numPedido);
		Integer identificador = pedidoRepository
				.recuperaPeloNumPedido(numPedido);
		PedidoBO retorno = buscarPedidoPorId(identificador);
		return retorno;
	}
	
	public NotaFiscalBO montaNotaFiscal(Long numPedido) {
		NotaFiscalBO notaFiscalBO = new NotaFiscalBO();
		PedidoBO pedidoBO = buscarPedidoPorNumPedido(numPedido);
		notaFiscalBO.setCpfCliente(pedidoBO.getClienteBO().getCpf());
		notaFiscalBO.setCepCliente(pedidoBO.getClienteBO().getCep());
		notaFiscalBO.setFretePedido(pedidoBO.getValorFrete());
		notaFiscalBO.setItensPedidos(pedidoBO.getItemPedidos());
		notaFiscalBO.setNomeCliente(pedidoBO.getClienteBO().getNome());
		
		CalculoFrete calculo = CalculoFrete.verificarCepDados(pedidoBO.getClienteBO().getCep());
		
		notaFiscalBO.setNomeEstadoCliente(calculo.getEstado());
		notaFiscalBO.setNumPedido(pedidoBO.getNumPedido());
		notaFiscalBO.setUfCliente(calculo.getUf());
		return notaFiscalBO;
	}
	
	public List<PedidoBO> litarPedidos(){
		List<Pedido> pedidos = pedidoRepository.findAll();
		List<PedidoBO> retorno = pedidos.stream().map(pedido -> PedidoConvert.converterToPedidoBoFromPedido(pedido)).collect(Collectors.toList());
		return retorno.isEmpty() ? Arrays.asList() : retorno;
	}
	
	public void salvarPedidos(List<Pedido> pedidos) {
		pedidoRepository.saveAll(pedidos);
	}
	
	private Pedido salvarPedido(Pedido pedido) {
		pedido.setId(null);
		return pedidoRepository.save(pedido);
	}

	public Integer incluirPedidoSemProduto(PedidoBO pedidoBO) {
		ValidacaoPedido.validacaoIncluirSemProduto(pedidoBO);
		long ultimNumPedido = pedidoRepository.ultimoNumPedido().getNumPedido() + 2l;
		pedidoBO.setNumPedido(ultimNumPedido);
		pedidoBO.setClienteBO(clienteService.buscarClientePorId(pedidoBO.getClienteBO().getId()));
		pedidoBO.setItemPedidos(new HashSet<>());
		Pedido pedido = PedidoConvert.converterToPedidoFromPedidoBO(pedidoBO);
		return salvarPedido(pedido).getId();
	}
	
	public Integer incluirPedidoComProduto(PedidoBO pedidoBO) {
		ValidacaoPedido.validacaoIncluirComProduto(pedidoBO);
		long ultimNumPedido = pedidoRepository.ultimoNumPedido().getNumPedido() + 1l;
		Pedido pedido = new Pedido();
		pedidoBO.setNumPedido(ultimNumPedido);
		pedidoBO.setClienteBO(clienteService.buscarClientePorId(pedidoBO.getClienteBO().getId()));
		pedidoBO.setItemPedidos(pedidoBO
				.getItemPedidos()
				.stream()
				.map(itemPedido -> new ItemPedidoBO(produtoService.buscarProdutoPorId(itemPedido.getProdutoBO().getId()), itemPedido.getQtde()))
				.collect(Collectors.toSet()));
		pedido = PedidoConvert.converterToPedidoFromPedidoBO(pedidoBO);
		final Pedido pedidoSalvo = salvarPedido(pedido);
		pedidoBO
			.getItemPedidos()
			.stream()
			.forEach((itemPedido) -> {
				ItemPedido itemPedidoSalvar = new ItemPedido(pedidoSalvo, ProdutoConvert.converterToProdutoFromProdutoBO(itemPedido.getProdutoBO()), itemPedido.getQtde());
				if(itemPedidoSalvar != null && (itemPedidoSalvar.getPedido() != null & itemPedidoSalvar.getPedido() !=null)) {
					itemPedidoService.salvarItemPedido(itemPedidoSalvar);
				}
			});
		return pedidoSalvo.getId();
	}
	
	public PedidoBO atualizarPedido(PedidoBO pedidoBO) {
		ValidacaoPedido.validacaoAtualziar(pedidoBO);
		buscarPedidoPorId(pedidoBO.getId());
		pedidoBO.setClienteBO(clienteService.buscarClientePorId(pedidoBO.getClienteBO().getId()));
		pedidoBO.setItemPedidos(pedidoBO
				.getItemPedidos()
				.stream()
				.map(itemPedido -> new ItemPedidoBO(produtoService.buscarProdutoPorId(itemPedido.getProdutoBO().getId()), itemPedido.getQtde()))
				.collect(Collectors.toSet()));
		Pedido pedido = PedidoConvert.converterToPedidoFromPedidoBO(pedidoBO);
		final Pedido pedidoSalvo = pedidoRepository.save(pedido);
		pedidoBO
			.getItemPedidos()
			.stream()
			.forEach((itemPedido) -> {
				ItemPedido itemPedidoSalvar = new ItemPedido(pedidoSalvo, ProdutoConvert.converterToProdutoFromProdutoBO(itemPedido.getProdutoBO()), itemPedido.getQtde());
				if(itemPedidoSalvar != null && (itemPedidoSalvar.getPedido() != null & itemPedidoSalvar.getPedido() !=null)) {
					itemPedidoService.salvarItemPedido(itemPedidoSalvar);
				}
			});
		return PedidoConvert.converterToPedidoBoFromPedido(pedidoSalvo);
	}
	
	public void deletarPedidoPorId(Integer id) {
		ValidacaoPedido.validacaoConsultar(id);
		final PedidoBO pedidoBO = buscarPedidoPorId(id);
		final Pedido pedidoSalvo = PedidoConvert.converterToPedidoFromPedidoBO(pedidoBO);
		try {
			pedidoBO
				.getItemPedidos()
				.stream()
				.forEach((itemPedido) -> {
					ItemPedido itemPedidoSalvar = new ItemPedido(pedidoSalvo, ProdutoConvert.converterToProdutoFromProdutoBO(itemPedido.getProdutoBO()), itemPedido.getQtde());
					if(itemPedidoSalvar != null && (itemPedidoSalvar.getPedido() != null & itemPedidoSalvar.getPedido() !=null)) {
						itemPedidoService.deletarPedido(itemPedidoSalvar);
					}
				});
			pedidoRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new com.edimar.loja.services.exceptions.DataIntegrityViolationException("Não é possíve excluir pedidos que possuem itens.");
		} catch (Exception e) {
			throw new GenericExcpetion("Erro ao ao tentar excluir -> " + id + " - Motivo: " + e.getMessage());
		}
	}
}

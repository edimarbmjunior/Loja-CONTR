package com.edimar.loja.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edimar.loja.model.Produto;
import com.edimar.loja.repositories.ProdutoRepository;

@Service
public class ProdutoService {

private static Logger logger = LoggerFactory.getLogger(PedidoService.class);
	
	@Autowired
	private ProdutoRepository produtoRepository;

	public Produto buscarProdutoPorId(Integer id) throws Exception {
		Produto c = null;
		try {
			c = produtoRepository.findById(id).get();
		}catch (NoSuchElementException noSuchElementException) {
			logger.warn("Não foi encontrado produto");
			return c;
		} catch (Exception eXception) {
			logger.error("Error ao acessar a base de informações, " + eXception.getMessage());
			throw new Exception("Error na tentativa de busca do usuario");
		}
		return c;
	}
	
	public void salvarProdutos(List<Produto> produtos) {
		produtoRepository.saveAll(produtos);
	}
	
	public void salvarProduto(Produto produto) {
		produtoRepository.save(produto);
	}
}

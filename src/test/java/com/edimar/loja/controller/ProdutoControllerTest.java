package com.edimar.loja.controller;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.edimar.loja.builder.ProdutoBOBuilder;
import com.edimar.loja.controllers.ProdutoController;
import com.edimar.loja.model.dto.ProdutoBO;
import com.edimar.loja.repositories.ProdutoRepository;
import com.edimar.loja.services.ProdutoService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
		ProdutoController.class
})
public class ProdutoControllerTest {
	
	//URL base para acesso desse controlador
    private final String BASE_URL = "/produto";
	
	@Autowired
	private ProdutoController produtoController;
	
	private MockMvc mockMvc;
	
	//Instância do ObjectMapper para trabalhar com JSON
    private ObjectMapper objectMapper;
	
	@MockBean
	private ProdutoRepository mockProdutoRepository;
	
	@MockBean
	private ProdutoService mockProdutoService;
	
	@Before
	public void setup() {
		objectMapper = new ObjectMapper();
		mockMvc = MockMvcBuilders
                .standaloneSetup(produtoController)
                .build();
	}
	
	@Test
	public void deveRetornarSucesso_BuscaProduto() throws Exception {
		ProdutoBO produto = ProdutoBOBuilder.montaEntityTipo1().retornoEntityProdutoBO();
		when(mockProdutoService.buscarProdutoPorId(1)).thenReturn(produto);
		
		/*mockMvc.perform(get(BASE_URL + "/1"))
	        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
	        .andExpect(status().isOk())
	        .andExpect(jsonPath("$.id", is(1)))
	        .andExpect(jsonPath("$.descricao", is("Arroz")))
	        .andExpect(jsonPath("$.categoria", is("Alimento")))
	        .andExpect(jsonPath("$.preco", is(29.50d)));*/
		
		verify(mockProdutoService, times(1)).buscarProdutoPorId(1);
	}
	
	@Test
	public void incluirSucesso_SalvarProduto() throws Exception {
		ProdutoBO produto = ProdutoBOBuilder.montaEntityTipo1().retornoEntityProdutoBO();
		when(mockProdutoService.salvarProduto(produto)).thenReturn(produto);
		
		/*mockMvc.perform(get(BASE_URL + "/1"))
	        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
	        .andExpect(status().isOk())
	        .andExpect(jsonPath("$.id", is(1)))
	        .andExpect(jsonPath("$.descricao", is("Arroz")))
	        .andExpect(jsonPath("$.categoria", is("Alimento")))
	        .andExpect(jsonPath("$.preco", is(29.50d)));*/
		
		verify(mockProdutoService, times(1)).buscarProdutoPorId(1);
	}
	
	@Test
    public void deveRetornarError_BuscaProduto() throws Exception {
		ProdutoBO produto1 = ProdutoBOBuilder.montaEntityTipo1().retornoEntityProdutoBO();
		ProdutoBO produto2 = ProdutoBOBuilder.montaEntityTipo2().retornoEntityProdutoBO();
		when(mockProdutoService.buscarProdutoPorId(1)).thenReturn(produto1);

		mockProdutoService.buscarProdutoPorId(2);
		
		assertNotEquals(produto2, produto1);
    }
}

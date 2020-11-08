package com.edimar.loja.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.edimar.loja.services.ProdutoService;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProdutoControllerTest {
	
	@Autowired
	private WebApplicationContext context;
	
	private MockMvc mockMvc;
	
	private static String BASE_URL = "/produto";
	
	@MockBean
	private ProdutoService produtoService;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}
	
	@Test
	public void teste01_deveRetornarSucesso_BuscaProduto() throws Exception {
		this.mockMvc.perform(get(BASE_URL+"/1"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("descricao", equalTo("Arroz")));
	}

	@Test
	public void teste02_deveRetornarError_BuscaProduto() throws Exception {
		this.mockMvc.perform(get(BASE_URL+"/5"))
			.andExpect(status().isNotFound());
	}
	
	@Test
	public void teste03_deveRetornarSucesso_BuscaProduto() throws Exception {
		this.mockMvc.perform(get(BASE_URL+"/todos")
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());
	}
	
	@Test
	public void teste04_deveRetornarSucesso_SalvarProduto() throws Exception {
		this.mockMvc.perform(post(BASE_URL)
				.content("{\"descricao\": \"Biscoito\",\"categoria\": \"Alimento\",\"preco\": 2.73}")
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated())
			.andExpect(header().string("Location", is("http://localhost/produto/5")));
			//.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void teste05_deveRetornarError_SalvarProduto() throws Exception {
		this.mockMvc.perform(post(BASE_URL)
				.content("{\"descricao\": \"Biscoito\",\"categoria\": \"Alimento\",\"preco\": 0.0}")
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isInternalServerError());
			//.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void teste06_deveRetornarSucesso_AtualizarProduto() throws Exception {
		this.mockMvc.perform(put(BASE_URL)
				.content("{\"id\": 3, \"descricao\": \"Leite\", \"categoria\": \"Alimento\", \"preco\": 4.52}")
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isNoContent());
			//.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void teste07_deveRetornarError_AtualizarProduto() throws Exception {
		this.mockMvc.perform(put(BASE_URL)
				.content("{\"id\": 3, \"descricao\": \"Leite\", \"categoria\": \"Alimento\", \"preco\": 0.0}")
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isInternalServerError());
			//.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void teste08_deveRetornarSucesso_DeletarProdutoPorId() throws Exception {
		this.mockMvc.perform(delete(BASE_URL+"/5"))
			.andExpect(status().isNoContent());
			//.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void teste09_deveRetornarError_DeletarProdutoPorId() throws Exception {
		this.mockMvc.perform(delete(BASE_URL+"/6"))
			.andExpect(status().isNotFound());
			//.andDo(MockMvcResultHandlers.print());
	}
}

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
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ItemPedidoControllerTest {
	
	@Autowired
	private WebApplicationContext context;
	
	private MockMvc mockMvc;
	
	private static String BASE_URL = "/itempedido";

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}
	
	@Test
	public void teste01_deveRetornarSucesso_BuscaProduto() throws Exception {
		this.mockMvc.perform(get(BASE_URL+"/3/3"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("valorTotalItensPedido", equalTo(22.4)));
	}

	@Test
	public void teste02_deveRetornarError_BuscaProduto() throws Exception {
		this.mockMvc.perform(get(BASE_URL+"/1/4"))
			.andExpect(status().isInternalServerError());
	}
	
	@Test
	public void teste03_deveRetornarSucesso_SalvarProduto() throws Exception {
		this.mockMvc.perform(post(BASE_URL)
				.content("{\"id\": 1,\"itemPedidos\": [{\"produtoBO\": {\"id\": 3},\"qtde\": 15}]}")
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isNoContent());
	}

	@Test
	public void teste04_deveRetornarSucesso_AtualizarProduto() throws Exception {
		this.mockMvc.perform(put(BASE_URL)
				.content("{\"id\": 1,\"itemPedidos\": [{\"produtoBO\": {\"id\": 3},\"qtde\": 2}]}")
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isNoContent());
			//.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void teste09_deveRetornarSucesso_DeletarProdutoPorId() throws Exception {
		this.mockMvc.perform(delete(BASE_URL+"/1/3"))
			.andExpect(status().isNoContent());
			//.andDo(MockMvcResultHandlers.print());
	}
}

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
public class PedidoControllerTest {
	
	@Autowired
	private WebApplicationContext context;
	
	private MockMvc mockMvc;
	
	private static String BASE_URL = "/pedido";

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}
	
	@Test
	public void teste01_deveRetornarSucesso_BuscaProduto() throws Exception {
		this.mockMvc.perform(get(BASE_URL+"/1"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("numPedido", equalTo(1)));
	}

	@Test
	public void teste02_deveRetornarError_BuscaProduto() throws Exception {
		this.mockMvc.perform(get(BASE_URL+"/10"))
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
		this.mockMvc.perform(post(BASE_URL+"/semProduto")
				.content("{\"id\": 2,\"dataPedido\": \"20/05/2020\",\"clienteBO\": {\"id\": 1}}")
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated())
			.andExpect(header().string("Location", is("http://localhost/pedido/semProduto/4")));
			//.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void teste05_deveRetornarSucesso_SalvarProduto() throws Exception {
		this.mockMvc.perform(post(BASE_URL+"/comProduto")
				.content("{ \"id\": 2,\"dataPedido\": \"25/05/2020\",\"clienteBO\": {\"id\": 2},\"itemPedidos\": [{\"produtoBO\": {\"id\": 1},\"qtde\": 2},{\"produtoBO\": {\"id\": 3},\"qtde\": 7}]}")
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated())
			.andExpect(header().string("Location", is("http://localhost/pedido/comProduto/5")));
			//.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void teste06_deveRetornarError_SalvarProduto() throws Exception {
		this.mockMvc.perform(post(BASE_URL+"/semProduto")
				.content("{\"id\": 2,\"dataPedido\": \"20/05/2020\",\"clienteBO\": {\"id\": 6}}")
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotFound());
			//.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void teste07_deveRetornarError_SalvarProduto() throws Exception {
		this.mockMvc.perform(post(BASE_URL+"/comProduto")
				.content("{ \"id\": 2,\"dataPedido\": \"25/05/2020\",\"clienteBO\": {\"id\": 6},\"itemPedidos\": [{\"produtoBO\": {\"id\": 1},\"qtde\": 2},{\"produtoBO\": {\"id\": 3},\"qtde\": 7}]}")
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotFound());
			//.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void teste08_deveRetornarSucesso_AtualizarProduto() throws Exception {
		this.mockMvc.perform(put(BASE_URL)
				.content("{\"id\": 3,\"dataPedido\": \"04/10/2020\",\"clienteBO\": {\"id\": 1},\"itemPedidos\": [{\"produtoBO\": {\"id\": 3},\"qtde\": 9}]}")
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isNoContent());
			//.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void teste09_deveRetornarSucesso_DeletarProdutoPorId() throws Exception {
		this.mockMvc.perform(delete(BASE_URL+"/3"))
			.andExpect(status().isNoContent());
			//.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void teste10_deveRetornarSucesso_BuscaProduto() throws Exception {
		this.mockMvc.perform(get(BASE_URL+"/numpedido/1"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("numPedido", equalTo(1)));
	}
	
	@Test
	public void teste11_deveRetornarSucesso_BuscaProduto() throws Exception {
		this.mockMvc.perform(get(BASE_URL+"/notafiscal/2"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("cpfCliente", equalTo("84978743079")));
	}
}

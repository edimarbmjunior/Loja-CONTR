package com.edimar.loja.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.edimar.loja.model.dto.ClienteBO;
import com.edimar.loja.model.dto.ValorFreteBO;
import com.edimar.loja.services.ClienteService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin
@RequestMapping(value="/cliente")
@Api(tags = "Clientes", description = "API de clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "Busca cliente pelo codigo")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Busca realizado com sucesso"),
			@ApiResponse(code = 404, message = "Não foi possível localizar"),
			@ApiResponse(code = 500, message = "Houve algum erro no processamento da informação passada")
	})
	public ResponseEntity<?> buscarClienteporId(@PathVariable Integer id) throws Exception {
		ClienteBO c = clienteService.buscarClientePorId(id);
		return ResponseEntity.ok().body(c);
	}
	
	@RequestMapping(value="/buscarCPF/{cpf}", method = RequestMethod.GET)
	@ApiOperation(value = "Busca cliente pelo cpf")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Busca realizado com sucesso"),
			@ApiResponse(code = 404, message = "Não foi possível localizar"),
			@ApiResponse(code = 500, message = "Houve algum erro no processamento da informação passada")
	})
	public ResponseEntity<?> buscarClienteporId(@PathVariable String cpf) throws Exception {
		ClienteBO c = clienteService.buscarClientePorCpf(cpf, 1);
		return ResponseEntity.ok().body(c);
	}
	
	@RequestMapping(value="/calculafrete/{cep}", method = RequestMethod.GET)
	@ApiOperation(value = "Busca valor do frete pelo CEP")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Busca realizado com sucesso"),
			@ApiResponse(code = 404, message = "Não foi possível localizar"),
			@ApiResponse(code = 500, message = "Houve algum erro no processamento da informação passada")
	})
	public ResponseEntity<?> buscarValorCep(@PathVariable Integer cep) throws Exception {
		ValorFreteBO c = clienteService.buscarValorFrete(cep);
		return ResponseEntity.ok().body(c);
	}
	
	@RequestMapping(value="/todos", method = RequestMethod.GET)
	@ApiOperation(value = "Busca clientes")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Busca realizado com sucesso"),
			@ApiResponse(code = 404, message = "Não foi possível localizar"),
			@ApiResponse(code = 500, message = "Houve algum erro no processamento da informação passada")
	})
	public ResponseEntity<List<ClienteBO>> litarClientes() {
		List<ClienteBO> pedidos = clienteService.litarClientes();
		return ResponseEntity.ok().body(pedidos);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "Inclui cliente")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Inclusão realizado com sucesso"),
			@ApiResponse(code = 500, message = "Houve algum erro no processamento da informação passada")
	})
	public ResponseEntity<Void> incluirCliente(@RequestBody ClienteBO cliente){
		Integer identificador = clienteService.incluirCliente(cliente);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(identificador)
				.toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	@ApiOperation(value = "Atualiza cliente")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Atualização realizado com sucesso"),
			@ApiResponse(code = 500, message = "Houve algum erro no processamento da informação passada")
	})
	public ResponseEntity<Void> atualizarPedido(@RequestBody ClienteBO cliente){
		clienteService.atualizarCliente(cliente);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	@ApiOperation(value = "Deleta cliente pelo identificador")
	@ApiResponses(value = {
			@ApiResponse(code = 204, message = "Exclusão realizado com sucesso"),
			@ApiResponse(code = 500, message = "Houve algum erro no processamento da informação passada")
	})
	public ResponseEntity<Void> deletarCliente(@PathVariable Integer id){
		clienteService.deletarCliente(id);
		return ResponseEntity.noContent().build();
	}
}

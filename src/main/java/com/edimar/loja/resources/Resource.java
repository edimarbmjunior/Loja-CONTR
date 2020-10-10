package com.edimar.loja.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.edimar.loja.model.Cliente;

@RestController
@RequestMapping(value="/resource")
public class Resource {

	/*
	 * @RequestMapping(method = RequestMethod.GET) public String listar() { return
	 * "Meu deus"; }
	 */

	@RequestMapping(method = RequestMethod.GET)
	public Cliente clienteTeste() {
		Cliente c = new Cliente();
		c.setCpf("00000000001");
		return c;
	}
}

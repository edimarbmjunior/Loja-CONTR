package com.edimar.loja.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ClienteBO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	private String cpf;
	private String endereco;
	private Integer cep;
	private String bairro;
	private String cidade;
	private String uf;
	private String telefeone;
	private List<PedidoBO> listPedidos = new ArrayList<>();
	
	public ClienteBO() {
		// TODO Auto-generated constructor stub
	}

	public ClienteBO(Integer id, String nome, String cpf, String endereco, Integer cep, String bairro, String cidade,
			String uf, String telefeone, List<PedidoBO> listPedidos) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.endereco = endereco;
		this.cep = cep;
		this.bairro = bairro;
		this.cidade = cidade;
		this.uf = uf;
		this.telefeone = telefeone;
		this.listPedidos = listPedidos;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Integer getCep() {
		return cep;
	}

	public void setCep(Integer cep) {
		this.cep = cep;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getTelefeone() {
		return this.telefeone;
	}

	public void setTelefeone(String telefeone) {
		this.telefeone = telefeone;
	}

	public List<PedidoBO> getListPedidos() {
		return listPedidos;
	}

	public void setListPedidos(List<PedidoBO> listPedidos) {
		this.listPedidos = listPedidos;
	}
}

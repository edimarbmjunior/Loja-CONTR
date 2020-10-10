package com.edimar.loja.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Cliente implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	@Column(unique = true)
	private String cpf;
	private String endereco;
	private Integer cep;
	private String bairro;
	private String cidade;
	private String uf;
	private String Telefeone;
	
	@OneToMany(mappedBy = "cliente")
	private List<Pedido> listPedidos = new ArrayList<>();
	
	public Cliente() {
		// TODO Auto-generated constructor stub
	}

	public Cliente(Integer id, String nome, String cpf, String endereco, Integer cep, String bairro, String cidade,
			String uf, String telefeone) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.endereco = endereco;
		this.cep = cep;
		this.bairro = bairro;
		this.cidade = cidade;
		this.uf = uf;
		Telefeone = telefeone;
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
		return Telefeone;
	}

	public void setTelefeone(String telefeone) {
		Telefeone = telefeone;
	}

	public List<Pedido> getListPedidos() {
		return listPedidos;
	}

	public void setListPedidos(List<Pedido> listPedidos) {
		this.listPedidos = listPedidos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", endereco=" + endereco + ", cep=" + cep
				+ ", bairro=" + bairro + ", cidade=" + cidade + ", uf=" + uf + ", Telefeone=" + Telefeone
				+ ", listPedidos=" + listPedidos + "]";
	}
}

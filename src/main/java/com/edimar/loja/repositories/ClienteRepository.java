package com.edimar.loja.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.edimar.loja.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

	@Query("select c from Cliente c where cpf = ?1")
	public Cliente buscarPorCpf(String cpf);
}

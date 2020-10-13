package com.edimar.loja.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.edimar.loja.model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer>{
	
	@Query("select max(p) from Pedido p order by 1 desc")
	public Pedido ultimoNumPedido();
	
	@Query("select p.id from Pedido p where numPedido = ?1 order by 1 desc")
	public Integer recuperaPeloNumPedido(Long numPedidoLong);

}

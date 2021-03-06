package br.com.ecommerceapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.ecommerceapi.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
	
	@Query(value = 
			"SELECT c "
			+ "FROM Cliente c "
			+ "JOIN FETCH c.enderecos "
			+ "WHERE c.email = :email ")
	Cliente buscarPor(String email);
	
	@Query(value = 
			"SELECT c "
			+ "FROM Cliente c "
			+ "JOIN FETCH c.enderecos ")
	List<Cliente> listarTodos();

}

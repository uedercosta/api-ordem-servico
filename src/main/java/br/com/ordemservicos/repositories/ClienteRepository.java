package br.com.ordemservicos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ordemservicos.models.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}

package br.com.ordemservicos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ordemservicos.models.OrdemServico;

public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long> {

}

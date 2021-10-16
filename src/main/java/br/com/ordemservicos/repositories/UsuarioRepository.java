package br.com.ordemservicos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ordemservicos.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}

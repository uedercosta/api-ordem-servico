package br.com.ordemservicos.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.ordemservicos.exceptions.ObjectNotFoundException;
import br.com.ordemservicos.models.Usuario;
import br.com.ordemservicos.repositories.UsuarioRepository;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@PostMapping
	public ResponseEntity<Usuario> post(@RequestBody @Valid Usuario entity){
		Usuario usuario = usuarioRepository.save(entity);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuario.getId()).toUri();
		return ResponseEntity.created(uri).body(usuario);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> findById(@PathVariable("id") Long id){
		Usuario usuario = usuarioRepository.findById(id)
						.orElseThrow(() -> new ObjectNotFoundException("Registro não encontrado..."));
		return ResponseEntity.ok(usuario);						 
	}
	
	@GetMapping
	public ResponseEntity<List<Usuario>> findAll(){
		List<Usuario> usuarios = usuarioRepository.findAll()
						.stream()
						.map(u -> Usuario.builder()
									.id(u.getId())
									.nome(u.getNome())
									.login(u.getLogin())
									.senha(u.getSenha())
									.ativo(u.isAtivo()).build())
						.collect(Collectors.toList());
		return !usuarios.isEmpty() ? ResponseEntity.ok(usuarios) : ((ResponseEntity<List<Usuario>>) ResponseEntity.noContent());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		usuarioRepository
					.findById(id)
					.map(u ->{
						usuarioRepository.delete(u);
						return Void.TYPE;
					})
					.orElseThrow(() -> new ObjectNotFoundException("Registro não encontrado..."));
		return ResponseEntity.ok().build();
					
	}

}

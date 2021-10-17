package br.com.ordemservicos.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.ordemservicos.dtos.ClienteDto;
import br.com.ordemservicos.exceptions.ObjectNotFoundException;
import br.com.ordemservicos.models.Cliente;
import br.com.ordemservicos.repositories.ClienteRepository;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteRepository repository;
	
	@PostMapping
	public ResponseEntity<Cliente> save(@RequestBody Cliente entity) {
		Cliente cliente = repository.save(entity);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId()).toUri();
		return ResponseEntity.created(uri).body(cliente);
	}
	
	@GetMapping
	public ResponseEntity<List<ClienteDto>> findAll() {
		List<ClienteDto> findAll = repository.findAll()
											 .stream()
											 .map( c -> new ClienteDto().toDto(c))
											 .collect(Collectors.toList());
		return findAll.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok().body(findAll);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ClienteDto> findById(@PathVariable Long id) {
		Cliente cliente = repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Registro não encontrado..."));
		ClienteDto dto = new ClienteDto();
		return ResponseEntity.ok().body(dto.toDto(cliente));
	}

}

package br.com.ordemservicos.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
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
	public ResponseEntity<Cliente> save(@RequestBody @Valid Cliente entity) {
		Cliente cliente = repository.save(entity);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId()).toUri();
		return ResponseEntity.created(uri).body(cliente);
	}
	
	@GetMapping
	public ResponseEntity<List<ClienteDto>> findAll() {
		List<ClienteDto> findAll = repository.findAll()
											 .stream()
											 .map( c -> new ClienteDto().toDto(c) )
											 .collect(Collectors.toList());
		return findAll.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok().body(findAll);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ClienteDto> findById(@PathVariable Long id) {
		Cliente cliente = repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Registro não encontrado..."));
		ClienteDto dto = new ClienteDto();
		return ResponseEntity.ok().body(dto.toDto(cliente));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ClienteDto> update(@PathVariable Long id, @RequestBody ClienteDto editado) {
		Cliente cliente = repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Registro não encontrado..."));
		cliente.setAtivo(editado.isAtivo());
		cliente.setCpf(editado.getCpf());
		cliente.setDataNascimento(editado.getDataNascimento());
		cliente.setNome(editado.getNome());
		cliente.setObservacoes(editado.getObservacoes());
		Cliente clienteAtualizado = repository.save(cliente);
		ClienteDto dto = new ClienteDto();
		return ResponseEntity.ok().body(dto.toDto(clienteAtualizado));
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		repository.findById(id).map(c -> {
			repository.deleteById(id);
			return Void.TYPE;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT));
		return ResponseEntity.ok().build();
	}

}

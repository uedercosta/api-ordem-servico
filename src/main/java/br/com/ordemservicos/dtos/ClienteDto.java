package br.com.ordemservicos.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.ordemservicos.models.Cliente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteDto {
	
	private Long id;	
	private String nome;
	private boolean ativo;
	private String cpf;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDateTime dataCadastro;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;
	
	private String observacoes;
	
	public ClienteDto toDto(Cliente cliente) {
		ClienteDto dto = ClienteDto.builder()
				.id(cliente.getId())
				.nome(cliente.getNome())
				.ativo(cliente.isAtivo())
				.cpf(cliente.getCpf())
				.dataNascimento(cliente.getDataNascimento())
				.observacoes(cliente.getObservacoes()).build();
		return dto;
	}

}

package br.com.ordemservicos.exceptions;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Erro {
	
	private String mensagem;
	private LocalDate data = LocalDate.now();
	
	public Erro(String mensagem) {
		this.mensagem = mensagem;
	}

	
}

package br.com.ordemservicos.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationExceptionHandler {
	
	@ExceptionHandler(ObjectNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public Erro handlerObjectNotFoundException (ObjectNotFoundException ex){
		Erro erro = new Erro();
		erro.setMensagem(ex.getMessage());
		return erro;		
	}
	
	
}

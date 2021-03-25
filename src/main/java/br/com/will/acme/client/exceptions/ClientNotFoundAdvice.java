package br.com.will.acme.client.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ClientNotFoundAdvice extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(ClientNotFound.class)
	public ResponseEntity<Object> clientNotFoundAdvice(ClientNotFound clientNotFound, WebRequest wr) {
		return super.handleExceptionInternal(clientNotFound, clientNotFound.getMessage(), new HttpHeaders(), clientNotFound.getStatus(), wr);
	}
	
}

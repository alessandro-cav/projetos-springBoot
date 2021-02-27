package br.com.api.empresa.handler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class CustomizedResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> allHandlerException(Exception ex, WebRequest request) {
		return new ResponseEntity<Object>(
				new ExceptionReponse(ex.getMessage(), request.getDescription(false), LocalDateTime.now()),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handlerMethodArgumentNotValidException(MethodArgumentNotValidException ex,
			WebRequest request) {
		return new ResponseEntity<Object>(
				new ExceptionReponse(ex.getMessage(), request.getDescription(false), LocalDateTime.now()),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(DepartamentoNotFoundException.class)
	public ResponseEntity<Object> handlerDepartamentoNotFoundException(DepartamentoNotFoundException ex,
			WebRequest request) {
		return new ResponseEntity<Object>(
				new ExceptionReponse(ex.getMessage(), request.getDescription(false), LocalDateTime.now()),
				HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(EmpregadoNotFoundException.class)
	public ResponseEntity<Object> handlerEmpregadoNotFoundException(EmpregadoNotFoundException ex, WebRequest request) {
		return new ResponseEntity<Object>(
				new ExceptionReponse(ex.getMessage(), request.getDescription(false), LocalDateTime.now()),
				HttpStatus.NOT_FOUND);
	}
}

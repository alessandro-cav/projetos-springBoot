package br.com.api.livraria.handler;

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
				new ExceptionResponse(ex.getMessage(), request.getDescription(false), LocalDateTime.now()),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handlerMethodArgumentNotValidException(MethodArgumentNotValidException ex,
			WebRequest request) {
		return new ResponseEntity<Object>(
				new ExceptionResponse(ex.getMessage(), request.getDescription(false), LocalDateTime.now()),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(EditoraNotFoundException.class)
	public ResponseEntity<Object> handlerEditoraNotFoundException(EditoraNotFoundException ex, WebRequest request) {
		return new ResponseEntity<Object>(
				new ExceptionResponse(ex.getMessage(), request.getDescription(false), LocalDateTime.now()),
				HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(LivroNotFoundException.class)
	public ResponseEntity<Object> handlerLivroNotFoundException(LivroNotFoundException ex, WebRequest request) {
		return new ResponseEntity<Object>(
				new ExceptionResponse(ex.getMessage(), request.getDescription(false), LocalDateTime.now()),
				HttpStatus.NOT_FOUND);
	}
}

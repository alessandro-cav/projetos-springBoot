package br.com.api.livraria.handler;

public class EditoraNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public EditoraNotFoundException(String message) {
		super(message);
	}

}

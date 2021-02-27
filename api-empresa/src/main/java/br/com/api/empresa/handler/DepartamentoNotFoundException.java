package br.com.api.empresa.handler;

public class DepartamentoNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DepartamentoNotFoundException(String messge) {
		super(messge);
	}
}

package br.com.api.empresa.handler;

public class EmpregadoNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EmpregadoNotFoundException(String messge) {
		super(messge);
	}
}

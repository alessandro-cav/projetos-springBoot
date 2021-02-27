package br.com.api.empresa.forum.enuns;

public enum Sexo {

	FEMININO(1, "Feminino"), MASCULINO(2, "Masculino");

	private int codigo;

	private String descricao;

	private Sexo(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
}

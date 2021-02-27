package br.com.api.empresa.forum.enuns;

public enum Sexo {

	FEMININO(1, "FEMININO"), MASCULINO(2, "MASCULINO");

	private Sexo(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	private int codigo;

	private String descricao;

	 public static Sexo getSexo(String sexo){
         for(Sexo sex :Sexo.values()){
             if(sex.getDescricao().equals(sexo.toUpperCase())){
                 return sex;
             }
         }
         return null;
     }

	public int getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

}

package br.com.api.empresa.forum.requests;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartamentoRequestDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	@NotBlank(message = "Nome é obrigatório!")
	private String nome;

	@NotBlank(message = "Sigla é obrigatorio!")
	private String sigla;

	@NotBlank(message = "Telefone é obrigatorio!")
	private String telefone;

}

package br.com.api.empresa.forum.responses;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.api.empresa.forum.enuns.Sexo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpregadoResponseDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;

	private String nome;

	private String sobrenome;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;

	private Sexo sexo;

	private String telefone;

	private String endereco;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataInicio;

}

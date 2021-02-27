package br.com.api.empresa.forum.requests;

import java.io.Serializable;
import java.time.LocalDate;

import br.com.api.empresa.forum.enuns.Sexo;
import br.com.api.empresa.forum.models.Departamento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpregadoRequestDTO implements Serializable {

	private Long id;

	private String nome;

	private String sobrenome;

	private LocalDate dataNascimento;

	private Sexo sexo;

	private String telefone;

	private String endereco;

	private LocalDate dataInicio;

	private Departamento departamento;
}

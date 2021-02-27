package br.com.api.empresa.forum.requests;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.api.empresa.forum.enuns.Sexo;
import br.com.api.empresa.forum.models.Departamento;
import br.com.api.empresa.forum.models.Empregado;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpregadoRequestDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	@NotBlank(message = "Nome é obrigatorio!")
	private String nome;

	@NotBlank(message = "Sobrenome é obrigatorio!")
	private String sobrenome;

	@NotNull(message = "Data de nascimento é obrigatoria!")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;

	@NotBlank(message = "Sexo é obrigatorio!")
	private String sexo;

	@NotBlank(message = "Telefone é obrigatorio!")
	private String telefone;

	@NotBlank(message = "Endereço é obrigatorio!")
	private String endereco;

	@NotNull(message = "Data de inicio é obrigatoria!")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataInicio;

	@NotNull(message = "idDpto é obrigatorio")
	private Long idDpto;

	public static Empregado transformaDTOEmObjeto(EmpregadoRequestDTO empregadoRequestDTO, Departamento departamento) {
		Sexo sexo = Sexo.getSexo(empregadoRequestDTO.getSexo());
		return new Empregado(empregadoRequestDTO.getId(), empregadoRequestDTO.getNome(),
				empregadoRequestDTO.getSobrenome(), empregadoRequestDTO.getDataNascimento(),
				sexo, empregadoRequestDTO.getTelefone(), empregadoRequestDTO.getEndereco(),
				empregadoRequestDTO.getDataInicio(), departamento);
	}
}

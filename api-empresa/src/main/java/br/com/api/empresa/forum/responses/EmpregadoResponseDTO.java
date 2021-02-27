package br.com.api.empresa.forum.responses;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.api.empresa.forum.enuns.Sexo;
import br.com.api.empresa.forum.models.Empregado;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpregadoResponseDTO implements Serializable {

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

	public static EmpregadoResponseDTO tranformaObjetoEmDTO(Empregado empregado) {
		return new EmpregadoResponseDTO(empregado.getId(), empregado.getNome(), empregado.getSobrenome(),
				empregado.getDataNascimento(), empregado.getSexo(), empregado.getTelefone(), empregado.getEndereco(),
				empregado.getDataInicio());
	}

}

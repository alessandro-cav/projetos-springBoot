package br.com.api.empresa.forum.responses;

import java.io.Serializable;

import br.com.api.empresa.forum.models.Departamento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartamentoResponseDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String nome;

	private String sigla;

	private String telefone;

	public static DepartamentoResponseDTO transformaObjetoEmDTO(Departamento departamento) {
		return new DepartamentoResponseDTO(departamento.getId(), departamento.getNome(), departamento.getSigla(),
				departamento.getTelefone());
	}

}

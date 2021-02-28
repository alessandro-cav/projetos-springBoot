package br.com.api.empresa.forum.responses;

import java.io.Serializable;
import java.util.List;

import br.com.api.empresa.forum.models.Departamento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartamentoEmpregadoResponseDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String nome;

	private String sigla;

	private String telefone;

	private List<EmpregadoResponseDTO> empregados;

	public static DepartamentoEmpregadoResponseDTO transformarEmDptoEmpregado(Departamento departamento,
			List<EmpregadoResponseDTO> empregadoResponseDTOs) {
		return new DepartamentoEmpregadoResponseDTO(departamento.getId(), departamento.getNome(),
				departamento.getSigla(), departamento.getTelefone(), empregadoResponseDTOs);
	}

}

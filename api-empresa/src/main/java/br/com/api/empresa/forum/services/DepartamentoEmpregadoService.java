package br.com.api.empresa.forum.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.api.empresa.forum.models.Departamento;
import br.com.api.empresa.forum.responses.DepartamentoEmpregadoResponseDTO;
import br.com.api.empresa.forum.responses.EmpregadoResponseDTO;
import br.com.api.empresa.handler.DepartamentoNotFoundException;
import br.com.api.empresa.handler.EmpregadoNotFoundException;

@Service
public class DepartamentoEmpregadoService {

	private DepartamentoService departamentoService;
	
	public DepartamentoEmpregadoService(DepartamentoService departamentoService) {
		this.departamentoService = departamentoService;
	}

	public DepartamentoEmpregadoResponseDTO buscarDepartamentoESeusEmpregados(Long id) {
		Departamento departamento = null;
		try {
			departamento = this.departamentoService.findByIdDepartamento(id);
		} catch (DepartamentoNotFoundException e) {
			throw e;
		}

		if (departamento.getEmpregado().isEmpty()) {
			throw new EmpregadoNotFoundException("Lista de empregados do " + departamento.getNome() + " esta vazia!");
		}

		List<EmpregadoResponseDTO> empregadoResponseDTOs = departamento.getEmpregado().stream()
				.map(e -> EmpregadoResponseDTO.tranformaObjetoEmDTO(e)).collect(Collectors.toList());
		DepartamentoEmpregadoResponseDTO dptoEmpregado = DepartamentoEmpregadoResponseDTO
				.transformarEmDptoEmpregado(departamento, empregadoResponseDTOs);
		return dptoEmpregado;
	}

	public DepartamentoEmpregadoResponseDTO buscarDepartamentoEDeterminadoEmpregado(Long idDpto, Long idEmp) {
		Departamento departamento = null;
		try {
			departamento = this.departamentoService.findByIdDepartamento(idDpto);
		} catch (DepartamentoNotFoundException e) {
			throw e;
		}

		if (departamento.getEmpregado().isEmpty()) {
			throw new EmpregadoNotFoundException("Lista de empregados do " + departamento.getNome() + " esta vazia!");
		}
		List<EmpregadoResponseDTO> empregadoResponseDTOs = departamento.getEmpregado().stream()
				.filter(e -> e.getId().equals(idEmp)).map(e -> EmpregadoResponseDTO.tranformaObjetoEmDTO(e))
				.collect(Collectors.toList());
		DepartamentoEmpregadoResponseDTO dptoEmpregado = DepartamentoEmpregadoResponseDTO
				.transformarEmDptoEmpregado(departamento, empregadoResponseDTOs);
		return dptoEmpregado;
	}

}

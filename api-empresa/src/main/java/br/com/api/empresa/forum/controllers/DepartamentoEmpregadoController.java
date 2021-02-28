package br.com.api.empresa.forum.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.empresa.forum.responses.DepartamentoEmpregadoResponseDTO;
import br.com.api.empresa.forum.services.DepartamentoEmpregadoService;

@RestController
public class DepartamentoEmpregadoController {

	private DepartamentoEmpregadoService departamentoEmpregadoService;

	public DepartamentoEmpregadoController(DepartamentoEmpregadoService departamentoEmpregadoService) {
		this.departamentoEmpregadoService = departamentoEmpregadoService;
	}

	@GetMapping("departamentos/{id}/empregados")
	public DepartamentoEmpregadoResponseDTO buscarDepartamentoESeusEmpregados(@PathVariable(name = "id") Long id) {
		DepartamentoEmpregadoResponseDTO departamentoEmpregadoResponseDTO = this.departamentoEmpregadoService
				.buscarDepartamentoESeusEmpregados(id);
		return departamentoEmpregadoResponseDTO;
	}

	@GetMapping("departamentos/{idDpto}/empregados/{idEmp}")
	public DepartamentoEmpregadoResponseDTO buscarDepartamentoEDeterminadoEmpregado(
			@PathVariable(name = "idDpto") Long idDpto, @PathVariable(name = "idEmp") Long idEmp) {
		DepartamentoEmpregadoResponseDTO departamentoEmpregadoResponseDTO = this.departamentoEmpregadoService
				.buscarDepartamentoEDeterminadoEmpregado(idDpto, idEmp);
		return departamentoEmpregadoResponseDTO;
	}

}

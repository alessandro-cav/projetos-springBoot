package br.com.api.empresa.forum.controllers;

import org.springframework.web.bind.annotation.RestController;

import br.com.api.empresa.forum.services.DepartamentoEmpregadoService;

@RestController
public class DepartamentoEmpregadoController {
	
	private DepartamentoEmpregadoService departamentoEmpregadoService;

	public DepartamentoEmpregadoController(DepartamentoEmpregadoService departamentoEmpregadoService) {
		this.departamentoEmpregadoService = departamentoEmpregadoService;
	}
	


}

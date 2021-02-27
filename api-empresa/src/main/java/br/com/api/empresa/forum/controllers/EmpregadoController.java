package br.com.api.empresa.forum.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.empresa.forum.services.EmpregadoService;


@RestController
@RequestMapping("/empregados")
public class EmpregadoController {

	private EmpregadoService empregadoService;

	public EmpregadoController(EmpregadoService empregadoService) {
		this.empregadoService = empregadoService;
	}

	
}

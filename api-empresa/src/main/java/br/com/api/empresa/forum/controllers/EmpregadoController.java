package br.com.api.empresa.forum.controllers;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.api.empresa.forum.models.Empregado;
import br.com.api.empresa.forum.requests.EmpregadoRequestDTO;
import br.com.api.empresa.forum.services.EmpregadoService;


@RestController
@RequestMapping("/empregados")
public class EmpregadoController {

	private EmpregadoService empregadoService;

	public EmpregadoController(EmpregadoService empregadoService) {
		this.empregadoService = empregadoService;
	}
	
	@PostMapping
	public ResponseEntity<Empregado> save(@Valid @RequestBody EmpregadoRequestDTO empregadoRequestDTO){
		Empregado empregado = this.empregadoService.save(empregadoRequestDTO);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(empregado.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
}

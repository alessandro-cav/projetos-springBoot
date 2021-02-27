package br.com.api.empresa.forum.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.api.empresa.forum.models.Departamento;
import br.com.api.empresa.forum.requests.DepartamentoRequestDTO;
import br.com.api.empresa.forum.responses.DepartamentoResponseDTO;
import br.com.api.empresa.forum.services.DepartamentoService;


@RestController
@RequestMapping("/departamentos")
public class DepartamentoController {

	private DepartamentoService departamentoService;

	public DepartamentoController(DepartamentoService departamentoService) {
		this.departamentoService = departamentoService;
	}

	@PostMapping
	public ResponseEntity<Departamento> save(@Valid @RequestBody DepartamentoRequestDTO departamentoRequestDTO) {
		Departamento departamento = this.departamentoService.save(departamentoRequestDTO);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(departamento.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<DepartamentoResponseDTO> findById(@PathVariable(name = "id") Long id) {
		DepartamentoResponseDTO departamentoResponseDTO = this.departamentoService.findById(id);
		return ResponseEntity.ok(departamentoResponseDTO);
	}
	
	@GetMapping
	public ResponseEntity<List<DepartamentoResponseDTO>> findAll(){
		List<DepartamentoResponseDTO> departamentoResponseDTOs = this.departamentoService.findAll();
		return ResponseEntity.ok(departamentoResponseDTOs);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable(name = "id") Long id){
		this.departamentoService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<DepartamentoResponseDTO> update(@Valid @RequestBody  DepartamentoRequestDTO departamentoRequestDTO, @PathVariable(name = "id") Long id){
		DepartamentoResponseDTO departamentoResponseDTO =  this.departamentoService.update(departamentoRequestDTO, id);
		return ResponseEntity.ok(departamentoResponseDTO);
	}
	
	@GetMapping("/consultas")
	public ResponseEntity<List<DepartamentoResponseDTO>> consultas(Departamento filtro){
		ExampleMatcher exampleMatcher=  ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING);
		Example<Departamento> example =  Example.of(filtro, exampleMatcher);
		List<DepartamentoResponseDTO> departamentoResponseDTOs = this.departamentoService.findAll(example);
		return ResponseEntity.ok(departamentoResponseDTOs);
	}
}

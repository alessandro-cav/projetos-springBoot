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

import br.com.api.empresa.forum.models.Empregado;
import br.com.api.empresa.forum.requests.EmpregadoRequestDTO;
import br.com.api.empresa.forum.responses.EmpregadoResponseDTO;
import br.com.api.empresa.forum.services.EmpregadoService;

@RestController
@RequestMapping("/empregados")
public class EmpregadoController {

	private EmpregadoService empregadoService;

	public EmpregadoController(EmpregadoService empregadoService) {
		this.empregadoService = empregadoService;
	}

	@PostMapping
	public ResponseEntity<Empregado> save(@Valid @RequestBody EmpregadoRequestDTO empregadoRequestDTO) {
		Empregado empregado = this.empregadoService.save(empregadoRequestDTO);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(empregado.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<EmpregadoResponseDTO> findById(@PathVariable(name = "id") Long id) {
		EmpregadoResponseDTO empregadoResponseDTO = this.empregadoService.findById(id);
		return ResponseEntity.ok(empregadoResponseDTO);
	}

	@GetMapping
	public ResponseEntity<List<EmpregadoResponseDTO>> findAll() {
		List<EmpregadoResponseDTO> empregadoResponseDTOs = this.empregadoService.findAll();
		return ResponseEntity.ok(empregadoResponseDTOs);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable(name = "id") Long id) {
		this.empregadoService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<EmpregadoResponseDTO> update(@Valid @RequestBody EmpregadoRequestDTO empregadoRequestDTO,
			@PathVariable(name = "id") Long id) {
		EmpregadoResponseDTO empregadoResponseDTO = this.empregadoService.update(empregadoRequestDTO, id);
		return ResponseEntity.ok(empregadoResponseDTO);
	}
	
	@GetMapping("/consultas")
	public ResponseEntity<List<EmpregadoResponseDTO>> consultas(Empregado filtro){
		ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING);
		Example<Empregado> example =  Example.of(filtro, exampleMatcher);
		List<EmpregadoResponseDTO> empregadoResponseDTOs =  this.empregadoService.findAll(example);
		return ResponseEntity.ok(empregadoResponseDTOs);
	}

}

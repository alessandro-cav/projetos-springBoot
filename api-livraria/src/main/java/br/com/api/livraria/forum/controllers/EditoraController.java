package br.com.api.livraria.forum.controllers;

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

import br.com.api.livraria.forum.models.Editora;
import br.com.api.livraria.forum.requests.EditoraRequestDTO;
import br.com.api.livraria.forum.responses.EditoraResponseDTO;
import br.com.api.livraria.forum.services.EditoraService;

@RestController
@RequestMapping("/editoras")
public class EditoraController {

	private EditoraService editoraService;

	public EditoraController(EditoraService editoraService) {
		this.editoraService = editoraService;
	}

	@PostMapping
	public ResponseEntity<Editora> save(@Valid @RequestBody EditoraRequestDTO editoraRequestDTO) {
		Editora editora = this.editoraService.save(editoraRequestDTO);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(editora.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<EditoraResponseDTO> finDById(@PathVariable(name = "id") Long id) {
		EditoraResponseDTO editoraResponseDTO = this.editoraService.findById(id);
		return ResponseEntity.ok(editoraResponseDTO);
	}
	
	@GetMapping
	public ResponseEntity<List<EditoraResponseDTO>> findAll(){
		List<EditoraResponseDTO> editoraResponseDTOs = this.editoraService.findAll();
		return ResponseEntity.ok(editoraResponseDTOs);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable(name = "id") Long id){
		this.editoraService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<EditoraResponseDTO> update(@PathVariable(name = "id") Long id, @Valid @RequestBody EditoraRequestDTO editoraRequestDTO){
		EditoraResponseDTO editoraResponseDTO = this.editoraService.update(id, editoraRequestDTO);
		return ResponseEntity.ok(editoraResponseDTO);
	}
	
	@GetMapping("/consultas")
	public ResponseEntity<List<EditoraResponseDTO>> consultas(Editora filtro){
		ExampleMatcher exampleMatcher =  ExampleMatcher.matching().withStringMatcher(StringMatcher.CONTAINING);
		Example<Editora> example = Example.of(filtro, exampleMatcher);
		List<EditoraResponseDTO>editoraResponseDTOs =  this.editoraService.findAll(example);
		return ResponseEntity.ok(editoraResponseDTOs);
	}
}

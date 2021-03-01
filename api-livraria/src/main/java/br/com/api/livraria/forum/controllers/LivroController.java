package br.com.api.livraria.forum.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

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

import br.com.api.livraria.forum.models.Livro;
import br.com.api.livraria.forum.requests.LivroRequestDTO;
import br.com.api.livraria.forum.responses.LivroResponseDTO;
import br.com.api.livraria.forum.services.LivroService;

@RestController
@RequestMapping("/livros")
public class LivroController {

	private LivroService  livroService;

	public LivroController(LivroService livroService) {
		this.livroService = livroService;
	}

	@PostMapping
	public ResponseEntity<Livro> save (@Valid @RequestBody LivroRequestDTO livroRequestDTO){
		Livro livro =  this.livroService.save(livroRequestDTO);
		URI location =  ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(livro.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<LivroResponseDTO> findById(@PathVariable(name = "id") Long id){
		LivroResponseDTO livroResponseDTO = this.livroService.findById(id);
		return ResponseEntity.ok(livroResponseDTO);
	}
	
	@GetMapping
	public ResponseEntity<List<LivroResponseDTO>> findAll(){
		List<LivroResponseDTO> livroResponseDTOs =  this.livroService.findAll();
		return ResponseEntity.ok(livroResponseDTOs);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable(name = "id") Long id){
		this.livroService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<LivroResponseDTO> update(@PathVariable(name = "id") Long id, @Valid @RequestBody LivroRequestDTO livroRequestDTO ){
		LivroResponseDTO livroResponseDTO = this.livroService.update(id, livroRequestDTO);
		return ResponseEntity.ok(livroResponseDTO);
	}
}

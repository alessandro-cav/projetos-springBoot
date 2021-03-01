package br.com.api.livraria.forum.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.livraria.forum.responses.EditoraLivroResponseDTO;
import br.com.api.livraria.forum.services.EditoraLivroService;

@RestController
public class EditoraLivroController {

	private EditoraLivroService editoraLivroService;

	public EditoraLivroController(EditoraLivroService editoraLivroService) {
		this.editoraLivroService = editoraLivroService;
	}

	@GetMapping("/editoras/{id}/livros")
	public ResponseEntity<EditoraLivroResponseDTO> buscarLivrosPelaEditora(@PathVariable(name = "id") Long id){
		EditoraLivroResponseDTO editoraLivroResponseDTO = this.editoraLivroService.buscarLivrosPelaEditora(id);
		return ResponseEntity.ok(editoraLivroResponseDTO);
	}
	

	@GetMapping("/editoras/{idEditora}/livros/{idLivro}")
	public ResponseEntity<EditoraLivroResponseDTO> buscarLivroEditora(@PathVariable(name = "idEditora") Long idEditora, @PathVariable(name = "idLivro") Long idLivro){
		EditoraLivroResponseDTO editoraLivroResponseDTO = this.editoraLivroService.buscarLivroEditora(idEditora,idLivro);
		return ResponseEntity.ok(editoraLivroResponseDTO);
	}
	
}

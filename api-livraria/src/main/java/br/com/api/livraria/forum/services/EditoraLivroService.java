package br.com.api.livraria.forum.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.api.livraria.forum.models.Editora;
import br.com.api.livraria.forum.responses.EditoraLivroResponseDTO;
import br.com.api.livraria.forum.responses.LivroResponseDTO;
import br.com.api.livraria.handler.EditoraNotFoundException;
import br.com.api.livraria.handler.LivroNotFoundException;

@Service
public class EditoraLivroService {

	private EditoraService editoraService;
	
	public EditoraLivroService(EditoraService editoraService) {
		this.editoraService = editoraService;
	}

	public EditoraLivroResponseDTO buscarLivrosPelaEditora(Long id) {
		Editora editora = null;
		try {
			editora = this.editoraService.findByIdEditora(id);
		} catch (EditoraNotFoundException e) {
			throw e;
		}
		if(!editora.getLivros().isEmpty()) {
			throw new LivroNotFoundException("Lista de livros esta vazia!");
		}
		List<LivroResponseDTO> livroResponseDTOs = editora.getLivros().stream()
				.map(l -> LivroResponseDTO.transformarObjetoEmDTO(l)).collect(Collectors.toList());
		EditoraLivroResponseDTO editoraLivroResponseDTO = EditoraLivroResponseDTO.transformaEmDTO(editora, livroResponseDTOs);
		return editoraLivroResponseDTO;
	}

	public EditoraLivroResponseDTO buscarLivroEditora(Long idEditora, Long idLivro) {
		Editora editora = null;
		try {
			editora = this.editoraService.findByIdEditora(idEditora);
		} catch (EditoraNotFoundException e) {
			throw e;
		}
		if (editora.getLivros().isEmpty()) {
			throw new LivroNotFoundException("Lista de livros esta vazia!");
		}
		List<LivroResponseDTO> livroResponseDTOs = editora.getLivros().stream()
				.filter(l -> l.getId().equals(idLivro)).map(l -> LivroResponseDTO.transformarObjetoEmDTO(l))
				.collect(Collectors.toList());
		EditoraLivroResponseDTO editoraLivroResponseDTO = EditoraLivroResponseDTO.transformaEmDTO(editora, livroResponseDTOs);
		return editoraLivroResponseDTO;
	}

}

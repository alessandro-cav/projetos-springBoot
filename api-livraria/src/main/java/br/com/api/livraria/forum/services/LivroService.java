package br.com.api.livraria.forum.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import br.com.api.livraria.forum.models.Editora;
import br.com.api.livraria.forum.models.Livro;
import br.com.api.livraria.forum.repositorys.LivroRepository;
import br.com.api.livraria.forum.requests.LivroRequestDTO;
import br.com.api.livraria.forum.responses.LivroResponseDTO;
import br.com.api.livraria.handler.EditoraNotFoundException;
import br.com.api.livraria.handler.LivroNotFoundException;

@Service
public class LivroService {

	@Qualifier("livroRepository")
	private LivroRepository livroRepository;

	@Qualifier("editoraService")
	private EditoraService editoraService;

	public LivroService(LivroRepository livroRepository, EditoraService editoraService) {
		this.livroRepository = livroRepository;
		this.editoraService = editoraService;
	}

	public Livro findByIdLivro(Long id) {
		Optional<Livro> livro = this.livroRepository.findById(id);
		if (!livro.isPresent()) {
			throw new LivroNotFoundException("Livro nao encontrado com esse codigo " + id);
		}
		return livro.get();
	}

	// CRUD
	public Livro save(@Valid LivroRequestDTO livroRequestDTO) {
		Editora editora = null;
		try {
			editora = this.editoraService.findByIdEditora(livroRequestDTO.getIdEditora());
		} catch (EditoraNotFoundException e) {
			throw e;
		}
		Livro livro = LivroRequestDTO.tranformarDTOEmObjeto(livroRequestDTO, editora);
		livro = this.livroRepository.save(livro);
		return livro;
	}

	public LivroResponseDTO findById(Long id) {
		Livro livro = null;
		try {
			livro = this.findByIdLivro(id);
		} catch (LivroNotFoundException e) {
			throw e;
		}
		LivroResponseDTO livroResponseDTO = LivroResponseDTO.transformarObjetoEmDTO(livro);
		return livroResponseDTO;
	}

	public List<LivroResponseDTO> findAll() {
		List<Livro> livros = this.livroRepository.findAll();
		if(livros.isEmpty()) {
			throw new LivroNotFoundException("Lista de livros esta vazia!");
		}
		List<LivroResponseDTO> livroResponseDTOs = livros.stream().map(l -> LivroResponseDTO.transformarObjetoEmDTO(l))
				.collect(Collectors.toList());
		return livroResponseDTOs;
	}

	public void delete(Long id) {
		Livro livro = null;
		try {
			livro = this.findByIdLivro(id);
		} catch (LivroNotFoundException e) {
			throw e;
		}
		this.livroRepository.delete(livro);
	}

	public LivroResponseDTO update(Long id, LivroRequestDTO livroRequestDTO) {
		Livro livro = null;
		Editora editora = null;
		try {
			editora = this.editoraService.findByIdEditora(livroRequestDTO.getIdEditora());
			livro = this.findByIdLivro(id);
		} catch (EditoraNotFoundException e) {
			throw e;
		}catch (LivroNotFoundException e) {
			throw e;
		}
		livro.setNome(livroRequestDTO.getNome());
		livro.setIsbn(livroRequestDTO.getIsbn());
		livro.setDataLancamento(livroRequestDTO.getDataLancamento());
		livro.setEditora(editora);
	
		livro = this.livroRepository.save(livro);
		LivroResponseDTO livroResponseDTO = LivroResponseDTO.transformarObjetoEmDTO(livro);
		return livroResponseDTO;
	}

	public List<LivroResponseDTO> findAll(Example<Livro> example) {
		List<Livro> livros = this.livroRepository.findAll(example);
		if(livros.isEmpty()) {
			throw new LivroNotFoundException("Lista de livros esta vazia!");
		}
		List<LivroResponseDTO> livroResponseDTOs = livros.stream().map(l -> LivroResponseDTO.transformarObjetoEmDTO(l))
				.collect(Collectors.toList());
		return livroResponseDTOs;
	}

}

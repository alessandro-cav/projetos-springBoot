package br.com.api.livraria.forum.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import br.com.api.livraria.forum.models.Editora;
import br.com.api.livraria.forum.repositorys.EditoraRepository;
import br.com.api.livraria.forum.requests.EditoraRequestDTO;
import br.com.api.livraria.forum.responses.EditoraResponseDTO;
import br.com.api.livraria.handler.EditoraNotFoundException;

@Service
public class EditoraService {

	private EditoraRepository editoraRepository;

	public EditoraService(EditoraRepository editoraRepository) {
		this.editoraRepository = editoraRepository;
	}

	public Editora findByIdEditora(Long id) {
		Optional<Editora> editora = this.editoraRepository.findById(id);
		if (!editora.isPresent()) {
			throw new EditoraNotFoundException("editora nao encontrada com esse numero " + id);
		}
		return editora.get();
	}

	// CRUD
	public Editora save(EditoraRequestDTO editoraRequestDTO) {
		Editora editora = EditoraRequestDTO.transformaDTOEmObjeto(editoraRequestDTO);
		editora = this.editoraRepository.save(editora);
		return editora;
	}

	public EditoraResponseDTO findById(Long id) {
		Editora editora = null;
		try {
			editora = this.findByIdEditora(id);
		} catch (EditoraNotFoundException e) {
			throw e;
		}
		EditoraResponseDTO editoraResponseDTO = EditoraResponseDTO.transformaObjetoEmDTO(editora);
		return editoraResponseDTO;
	}

	public List<EditoraResponseDTO> findAll() {
		List<Editora> editoras = this.editoraRepository.findAll();
		if (editoras.isEmpty()) {
			throw new EditoraNotFoundException("Lista de editoras está vazia!");
		}
		List<EditoraResponseDTO> editoraResponseDTOs = editoras.stream()
				.map(e -> EditoraResponseDTO.transformaObjetoEmDTO(e)).collect(Collectors.toList());
		return editoraResponseDTOs;
	}

	public void delete(Long id) {
		Editora editora = null;
		try {
			editora = this.findByIdEditora(id);
		} catch (EditoraNotFoundException e) {
			throw e;
		}
		this.editoraRepository.delete(editora);
	}

	public EditoraResponseDTO update(Long id, EditoraRequestDTO editoraRequestDTO) {
		Editora editora = null;
		try {
			editora = this.findByIdEditora(id);
		} catch (EditoraNotFoundException e) {
			throw e;
		}
		editora.setCodEditora(editoraRequestDTO.getCodEditora());
		editora.setContato(editoraRequestDTO.getContato());
		editora.setEndereco(editoraRequestDTO.getEndereco());
		editora.setFantasia(editoraRequestDTO.getFantasia());
		editora.setRazaoSocial(editoraRequestDTO.getRazaoSocial());
		editora.setTelefone(editora.getTelefone());
		
		editora =  this.editoraRepository.saveAndFlush(editora);
		EditoraResponseDTO editoraResponseDTO = EditoraResponseDTO.transformaObjetoEmDTO(editora);
		return editoraResponseDTO;
	}

	public List<EditoraResponseDTO> findAll(Example<Editora> example) {
		List<Editora> editoras = this.editoraRepository.findAll(example);
		if (editoras.isEmpty()) {
			throw new EditoraNotFoundException("Lista de editoras está vazia!");
		}
		List<EditoraResponseDTO> editoraResponseDTOs = editoras.stream()
				.map(e -> EditoraResponseDTO.transformaObjetoEmDTO(e)).collect(Collectors.toList());
		return editoraResponseDTOs;
	}
}

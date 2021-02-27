package br.com.api.empresa.forum.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import br.com.api.empresa.forum.models.Departamento;
import br.com.api.empresa.forum.repositorys.DepartamentoRepository;
import br.com.api.empresa.forum.requests.DepartamentoRequestDTO;
import br.com.api.empresa.forum.responses.DepartamentoResponseDTO;
import br.com.api.empresa.handler.DepartamentoNotFoundException;

@Service
public class DepartamentoService {

	private DepartamentoRepository departamentoRepository;

	public DepartamentoService(DepartamentoRepository departamentoRepository) {
		this.departamentoRepository = departamentoRepository;
	}

	private Departamento findByIdDepartamento(Long id) {
		Optional<Departamento> dpto = this.departamentoRepository.findById(id);
		if (!dpto.isPresent()) {
			throw new DepartamentoNotFoundException("Departamento nao encontrado com esse codigo " + id);
		}
		return dpto.get();
	}

	// CRUD
	public Departamento save(DepartamentoRequestDTO departamentoRequestDTO) {
		Departamento departamento = DepartamentoRequestDTO.transformarDTOEmObjeto(departamentoRequestDTO);
		departamento = this.departamentoRepository.save(departamento);
		return departamento;
	}

	public DepartamentoResponseDTO findById(Long id) {
		Departamento departamento = null;
		try {
			departamento = this.findByIdDepartamento(id);
		} catch (DepartamentoNotFoundException e) {
			throw e;
		}
		DepartamentoResponseDTO departamentoResponseDTO = DepartamentoResponseDTO.transformaObjetoEmDTO(departamento);
		return departamentoResponseDTO;
	}

	public List<DepartamentoResponseDTO> findAll() {
		List<Departamento> departamentos = this.departamentoRepository.findAll();
		if (departamentos.isEmpty()) {
			throw new DepartamentoNotFoundException("Lista de departamentos está vazia!");
		}
		List<DepartamentoResponseDTO> departamentoResponseDTOs = departamentos.stream()
				.map(d -> DepartamentoResponseDTO.transformaObjetoEmDTO(d)).collect(Collectors.toList());
		return departamentoResponseDTOs;
	}

	public void delete(Long id) {
		Departamento departamento = null;
		try {
			departamento = this.findByIdDepartamento(id);
		} catch (DepartamentoNotFoundException e) {
			throw e;
		}
		this.departamentoRepository.delete(departamento);
	}

	public DepartamentoResponseDTO update(DepartamentoRequestDTO departamentoRequestDTO, Long id) {
		Departamento departamento = null;
		try {
			departamento = this.findByIdDepartamento(id);
		} catch (DepartamentoNotFoundException e) {
			throw e;
		}
		departamento.setNome(departamentoRequestDTO.getNome());
		departamento.setSigla(departamentoRequestDTO.getSigla());
		departamento.setTelefone(departamentoRequestDTO.getTelefone());
		departamento = this.departamentoRepository.saveAndFlush(departamento);
		DepartamentoResponseDTO departamentoResponseDTO = DepartamentoResponseDTO.transformaObjetoEmDTO(departamento);
		return departamentoResponseDTO;
	}

	public List<DepartamentoResponseDTO> findAll(Example<Departamento> example) {
		List<Departamento> departamentos = this.departamentoRepository.findAll(example);
		if (departamentos.isEmpty()) {
			throw new DepartamentoNotFoundException(
					"lista de departamento esta vazia com esse dados de consulta ");
		}
		List<DepartamentoResponseDTO> departamentoResponseDTOs = departamentos.stream()
				.map(d -> DepartamentoResponseDTO.transformaObjetoEmDTO(d)).collect(Collectors.toList());
		return departamentoResponseDTOs;
	}

}

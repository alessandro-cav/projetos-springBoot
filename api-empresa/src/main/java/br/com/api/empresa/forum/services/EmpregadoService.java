package br.com.api.empresa.forum.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import br.com.api.empresa.forum.enuns.Sexo;
import br.com.api.empresa.forum.models.Departamento;
import br.com.api.empresa.forum.models.Empregado;
import br.com.api.empresa.forum.repositorys.EmpregadoRepository;
import br.com.api.empresa.forum.requests.EmpregadoRequestDTO;
import br.com.api.empresa.forum.responses.EmpregadoResponseDTO;
import br.com.api.empresa.handler.DepartamentoNotFoundException;
import br.com.api.empresa.handler.EmpregadoNotFoundException;

@Service
public class EmpregadoService {

	@Qualifier("empregadoRepository")
	private EmpregadoRepository empregadoRepository;
	
	@Qualifier("departamentoService")
	private DepartamentoService departamentoService;

	public EmpregadoService(EmpregadoRepository empregadoRepository, DepartamentoService departamentoService) {
		this.empregadoRepository = empregadoRepository;
		this.departamentoService = departamentoService;
	}

	private Empregado findByIdEmpregado(Long id) {
		Optional<Empregado> empregado = this.empregadoRepository.findById(id);
		if (!empregado.isPresent()) {
			throw new EmpregadoNotFoundException("Empregado nao encontrado com esse codigo " + id);
		}
		return empregado.get();
	}

	// CRUD
	public Empregado save(EmpregadoRequestDTO empregadoRequestDTO) {
		Departamento departamento = null;
		try {
			departamento = this.departamentoService.findByIdDepartamento(empregadoRequestDTO.getIdDpto());
		} catch (DepartamentoNotFoundException e) {
			throw e;
		}
		Empregado empregado = EmpregadoRequestDTO.transformaDTOEmObjeto(empregadoRequestDTO, departamento);
		empregado = this.empregadoRepository.save(empregado);
		return empregado;
	}

	public EmpregadoResponseDTO findById(Long id) {
		Empregado empregado = null;
		try {
			empregado = this.findByIdEmpregado(id);
		} catch (EmpregadoNotFoundException e) {
			throw e;
		}
		EmpregadoResponseDTO empregadoResponseDTO = EmpregadoResponseDTO.tranformaObjetoEmDTO(empregado);
		return empregadoResponseDTO;
	}

	public List<EmpregadoResponseDTO> findAll() {
		List<Empregado> empregados = this.empregadoRepository.findAll();
		List<EmpregadoResponseDTO> empregadoResponseDTOs = empregados.stream()
				.map(e -> EmpregadoResponseDTO.tranformaObjetoEmDTO(e)).collect(Collectors.toList());
		return empregadoResponseDTOs;
	}

	public void delete(Long id) {
		Empregado empregado = null;
		try {
			empregado = this.findByIdEmpregado(id);
		} catch (EmpregadoNotFoundException e) {
			throw e;
		}
		this.empregadoRepository.delete(empregado);
	}

	public EmpregadoResponseDTO update(EmpregadoRequestDTO empregadoRequestDTO, Long id) {
		Departamento departamento = null;
		Empregado empregado = null;
		try {
			empregado = this.findByIdEmpregado(id);
			departamento = this.departamentoService.findByIdDepartamento(empregadoRequestDTO.getIdDpto());
		} catch (DepartamentoNotFoundException e) {
			throw e;
		} catch (EmpregadoNotFoundException e) {
			throw e;
		}
		empregado.setNome(empregadoRequestDTO.getNome());
		empregado.setSobrenome(empregadoRequestDTO.getSobrenome());
		empregado.setTelefone(empregadoRequestDTO.getTelefone());
		empregado.setDataInicio(empregadoRequestDTO.getDataInicio());
		empregado.setDataNascimento(empregadoRequestDTO.getDataNascimento());
		empregado.setEndereco(empregadoRequestDTO.getEndereco());
		Sexo sexo = Sexo.getSexo(empregadoRequestDTO.getSexo());
		empregado.setSexo(sexo);
		empregado.setDepartamento(departamento);

		empregado = this.empregadoRepository.saveAndFlush(empregado);
		EmpregadoResponseDTO empregadoResponseDTO = EmpregadoResponseDTO.tranformaObjetoEmDTO(empregado);
		return empregadoResponseDTO;
	}

	public List<EmpregadoResponseDTO> findAll(Example<Empregado> example) {
		List<Empregado> empregados = this.empregadoRepository.findAll(example);
		List<EmpregadoResponseDTO> empregadoResponseDTOs = empregados.stream()
				.map(e -> EmpregadoResponseDTO.tranformaObjetoEmDTO(e)).collect(Collectors.toList());
		return empregadoResponseDTOs;
	}

}

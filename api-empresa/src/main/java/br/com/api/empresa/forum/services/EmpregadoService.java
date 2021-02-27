package br.com.api.empresa.forum.services;

import org.springframework.stereotype.Service;

import br.com.api.empresa.forum.models.Departamento;
import br.com.api.empresa.forum.models.Empregado;
import br.com.api.empresa.forum.repositorys.EmpregadoRepository;
import br.com.api.empresa.forum.requests.EmpregadoRequestDTO;
import br.com.api.empresa.handler.DepartamentoNotFoundException;

@Service
public class EmpregadoService {

	private EmpregadoRepository empregadoRepository;
	private DepartamentoService departamentoService;
	
	public EmpregadoService(EmpregadoRepository empregadoRepository, DepartamentoService departamentoService) {
		this.empregadoRepository = empregadoRepository;
		this.departamentoService = departamentoService;
	}

	public Empregado save(EmpregadoRequestDTO empregadoRequestDTO) {
		Departamento departamento = null; 
		try {
			departamento = this.departamentoService.findByIdDepartamento(empregadoRequestDTO.getIdDpto());
		} catch (DepartamentoNotFoundException e) {
			throw e;
		}
		Empregado  empregado =  EmpregadoRequestDTO.transformaDTOEmObjeto(empregadoRequestDTO, departamento);
		empregado =  this.empregadoRepository.save(empregado);
		return empregado;
	}

}

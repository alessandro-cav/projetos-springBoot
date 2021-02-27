package br.com.api.empresa.forum.services;

import org.springframework.stereotype.Service;

import br.com.api.empresa.forum.enuns.Sexo;
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

	public Empregado save( EmpregadoRequestDTO empregadoRequestDTO) {
		Departamento departamento = null; 
		Sexo sexo = null;
		try {
			
			 sexo = Sexo.getSexo(empregadoRequestDTO.getSexo());
			departamento = this.departamentoService.findByIdDepartamento(empregadoRequestDTO.getIdDpto());
		} catch (DepartamentoNotFoundException e) {
			throw e;
		}catch (Exception e) {
			throw new RuntimeException("Sexo escolhido nao existe!");
		}
		Empregado  empregado =  EmpregadoRequestDTO.transformaDTOEmObjeto(empregadoRequestDTO, departamento, sexo);
		empregado =  this.empregadoRepository.save(empregado);
		return empregado;
	}

}

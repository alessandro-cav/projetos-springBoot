package br.com.api.empresa.forum.services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class DepartamentoEmpregadoService {

	@Qualifier("departamentoService")
	private DepartamentoService departamentoService;
	
	@Qualifier("empregadoService")
	private EmpregadoService empregadoService;

	

	
}

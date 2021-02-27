package br.com.api.empresa.forum.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.api.empresa.forum.models.Departamento;

public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {

}

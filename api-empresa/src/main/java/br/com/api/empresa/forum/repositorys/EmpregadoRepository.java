package br.com.api.empresa.forum.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.api.empresa.forum.models.Empregado;

public interface EmpregadoRepository extends JpaRepository<Empregado, Long> {

}

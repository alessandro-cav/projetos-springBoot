package br.com.api.livraria.forum.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.api.livraria.forum.models.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long>{

}

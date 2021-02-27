package br.com.api.empresa.forum.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "DEPARTAMENTO")
public class Departamento implements Serializable {

	public Departamento(Long id, String nome, String sigla, String telefone) {
		this.id = id;
		this.nome = nome;
		this.sigla = sigla;
		this.telefone = telefone;
	}

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_DPTO")
	private Long id;

	@Column(name = "NOME_DPTO")
	private String nome;

	@Column(name = "SIGLA_DPTO")
	private String sigla;

	@Column(name = "TELEFONE")
	private String telefone;

	@OneToMany(mappedBy = "departamento")
	private List<Empregado> empregado;
}

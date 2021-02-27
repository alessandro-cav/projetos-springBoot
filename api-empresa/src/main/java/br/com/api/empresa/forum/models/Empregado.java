package br.com.api.empresa.forum.models;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.api.empresa.forum.enuns.Sexo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "EMPREGADO")
public class Empregado implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_EMPREGADO")
	private Long id;
	
	@Column(name = "NOME")
	private String nome;
	
	@Column(name = "SOBRENOME")
	private String sobrenome;
	
	@Column(name = "DATA_NASCIMENTO")
	private LocalDate dataNascimento;
	
	@Column(name = "SEXO")
	private Sexo sexo;
	
	@Column(name = "TELEFONE")
	private String telefone;
	
	@Column(name = "ENDERECO")
	private String endereco;
	
	@Column(name = "DATA_INICIO")
	private LocalDate dataInicio;
	
	@ManyToOne
	@JoinColumn(name = "ID_DPTO")
	private Departamento departamento;
}

package br.com.api.livraria.forum.models;

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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Livro")
public class Livro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_LIVRO")
	private Long id;
	
	@Column(name = "NM_LIVRO")
	private String nome;
	
	@Column(name = "ISBN")
	private String isbn;
	
	@Column(name = "DATA_LANCAMENTO")
	private LocalDate dataLancamento;
	
	@ManyToOne
	@JoinColumn(name = "ID_EDITORA")
	private Editora editora;
}

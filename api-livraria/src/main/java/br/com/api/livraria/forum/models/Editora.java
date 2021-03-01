package br.com.api.livraria.forum.models;

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
@Table(name = "EDITORA")
public class Editora implements Serializable {

	public Editora(Long id, String codEditora, String razaoSocial, String fantasia,String telefone,  String contato, String endereco) {
		this.id = id;
		this.codEditora = codEditora;
		this.razaoSocial = razaoSocial;
		this.fantasia = fantasia;
		this.telefone =  telefone;
		this.contato = contato;
		this.endereco =  endereco;
	}

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_EDITORA")
	private Long id;

	@Column(name = "COD_EDITORA")
	private String codEditora;

	@Column(name = "RAZAO_SOCIAL")
	private String razaoSocial;

	@Column(name = "FANTASIA")
	private String fantasia;

	@Column(name = "TELEFONE")
	private String telefone;

	@Column(name = "CONTATO")
	private String contato;

	@Column(name = "ENDERECO")
	private String endereco;

	@OneToMany(mappedBy = "editora")
	private List<Livro> livros;
}

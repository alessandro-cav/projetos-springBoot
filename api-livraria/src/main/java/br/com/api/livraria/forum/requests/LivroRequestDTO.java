package br.com.api.livraria.forum.requests;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.api.livraria.forum.models.Editora;
import br.com.api.livraria.forum.models.Livro;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LivroRequestDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	@NotBlank(message = "Nome é obrigatorio!")
	private String nome;

	@NotBlank(message = "ISBN  é obrigatorio!")
	private String isbn;
	
	@NotNull(message = "Data de lançamento  é obrigatoria!")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataLancamento;

	@NotNull(message = "Codigo de edtiroa  é obrigatorio!")
	private Long idEditora;

	public static Livro tranformarDTOEmObjeto(LivroRequestDTO livroRequestDTO, Editora editora) {
		return new Livro(livroRequestDTO.getId(), livroRequestDTO.getNome(), livroRequestDTO.getIsbn(),
				livroRequestDTO.getDataLancamento(), editora);
	}

}

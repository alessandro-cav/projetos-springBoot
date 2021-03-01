package br.com.api.livraria.forum.responses;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.api.livraria.forum.models.Livro;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LivroResponseDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String nome;

	private String isbn;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataLancamento;

	public static LivroResponseDTO transformarObjetoEmDTO(Livro livro) {
		return new LivroResponseDTO(livro.getId(), livro.getNome(), livro.getIsbn(), livro.getDataLancamento());
	}

}

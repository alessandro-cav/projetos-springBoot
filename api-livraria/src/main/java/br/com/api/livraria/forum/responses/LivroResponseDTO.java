package br.com.api.livraria.forum.responses;

import java.io.Serializable;
import java.time.LocalDate;

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

	private LocalDate dataLancamento;

}

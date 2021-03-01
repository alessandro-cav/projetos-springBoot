package br.com.api.livraria.forum.responses;

import java.io.Serializable;
import java.util.List;

import br.com.api.livraria.forum.models.Editora;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditoraLivroResponseDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String codEditora;

	private String razaoSocial;

	private String fantasia;

	private String telefone;

	private String contato;

	private String endereco;

	private List<LivroResponseDTO> livro;

	public static EditoraLivroResponseDTO transformaEmDTO(Editora editora, List<LivroResponseDTO> livroResponseDTOs) {
		return new EditoraLivroResponseDTO(editora.getId(), editora.getCodEditora(), editora.getRazaoSocial(),
				editora.getFantasia(), editora.getTelefone(), editora.getContato(), editora.getEndereco(), livroResponseDTOs);
	}
}

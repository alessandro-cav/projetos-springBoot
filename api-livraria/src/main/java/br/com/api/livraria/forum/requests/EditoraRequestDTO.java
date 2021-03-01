package br.com.api.livraria.forum.requests;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import br.com.api.livraria.forum.models.Editora;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditoraRequestDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	@NotBlank(message = "Codigo é obrigatorio!")
	private String codEditora;

	@NotBlank(message = "Razão Social é obrigatorio!")
	private String razaoSocial;

	@NotBlank(message = "Fantasia é obrigatorio!")
	private String fantasia;

	@NotBlank(message = "Telefone é obrigatorio!")
	private String telefone;

	@NotBlank(message = "Contato é obrigatorio!")
	private String contato;

	@NotBlank(message = "Endereço é obrigatorio!")
	private String endereco;

	public static Editora transformaDTOEmObjeto(EditoraRequestDTO editoraRequestDTO) {
		return new Editora(editoraRequestDTO.getId(), editoraRequestDTO.getCodEditora(),
				editoraRequestDTO.getRazaoSocial(), editoraRequestDTO.getFantasia(), editoraRequestDTO.getTelefone(),
				editoraRequestDTO.getContato(), editoraRequestDTO.getEndereco());
	}

}

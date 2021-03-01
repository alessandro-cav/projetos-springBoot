package br.com.api.livraria.forum.requests;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditoraRequestDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String codEditora;

	private String razaoSocial;

	private String fantasia;

	private String telefone;

	private String contato;

	private String endereco;

}

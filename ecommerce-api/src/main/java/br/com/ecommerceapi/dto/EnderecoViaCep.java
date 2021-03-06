package br.com.ecommerceapi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class EnderecoViaCep {
	
	private String logradouro;
	
	private String bairro;
	
	private String complemento;
	
	private String uf;
	
	private String localidade;

}

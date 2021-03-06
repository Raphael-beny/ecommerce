package br.com.ecommerceapi.service;

import javax.validation.constraints.NotNull;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import br.com.ecommerceapi.dto.EnderecoViaCep;
import br.com.ecommerceapi.exception.RegistroNaoEncontradoException;

@Service
@Validated
public class EnderecoService {
	
	@Autowired
	private ProducerTemplate producerTemplate;
	
	public EnderecoViaCep buscarPor(@NotNull(message = "O cep deve ser informado") String cep) {
		
		EnderecoViaCep enderecoEncontrado = 
				producerTemplate.requestBody("direct:buscarEndereco", cep, EnderecoViaCep.class);
		
		if(enderecoEncontrado == null) {
			throw new RegistroNaoEncontradoException("Endereço não encontrado com o cep "+cep);
		}
		
		return enderecoEncontrado;
		
	}

}

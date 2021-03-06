package br.com.ecommerceapi.service;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import br.com.ecommerceapi.dto.EnderecoViaCep;
import br.com.ecommerceapi.entity.Cliente;
import br.com.ecommerceapi.repository.ClienteRepository;

@Service
@Validated
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoService enderecoService;
	
	public Cliente inserir(
			@Valid 
			@NotNull(message = "O cliente não deve ser nulo") 
			Cliente cliente) {
		
		cliente.getEnderecos().forEach(e -> {
			
			EnderecoViaCep endereco = enderecoService.buscarPor(e.getCEP());
			e.setBairro(endereco.getBairro());
			e.setCidade(endereco.getLocalidade());
			e.setEstado(endereco.getUf());
			e.setLogradouro(endereco.getLogradouro());
			
		});
		
		Cliente clienteSalvo = clienteRepository.save(cliente);
		
		return clienteSalvo;
	}
	
	public Cliente buscarPor(String email) {
		return clienteRepository.buscarPor(email);
	}
	
	public List<Cliente> listarTodos(){
		return clienteRepository.findAll();
	}

}

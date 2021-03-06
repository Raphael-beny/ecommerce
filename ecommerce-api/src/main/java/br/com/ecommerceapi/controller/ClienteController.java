package br.com.ecommerceapi.controller;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ecommerceapi.entity.Cliente;
import br.com.ecommerceapi.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@PostMapping
	public ResponseEntity<?> inserir(@RequestBody Cliente cliente){
		clienteService.inserir(cliente);
		return ResponseEntity.created(URI.create("/clientes/id/" + cliente.getId())).build();
	}
	
	@GetMapping
	public ResponseEntity<?> buscarPor(@RequestParam(name = "email") Optional<String> email){
		if(email.isPresent()) {
			return ResponseEntity.ok(clienteService.buscarPor(email.get()));
		}
		
		return ResponseEntity.ok(clienteService.listarTodos());
	}

}

package br.com.ecommerceapi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true, value = {"hibernateLazyInitializer", "handler"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = "Endereco")
@Table(name = "enderecos")
public class Endereco {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Integer id;
	
	@Column(name = "logradouro")
	@NotNull(message = "O logradouro do endereço é obrigatório")
	private String logradouro;
	
	@Column(name = "numero")
	private Integer numero;
	
	@Column(name = "bairro")
	@NotNull(message = "O bairro do endereço é obrigatório")
	private String bairro;
	
	@Column(name = "cidade")
	@NotNull(message = "A cidade do endereço é obrigatória")
	private String cidade;
	
	@Column(name = "estado")
	@NotNull(message = "O estado do endereço é obrigatório")
	@Size(min = 2, max = 2, message = "O estado deve conter 2 caracteres")
	private String estado;
	
	@Column(name = "cep")
	@NotNull(message = "O cep do endereço é obrigatório")
	@Size(min = 8, max = 8, message = "O cep deve conter 8 caracteres")
	private String CEP;

}

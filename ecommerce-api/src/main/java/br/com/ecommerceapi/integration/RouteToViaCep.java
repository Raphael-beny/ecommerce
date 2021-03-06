package br.com.ecommerceapi.integration;

import javax.annotation.PostConstruct;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.ecommerceapi.dto.EnderecoViaCep;

@Component
public class RouteToViaCep extends RouteBuilder {

		private String endpointViaCep;
		
		@Value("${endpoint-viacep}")
		private String urlViaCep;
		
		@PostConstruct
		public void inicializar() {
			this.endpointViaCep = urlViaCep + "${body}/json/";
		}
		
		@Override
		public void configure() throws Exception {
			
			from("direct:buscarEndereco")
				.id("consultar-via-cep")
					.doTry()
						.setHeader(Exchange.HTTP_METHOD, constant("GET"))
						.toD(endpointViaCep)
						.process(new Processor() {
							@Override
							public void process(Exchange exchange) throws Exception {
								String viaCep = exchange.getMessage().getBody(String.class);
								JSONObject viaCepJson = new JSONObject(viaCep);
								EnderecoViaCep endereco = new EnderecoViaCep();
								endereco.setBairro(viaCepJson.getString("bairro"));
								endereco.setComplemento(viaCepJson.getString("complemento"));
								endereco.setLocalidade(viaCepJson.getString("localidade"));
								endereco.setLogradouro(viaCepJson.getString("logradouro"));
								endereco.setUf(viaCepJson.getString("uf"));
								
								exchange.getMessage().setBody(endereco);
							}
						})
					.doCatch(Exception.class)
						.setProperty("error", simple("${exception}"))
						.process(new Processor() {						
							@Override
							public void process(Exchange exchange) throws Exception {
								exchange.getMessage().setBody(null);
								Exception ex = exchange.getProperty("error", Exception.class);
								ex.printStackTrace();
							}
						})
			.end();
			
		}

}

package com.ProjetoEscola.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //Quando inicia o projeto, todos os configuration são lidos;
public class ModelMapperConfig {

	@Bean //Já fica pronto para ser usado e é gerenciado automaticamente pelo spring;
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
}

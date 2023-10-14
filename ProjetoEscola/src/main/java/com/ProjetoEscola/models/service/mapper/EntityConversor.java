package com.ProjetoEscola.models.service.mapper;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EntityConversor {

	@Autowired
	private ModelMapper mapper;
	
	public <O,I> O parseObject(I input, Class<O> output) {
		return mapper.map(input, output);
	}
	
	public <O,I> List<O> parseListObjects(List<I> input, Class<O> output) {
		List<O> outputObjects = new ArrayList<O>();
		
		for(I i:input) {
			outputObjects.add(mapper.map(i, output));
		}
		
		return outputObjects;
	}
}
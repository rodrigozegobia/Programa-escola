package com.ProjetoEscola.models.service.pagination;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageRequestConfig {

	public static Pageable generatePage(Integer actualPage, Integer pageSize, String order, String props) {
		return PageRequest.of(actualPage, pageSize, getSortByProp(order, props));
	}
	
	private static Sort getSortByProp(String order, String props) {
		Sort sort = order.equalsIgnoreCase(Sort.Direction.ASC.name())
				? Sort.by(props).ascending()
				: Sort.by(props).descending();
		
		return sort;
	}
}

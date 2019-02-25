package com.cchacalcaje.cromartic.security.utils;

import java.util.HashSet;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@Component
public class FilterFields {

	public MappingJacksonValue filterEntity(Object object, String className , List<String> fields) {
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
				.filterOutAllExcept(new HashSet<String>(fields));
		FilterProvider filters = new SimpleFilterProvider()
				.addFilter(className, filter);
		MappingJacksonValue mapping = new MappingJacksonValue(object);
		mapping.setFilters(filters);
		return mapping;
	}
}

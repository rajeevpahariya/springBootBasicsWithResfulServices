package com.rest.webservices.restfulwebservices.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {
	
	@GetMapping(path = "/filtering")
	public StaticFilteringBean getBean() {
		return new StaticFilteringBean("field1", "field2", "field3");
	}
	
	@GetMapping(path = "/filtering-beans")
	public List<StaticFilteringBean> getBeans() {
		return Arrays.asList(new StaticFilteringBean("field1", "field2", "field3"),
				new StaticFilteringBean("field11", "field12", "field13"));
	}
	
	@GetMapping(path = "/dynamicFiltering")
	public MappingJacksonValue getDynamicBean() {
		DynamicFilteringBean dynamicBean = new DynamicFilteringBean("field1", "field2", "field3");
		/*
		 * SimpleBeanPropertyFilter filter =
		 * SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field2");
		 * FilterProvider fp = new SimpleFilterProvider()
		 * .addFilter("DynamicFilteringBean", filter); MappingJacksonValue mapping = new
		 * MappingJacksonValue(dynamicBean); mapping.setFilters(fp);
		 */
		MappingJacksonValue mapping = getMapping(dynamicBean, new String[]{"field1", "field2"});
		return mapping;
	}
	
	@GetMapping(path = "/dynamicFiltering-beans")
	public MappingJacksonValue getynamicBeanBeans() {
		List<DynamicFilteringBean> list = Arrays.asList(new DynamicFilteringBean("field1", "field2", "field3"),
				new DynamicFilteringBean("field11", "field12", "field13"));
		MappingJacksonValue mapping = getMapping(list, new String[]{"field3", "field2"});
		/*
		 * SimpleBeanPropertyFilter filter =
		 * SimpleBeanPropertyFilter.filterOutAllExcept("field3", "field2");
		 * FilterProvider fp = new SimpleFilterProvider()
		 * .addFilter("DynamicFilteringBean", filter); MappingJacksonValue mapping = new
		 * MappingJacksonValue(list); mapping.setFilters(fp);
		 */
		return mapping;
	}
	
	// Method created to remove duplicate code
	public MappingJacksonValue getMapping(Object obj, String[] strArray) {
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(strArray);
		FilterProvider fp = new SimpleFilterProvider()
				.addFilter("DynamicFilteringBean", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(obj);
		mapping.setFilters(fp);
		return mapping;
	}
}

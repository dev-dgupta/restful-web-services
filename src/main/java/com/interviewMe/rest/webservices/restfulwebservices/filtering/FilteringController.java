package com.interviewMe.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    @GetMapping("/static-filtering")
    public StaticFilterBean retrieveStaticFilterBean() {
        return new StaticFilterBean("value1", "value2", "value3");
    }

    @GetMapping("/static-filtering-list")
    public List<StaticFilterBean> retrieveStaticFilterBeanList() {
        return Arrays.asList(
                new StaticFilterBean("value1", "value2", "value3"),
                new StaticFilterBean("value3", "value4", "value5"));
    }

    @GetMapping("/dynamic-filtering")
    public MappingJacksonValue retrieveDynamicFilterBean() {
        DynamicFilterBean dynamicFilterBean = new DynamicFilterBean("value1", "value2", "value3");

        SimpleBeanPropertyFilter filter=SimpleBeanPropertyFilter.filterOutAllExcept("field1");
        FilterProvider filterProvider=new SimpleFilterProvider().addFilter("DynamicFilter",filter);
        MappingJacksonValue mappingJacksonValue=new MappingJacksonValue(dynamicFilterBean);
        mappingJacksonValue.setFilters(filterProvider);
        return mappingJacksonValue;
    }

    @GetMapping("/dynamic-filtering-list")
    public MappingJacksonValue retrieveDynamicFilterBeanList() {
        List<DynamicFilterBean> dynamicFilterBeans= Arrays.asList(
                new DynamicFilterBean("value1", "value2", "value3"),
                new DynamicFilterBean("value3", "value4", "value5"));

        SimpleBeanPropertyFilter filter=SimpleBeanPropertyFilter.filterOutAllExcept("field1","field2");
        FilterProvider filterProvider=new SimpleFilterProvider().addFilter("DynamicFilter",filter);
        MappingJacksonValue mappingJacksonValue=new MappingJacksonValue(dynamicFilterBeans);
        mappingJacksonValue.setFilters(filterProvider);
        return mappingJacksonValue;

    }


}

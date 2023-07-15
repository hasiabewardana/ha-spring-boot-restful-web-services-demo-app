package com.ha.haspringbootrestfulwebservicesdemoapp.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.ha.haspringbootrestfulwebservicesdemoapp.domain.SomeBean;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    @RequestMapping("/filtering")
    public MappingJacksonValue filtering() {
        SomeBean someBean = new SomeBean("value1", "value2", "value3");

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBean);
        SimpleBeanPropertyFilter simpleBeanPropertyFilter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field3");
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("SomeBeanFilter", simpleBeanPropertyFilter);
        mappingJacksonValue.setFilters(filterProvider);

        return mappingJacksonValue;
    }

    @RequestMapping("/filtering-list")
    public MappingJacksonValue filteringList() {
        List<SomeBean> someBeanList = Arrays.asList(new SomeBean("value1", "value2", "value3"), new SomeBean("value4", "value5", "value6"));

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBeanList);
        SimpleBeanPropertyFilter simpleBeanPropertyFilter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field3");
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("SomeBeanFilter", simpleBeanPropertyFilter);
        mappingJacksonValue.setFilters(filterProvider);

        return mappingJacksonValue;
    }
}

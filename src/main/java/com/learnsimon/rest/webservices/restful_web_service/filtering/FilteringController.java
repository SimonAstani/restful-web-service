package com.learnsimon.rest.webservices.restful_web_service.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Arrays;
import java.util.Map;

@RestController
public class FilteringController {

    public class JsonFilterUtil {
        //method to apply a fitler dynamically

        public static MappingJacksonValue applyFilter(Object object, String filterName, String... fieldsToInclude){
            SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(fieldsToInclude);
            SimpleFilterProvider filters = new SimpleFilterProvider().addFilter(filterName, filter);
            MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(object);
            mappingJacksonValue.setFilters(filters);
            return mappingJacksonValue;
        }
    }

    @GetMapping("/filtering")
    public MappingJacksonValue filtering(){
        someBean someBean = new someBean("value1", "value2", "value3");
        return JsonFilterUtil.applyFilter(someBean, "SomeBeanFilter", "field1", "field3");
    }

    @GetMapping("/filtering-list")
    public MappingJacksonValue filteringList(){
        List<someBean> list = Arrays.asList(new someBean("value1", "value2", "value3"),
                new someBean("value4", "value5", "value6"));
        return JsonFilterUtil.applyFilter(list, "SomeBeanFilter", "field2", "field3");
    }
}

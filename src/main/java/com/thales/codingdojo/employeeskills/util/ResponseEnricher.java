package com.thales.codingdojo.employeeskills.util;


import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractMappingJacksonResponseBodyAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class ResponseEnricher extends AbstractMappingJacksonResponseBodyAdvice {
    private static final String[] EMPTY = new String[]{};
    private static final String PARTIAL_RESPONSE_INCLUDED_PARAM = "fields";
    private static final String PARTIAL_RESPONSE_EXCLUDED_PARAM = "excluded_fields";

    private static final String PARTIAL_RESPONSE_SEPARATOR = ",";

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    protected void beforeBodyWriteInternal(MappingJacksonValue mappingJacksonValue, MediaType mediaType, MethodParameter methodParameter, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        HttpServletRequest baseReq = ((ServletServerHttpRequest) serverHttpRequest).getServletRequest();
        String exclusion = baseReq.getParameter(PARTIAL_RESPONSE_EXCLUDED_PARAM);

        mappingJacksonValue.setFilters(new SimpleFilterProvider().setDefaultFilter(SimpleBeanPropertyFilter.serializeAll()));
        if(exclusion!=null){
            String[] fields = getAllFields(exclusion);
            mappingJacksonValue.setFilters(new SimpleFilterProvider().addFilter("dynamicExclude", SimpleBeanPropertyFilter.serializeAllExcept(fields)));
        }
        String inclusion = baseReq.getParameter(PARTIAL_RESPONSE_INCLUDED_PARAM);
        if(inclusion!=null){
            String[] fields = getAllFields(inclusion);
            mappingJacksonValue.setFilters(new SimpleFilterProvider().addFilter("dynamicExclude", SimpleBeanPropertyFilter.filterOutAllExcept(fields)));
        }

    }

    private String[] getAllFields(String fieldsString) {
        String[] attrs = StringUtils.split(fieldsString, PARTIAL_RESPONSE_SEPARATOR);
        if (attrs == null) {
            attrs = new String[] {fieldsString};
        }
        return attrs;
    }
}

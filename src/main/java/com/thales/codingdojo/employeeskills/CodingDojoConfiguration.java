package com.thales.codingdojo.employeeskills;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.servlet.view.xml.MappingJackson2XmlView;

@Configuration
@EnableWebMvc
public class CodingDojoConfiguration extends WebMvcConfigurerAdapter {
            /**
         *  Total customization - see below for explanation.
         */
            // By default strategies for checking the extension of the request path and the Accept header are registered. The path extension check will perform lookups through the ServletContext and the Java Activation Framework (if present) unless media types are configured.
      /*  @Override
        public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
            configurer.favorPathExtension(false).
                    favorParameter(false).
                    parameterName("format").
                    ignoreAcceptHeader(true).
                    useJaf(false).
                    defaultContentType(MediaType.APPLICATION_JSON).
                    mediaType("xml", MediaType.APPLICATION_XML).
                    mediaType("json", MediaType.APPLICATION_JSON);
        }*/

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.enableContentNegotiation(
                new MappingJackson2XmlView(),
                new MappingJackson2JsonView());
    }
}

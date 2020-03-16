package com.fresh.restapi.configs;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Arrays;
import java.util.List;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Bean
    @Qualifier("SnakeCaseJackson2ObjectMapperBuilder")
    public Jackson2ObjectMapperBuilder getJackson2ObjectMapperBuilder() {
        return new Jackson2ObjectMapperBuilder()
                .propertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
    }

    @Bean
    @Autowired
    public MappingJackson2HttpMessageConverter getMappingJackson2HttpMessageConverter(
            @Qualifier("SnakeCaseJackson2ObjectMapperBuilder") Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder) {
        return new MappingJackson2HttpMessageConverter(jackson2ObjectMapperBuilder.build());
    }


    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        //converters.add(this.getMappingJackson2HttpMessageConverter());
    }

    @Bean
    @Autowired
    public RestTemplate getRestTemplate(MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter) {
        return new RestTemplate(Arrays.asList(mappingJackson2HttpMessageConverter));
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/docs", "/swagger-ui.html");
    }

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new Authorizer());
//    }
}

package com.epam.spring.movie.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.epam.spring.movie.converter.LocalDateTimeConverter;
 
@Configuration
@EnableWebMvc
class WebMvcContext extends WebMvcConfigurerAdapter {
 
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new LocalDateTimeConverter("yyyy-MM-dd'T'HH:mm"));
    }

}
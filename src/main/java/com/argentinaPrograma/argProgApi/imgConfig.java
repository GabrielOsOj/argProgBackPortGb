package com.argentinaPrograma.argProgApi;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class imgConfig implements WebMvcConfigurer {

    public void manejadorDeRecursos(ResourceHandlerRegistry registro){
        WebMvcConfigurer.super.addResourceHandlers(registro);
        registro.addResourceHandler("/userImgs/**").addResourceLocations("file:///C:/Users/gabriel/Desktop/https/portafolio%20con%20front%20y%20back%20argProg/userImgs/");
    }



}

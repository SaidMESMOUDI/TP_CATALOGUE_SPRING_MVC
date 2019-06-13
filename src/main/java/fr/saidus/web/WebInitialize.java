package fr.saidus.web;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import fr.saidus.TpCatalogueSpringMvcApplication;

public class WebInitialize extends SpringBootServletInitializer {
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		
		return builder.sources(TpCatalogueSpringMvcApplication.class);
		
	}
}

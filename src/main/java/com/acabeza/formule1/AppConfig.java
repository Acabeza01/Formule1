package com.acabeza.formule1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

@Configuration
public class AppConfig implements WebMvcConfigurer  {

	@Autowired
	private ApplicationContext applicationContext;
	
	@Override
	public void addResourceHandlers (ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/files/**")
				.addResourceLocations("/WEB-INF/pdf/");
	}
	
	@Bean
	@Description("Thymeleaf Template Resolver")
	public SpringResourceTemplateResolver templateResolver() {
		SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
		templateResolver.setApplicationContext(applicationContext);
	    templateResolver.setPrefix("/WEB-INF/views/");
	    templateResolver.setSuffix(".html");
//	    templateResolver.setTemplateMode("HTML5");
	    return templateResolver;
	}

	@Bean
	@Description("Thymeleaf Template Engine")
	public SpringTemplateEngine templateEngine() {
	    SpringTemplateEngine templateEngine = new SpringTemplateEngine();
	    templateEngine.setTemplateResolver(templateResolver());
	    templateEngine.setEnableSpringELCompiler(true);
	    return templateEngine;
	}
	
	@Bean
	@Description("Thymeleaf View Resolver")	
	public ViewResolver thymeleafResolver() {
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(templateEngine());
		viewResolver.setOrder(0);
		return viewResolver;
	}

	@Bean
//	The th:text=”#{key}” tag attribute can be used to display values from property files.
//	For this to work the property file must be configured as messageSource bean:
//	messages.properties file is hiervoor toegevoegd
	@Description("Spring Message Resolver")
	public ResourceBundleMessageSource messageSource() {
	    ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
	    messageSource.setBasename("messages");  // messages.properties is naam van de file
	    return messageSource;
	}	
	
	@Bean
	@Description("JSP View Resolver")
	public ViewResolver viewResolver() {
		InternalResourceViewResolver bean = new InternalResourceViewResolver();
		bean.setPrefix("/WEB-INF/jsp/");
		bean.setSuffix(".jsp");
		bean.setOrder(1); // niet meer actief
		return bean;
	}

	
	
}

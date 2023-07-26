package com.sky.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

	public static final String AUTHORIZATION_HEADER = "Authorization";

	public ApiKey apiKey() {
		return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
	}

	private List<SecurityContext> securityContexts() {
		return Arrays.asList(SecurityContext.builder().securityReferences(sRef()).build());
	}

	private List<SecurityReference> sRef() {
		AuthorizationScope scope = new AuthorizationScope("global", "accessEverything");
		return Arrays.asList(new SecurityReference("JWT", new AuthorizationScope[] { scope }));
	}

	@Bean
	Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.enable(true)
				.securityContexts(securityContexts())
				.securitySchemes(Arrays.asList(apiKey()))
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.sky"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(getInfo());
	}

//	private ApiInfo getInfo() {
//
//		return new ApiInfo("Blog Application: Backend", "This application is developed by Shubham Yadav", "1.0",
//				"Terms of Service", new Contact("Shubham Yadav", "Blog Application", "shubhamyadav@gmail.com"),
//				"License of APIs", "API License url", Collections.emptyList());
//	}
	
	private ApiInfo getInfo() {

		return new ApiInfoBuilder()
				.title("Blog Application: Backend")
				.description("This application is developed by Shubham Yadav")
				.version("1.1.0")
				.license("License of APIs")
				.licenseUrl("API License url")
				.contact(new Contact("Shubham Yadav", "Blog Application", "shubhamyadav@gmail.com"))
				.build();
	}
}

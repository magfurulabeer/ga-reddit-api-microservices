package com.example.postsapi.config;

import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.xmlpull.v1.XmlPullParserException;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import java.io.IOException;

@Configuration
public class SwaggerConfig {
    @Bean
    public Docket api() throws IOException, XmlPullParserException {
        MavenXpp3Reader reader = new MavenXpp3Reader();
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.postsapi.controller"))
                .paths(PathSelectors.any())
                .build().apiInfo(new ApiInfo("GA Microservices - Posts REST API", "GA Microservices - Posts REST API", "v2", null, new Contact("abeer", "", "magfurulabeer@gmail.com"), null, null));
    }
}
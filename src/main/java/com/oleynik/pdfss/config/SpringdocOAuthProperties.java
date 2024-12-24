package com.oleynik.pdfss.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties("springdoc.oauth")
public class SpringdocOAuthProperties {
    private String authorizeUrl = "http://localhost:8081/oauth/authorize";
}

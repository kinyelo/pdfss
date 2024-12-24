package com.oleynik.pdfss.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.Scopes;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
@EnableConfigurationProperties(SpringdocOAuthProperties.class)
public class SpringdocConfiguration {
    private final SpringdocOAuthProperties springdocOAuthProperties;

    @Value("${spring.application.name}")
    private String applicationName;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title(applicationName)
                .version("1")
            )
            .addServersItem(new Server().url("/"))
            .addSecurityItem(new SecurityRequirement()
                .addList("oauth2")
            )
            .components(new Components()
                .addSecuritySchemes("oauth2", new SecurityScheme()
                    .type(SecurityScheme.Type.OAUTH2)
                    .in(SecurityScheme.In.HEADER)
                    .bearerFormat("jwt")
                    .flows(new OAuthFlows()
                        .implicit(new OAuthFlow()
                            .authorizationUrl(springdocOAuthProperties.getAuthorizeUrl())
                            .scopes(new Scopes()
                                .addString("read", "read scope")
                            )
                        )
                    )
                )
            );
    }
}

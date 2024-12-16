package com.generation.farmasys.config;


import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import io.swagger.v3.oas.models.servers.Server;

import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API FarmaSys")
                        .version("1.0")
                        .description("Documentação interativa para a api do projeto FarmaSys - gerenciador de medicamentos")
                        .contact(new Contact()
                        		.name("Matheus Torres")
                        		.url("https://matheustorres.vercel.app/")))
                .addServersItem(new Server()
                		.url("http://localhost:8080")
                		.description("Servidor de Desenvolvimento"))
                .externalDocs(new ExternalDocumentation()
                		.description("Github")
                		.url("https://github.com/Kagradiel/CRUD-Farmacia"));
    }
    
    private ApiResponse createApiResponse(String message) {
    	
    	return new ApiResponse().description(message);
    	
    }
    
    @Bean
    OpenApiCustomizer customerGlobalHeaderOpenApiCustomizer() {
    	
    	return openApi -> {
    		openApi.getPaths().values().forEach(pathItem -> pathItem.readOperations()
    				.forEach(operation ->{
    					
    					ApiResponses apiResponses = operation.getResponses();
    					
    					apiResponses.addApiResponse("200", createApiResponse("Sucesso!"));
    					apiResponses.addApiResponse("201", createApiResponse("Objeto Persistido"));
    					apiResponses.addApiResponse("204", createApiResponse("Objeto Excluido"));
    					apiResponses.addApiResponse("400", createApiResponse("Erro na Requisição"));
    					apiResponses.addApiResponse("401", createApiResponse("Acesso não autorizado"));
    					apiResponses.addApiResponse("403", createApiResponse("Acesso Proibido!"));
    					apiResponses.addApiResponse("404", createApiResponse("Objeto não encontrado"));
    					apiResponses.addApiResponse("500", createApiResponse("Erro na aplicação!!!"));
    					
    				}));
    	};
    	
    }
}
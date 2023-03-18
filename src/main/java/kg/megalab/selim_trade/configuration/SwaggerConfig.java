package kg.megalab.selim_trade.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info =@Info(
                title = "Selim API",
                version = "1.0",
                contact = @Contact(
                        name = "Bekzhan", email = "2020.prepartion@gmail.com"
                ),
                license = @License(
                        name = "Apache 2.0", url = "https://www.apache.org/licenses/LICENSE-2.0"
                ),
                description = "backend api for selim"
        )
)
public class SwaggerConfig {
    @Bean
    public OpenAPI customizeOpenAPI() {
        final String securitySchemeName = "Bearer_Token_Authorization";
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement()
                        .addList(securitySchemeName))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName, new SecurityScheme()
                                .name(securitySchemeName)
                                .type(SecurityScheme.Type.HTTP)
                                .description("Просто скопируй сюда тот access токен. Никакого bearer приписывать не надо. Только сам токен")
                                .scheme("bearer")
                                .bearerFormat("JWT")));
    }
}

package kg.megalab.selim_trade.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Selim API",
                version = "1.0",
                contact = @Contact(
                        name = "Bekzhan", email = "2020.prepartion@gmail.com"
                ),
                description = """
                        При логине выдается access и refresh токены.
                        С помощью access токена пользователь может авторизоваться.
                        Но он действителен только в течение 30 минут.
                        Сделано это для того, чтобы даже если кто-то украдет access 
                        токен, он не смог пользоваться им вечно и наворотить дел.
                        А чтобы обновлять его нужен refresh токен.
                        Потом чтобы не логиниться снова нужно отправить refresh токен на 
                        ендпоинт '/refresh-token'
                        все refresh токены хранятся в бд.
                        Refresh токен валиден в течение суток.
                        Если refresh токен есть в базе данных и срок его 
                        дейтсвия еще не истек, то выдается новый refresh и 
                        access токен. Старый refresh токен стирается с бд, 
                        чтобы если вдруг кто-то украдет его он не смог им воспользоваться больше 1 раза.
                        Если refresh токен тоже истек то он также стирается с бд и 
                        пользователю придется уже логиниться.
                                    """,
                license = @License(
                        name = "Apache 2.0", url = "https://www.apache.org/licenses/LICENSE-2.0"
                )
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
                                .bearerFormat("JWT")))
                ;
    }
}

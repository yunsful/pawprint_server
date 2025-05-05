package pawprint.demo.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    
    @Bean
    public OpenAPI pawPrintAPI() {
        Info info = new Info()
                .title("PawPrint API")
                .description("PawPrint API 명세서")
                .version("v1.0");
        
//        String jwtSchemeName = "Authorization";
//
//        // API 요청헤더에 인증정보 포함
//        SecurityRequirement securityRequirement = new SecurityRequirement().addList(jwtSchemeName);
//
//        // SecuritySchemes 등록
//        Components components = new Components()
//                .addSecuritySchemes(jwtSchemeName, new SecurityScheme()
//                        .name(jwtSchemeName)
//                        .type(SecurityScheme.Type.HTTP) // HTTP 방식
//                        .scheme("bearer")
//                        .bearerFormat("JWT"));
        
        return new OpenAPI()
                .components(new Components())
                .info(info);
//                .addSecurityItem(securityRequirement)
//                .components(components);
    }
    
    
}

package pawprint.demo.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
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
        
        
        return new OpenAPI()
                .components(new Components())
                .info(info);
    }
    
    
}

package com.apirest.portafolio.openapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;

import io.swagger.v3.oas.annotations.security.SecurityScheme;

@OpenAPIDefinition(info = @Info(title = "Api Portafolio - Open Api Documentation", version = "1.0", description = "Api RestFul para portafolio de proyectos personales"))

@SecurityScheme(name = "bearerAuth", description = "JWT authentication", scheme = "bearer", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", in = SecuritySchemeIn.HEADER)
public class OpenApiConfiguration {
        // ..
}

package com.attornatus.project.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
  info = @Info(
    title = "Person API",
    version = "v1",
    description = "API for managing persons",
    contact = @Contact(
      name = "Yuri Nascimento",
      email = "yuriidiiego@gmail.com",
      url = "https://github.com/yuriidiiego"
    ),
    license = @License(
      name = "Apache 2.0",
      url = "http://www.apache.org/licenses/LICENSE-2.0.html"
    )
  ),
  servers = {
    @Server(url = "http://localhost:8080", description = "Local server"),
    @Server(url = "https://person-api-production.up.railway.app", description = "Railway production server")
  }
)
public class OpenApiConfig {}

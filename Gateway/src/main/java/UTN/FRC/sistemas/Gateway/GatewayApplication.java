package UTN.FRC.sistemas.Gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(GatewayApplication.class);
		app.setWebApplicationType(WebApplicationType.SERVLET); // Fuerza el tipo MVC
		app.run(args);
	}
}


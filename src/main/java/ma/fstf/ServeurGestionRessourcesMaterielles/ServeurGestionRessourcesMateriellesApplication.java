package ma.fstf.ServeurGestionRessourcesMaterielles;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
		/*(scanBasePackages = {"ma.fstf.ServeurGestionRessourcesMaterielles.Models",
		"ma.fstf.ServeurGestionRessourcesMaterielles.Repositories"})*/
@EnableJpaRepositories(basePackages = "ma.fstf.ServeurGestionRessourcesMaterielles.Repositories")
public class ServeurGestionRessourcesMateriellesApplication {
	public static void main(String[] args) {
		SpringApplication.run(ServeurGestionRessourcesMateriellesApplication.class, args);
	}
}

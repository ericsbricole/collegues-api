package dev.collegue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import dev.collegue.model.Collegue;
import dev.collegue.repository.CollegueRepository;

@SpringBootApplication
public class ColleguesApiApplication {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private CollegueRepository colleguerespository;
	
	@Bean

	public WebMvcConfigurer corsConfigurer() {

		return new WebMvcConfigurer() {

			@Override

			public void addCorsMappings(CorsRegistry registry) {

				registry.addMapping("/**")

						.allowedOrigins("*")

						.allowedMethods("GET", "POST", "PATCH", "PUT", "OPTIONS")

						.allowCredentials(true);

				;

			}

		};

}

	@EventListener(ContextRefreshedEvent.class)
	public void init() {

		List<Collegue> collegues = new ArrayList<>();
		collegues.add(new Collegue(UUID.randomUUID().toString(), "Astier", "Alexandre", "astier@kaamelot.com",
				LocalDate.of(1980, 10, 11), "https://www.savemybrain.net/v2/wp-content/uploads/2012/04/arthur.jpg",
				passwordEncoder.encode("toto"), Arrays.asList("ROLE_USER")));
		collegues.add(new Collegue(UUID.randomUUID().toString(), "Asterix", "Gaulois", "asterix@armorique.com",
				LocalDate.of(1960, 01, 14), "http://om1337.o.m.pic.centerblog.net/4w7h72p3.jpg",
				passwordEncoder.encode("toto"), Arrays.asList("ROLE_USER")));
		collegues.add(new Collegue(UUID.randomUUID().toString(), "Dark", "Vador", "vador@blackStar.com",
				LocalDate.of(1960, 12, 05), "https://reggiestake.files.wordpress.com/2012/06/darth-vader-3.jpeg",
				passwordEncoder.encode("toto"), Arrays.asList("ROLE_USER")));
		collegues.add(new Collegue(UUID.randomUUID().toString(), "Dark", "Sidious", "palpatine@empire.com",
				LocalDate.of(1910, 12, 25), "https://i.ytimg.com/vi/HjMILwTcLsQ/maxresdefault.jpg",
				passwordEncoder.encode("titi"), Arrays.asList("ROLE_ADMIN")));
		collegues.add(new Collegue(UUID.randomUUID().toString(), "Dark", "Nihilus", "sion@marmiton.com",
				LocalDate.of(1950, 05, 20),
				"http://static.giantbomb.com/uploads/original/7/73953/1661864-darth_nihilus.jpg",
				passwordEncoder.encode("toto"), Arrays.asList("ROLE_USER")));
		collegues.add(new Collegue(UUID.randomUUID().toString(), "Dark", "Sion", "sion@vivelessith.com",
				LocalDate.of(1950, 05, 20), "https://i.pinimg.com/564x/ed/bc/90/edbc90c6c9547e02c479ce246fd5300f.jpg",
				passwordEncoder.encode("toto"), Arrays.asList("ROLE_USER")));
		colleguerespository.saveAll(collegues);

	}

	public static void main(String[] args) {
		SpringApplication.run(ColleguesApiApplication.class, args);
	}

}

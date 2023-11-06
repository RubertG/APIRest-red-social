package db2.redsocial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EntityScan("db2.redsocial.entities")
public class RedsocialApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedsocialApplication.class, args);
	}

}

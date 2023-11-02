package cz.inventi.kpj.KPJ_Homework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class KpjHomeworkApplication{

	public static void main(String[] args) {

		SpringApplication.run(KpjHomeworkApplication.class, args);
	}

}

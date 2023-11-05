package cz.inventi.kpj.KPJ_Homework;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@Slf4j
//@ComponentScan(basePackages = "cz.inventi.kpj.KPJ_Homework")
public class KpjHomeworkApplication{

	public static void main(String[] args) {

		SpringApplication.run(KpjHomeworkApplication.class, args);
	}

}

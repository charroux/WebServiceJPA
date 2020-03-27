package com.efrei.JPAExample;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@SpringBootApplication
public class JpaExampleApplication {

	private static final Logger log = LoggerFactory.getLogger(JpaExampleApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(JpaExampleApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(PersonRepository repository) {
		return (args) -> {
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = dateFormat.parse("2020-10-09");
			log.info(date.toString());

			Rent rent = new Rent();
			rent.setBeginDate(date);

			date = dateFormat.parse("2020-12-09");
			rent.setEndDate(date);

			Person tintin = new Person("Tintin");
			tintin.getRents().add(rent);
			rent.setPerson(tintin);

			Car ferrari = new Car("11AA22");
			ferrari.getRents().add(rent);
			rent.setVehicule(ferrari);

			repository.save(tintin);

			log.info("-------------------------------");
			log.info("Persons found with findAll():");
			for (Person person : repository.findAll()) {
				log.info(person.toString());
			}
			log.info("");

			log.info("--------------------------------------------");
			log.info("Person found with findName('Tintin'):");
			repository.findByName("Tintin").forEach(person -> {
				log.info(person.toString());
			});

		};
	}

}

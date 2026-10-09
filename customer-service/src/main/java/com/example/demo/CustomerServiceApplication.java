package com.example.demo;

import com.example.demo.config.ConfigTestRestController;
import com.example.demo.config.CustomerConfigParams;
import com.example.demo.entities.Customer;
import com.example.demo.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(CustomerConfigParams.class)
public class  CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(CustomerRepository customerRepository){
		return args->{
			customerRepository.save(Customer.builder().name("mohammed").email("med@gmail.com").build());
			customerRepository.save(Customer.builder().name("Marwa").email("marwa@gmail.com").build());
			customerRepository.save(Customer.builder().name("Hajar").email("hajar@gmail.com").build());

		};
	}

}

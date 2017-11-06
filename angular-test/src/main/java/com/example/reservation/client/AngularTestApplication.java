package com.example.reservation.client;

import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@SpringBootApplication
public class AngularTestApplication implements CommandLineRunner{
	
	@Autowired
	private RuleRepository repo;

	public static void main(String[] args) {
		SpringApplication.run(AngularTestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Arrays.asList("Minimum 5 characters.", "Maximum 20 characters ", "Should contain a number.")
			.forEach(name -> repo.save(new Rule(name)));
	}
}

@RepositoryRestResource
interface RuleRepository extends JpaRepository<Rule, Long> {}

@Entity
class Rule {
	@Id
	@GeneratedValue
	private Long id;
	
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Rule(String name) {
		this.name = name;
	}

	public Rule() {
		super();
	}	
}

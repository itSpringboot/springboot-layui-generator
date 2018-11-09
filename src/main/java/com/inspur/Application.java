package com.inspur;

import com.inspur.factory.generator.GeneratorHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

/**
 * {@link com.inspur.factory.generator.GeneratorHelper}
 */
@SpringBootApplication
public class Application {

	@Autowired
	GeneratorHelper generatorHelper;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@PostConstruct
	public void method(){
		generatorHelper.generate();
	}

}

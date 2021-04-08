package com.ssafy.kpop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;

import com.amazonaws.services.elasticmapreduce.model.Application;

@SpringBootApplication
public class KpopApplication {

	public static void main(String[] args) {
		SpringApplication app =  new SpringApplication(KpopApplication.class);
		app.addListeners(new ApplicationPidFileWriter());
		app.run(args);
	}

}

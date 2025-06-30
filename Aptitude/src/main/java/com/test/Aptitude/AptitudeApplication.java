package com.test.Aptitude;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.test.Aptitude.DTOs.Input.ClientOrder;
import com.test.Aptitude.DTOs.Output.Order;
import com.test.Aptitude.Mapper.FromInputToOutput;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.InputStream;

@SpringBootApplication
public class AptitudeApplication {

	public static void main(String[] args) {
		SpringApplication.run(AptitudeApplication.class, args);
	}

}


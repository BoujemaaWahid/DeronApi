package com.deron.demo;

import net.bytebuddy.utility.RandomString;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
public class DeronApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeronApplication.class, args);
	}

}

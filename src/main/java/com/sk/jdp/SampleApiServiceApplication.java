package com.sk.jdp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@SpringBootApplication
public class SampleApiServiceApplication {


	public static void main(String[] args) {
		SpringApplication.run(SampleApiServiceApplication.class, args);
	}

}

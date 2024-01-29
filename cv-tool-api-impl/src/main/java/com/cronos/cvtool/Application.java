package com.cronos.cvtool;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class Application {

	@Autowired
	Environment environment;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@PostConstruct
	public void printServerInfo() throws UnknownHostException {
		String host = InetAddress.getLocalHost().getHostAddress();
		String port = environment.getProperty("server.port");
		String contextPath = environment.getProperty("server.servlet.context-path");

		log.info("--------------------------------------------------------------------");
		log.info("   Health check:  http://{}:{}{}/actuator/health", host, port, contextPath);
		log.info("   Swagger:       http://{}:{}{}/swagger-ui.html", host, port, contextPath);
		log.info("--------------------------------------------------------------------");
	}
}

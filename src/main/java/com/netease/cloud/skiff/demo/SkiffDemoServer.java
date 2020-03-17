package com.netease.cloud.skiff.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SkiffDemoServer {
	public static void main(String[] args) {
		SpringApplication.run(SkiffDemoServer.class, args);
	}
}

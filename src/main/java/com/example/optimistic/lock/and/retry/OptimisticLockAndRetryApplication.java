package com.example.optimistic.lock.and.retry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
public class OptimisticLockAndRetryApplication {

	public static void main(String[] args) {
		SpringApplication.run(OptimisticLockAndRetryApplication.class, args);
	}
}

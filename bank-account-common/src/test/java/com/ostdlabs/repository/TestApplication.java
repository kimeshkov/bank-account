package com.ostdlabs.repository;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.ostdlabs.model")
public class TestApplication {

}

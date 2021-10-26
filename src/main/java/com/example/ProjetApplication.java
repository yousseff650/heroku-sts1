package com.example;


import java.util.Date;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.dao.CompteRepository;
import com.example.entities.Compte;

@SpringBootApplication
public class ProjetApplication implements CommandLineRunner{

	@Autowired
	private CompteRepository icompRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(ProjetApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		 Compte compte1 = icompRepository.save(new Compte(1, 1000, new Date() ) );
		 Compte compte2 = icompRepository.save(new Compte(2, 2000, new Date() ) );
		 Compte compte3 = icompRepository.save(new Compte(3, 3000, new Date() ) );
	}
	

}

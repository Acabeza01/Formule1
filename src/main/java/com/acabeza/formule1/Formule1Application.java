package com.acabeza.formule1;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Formule1Application {
	
	@Autowired
	StandService standService;
	

	private static final Logger log = LoggerFactory.getLogger(Formule1Application.class);	 
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM HH:mm:ss");	
	
    public static Team teams[] = new Team[4];

    
	public static void main(String[] args) {
		SpringApplication.run(Formule1Application.class, args);
	}

	  @Bean
	  public CommandLineRunner Run() {
		  
	    return (args) -> {

	    	log.info("F1 Spel started - the time is now {}", dateFormat.format(new Date()));
    	
	    	
	    };
	  }
	  

	  
}

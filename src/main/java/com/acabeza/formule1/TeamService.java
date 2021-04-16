package com.acabeza.formule1;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

@Service
public class TeamService {
	
	@Autowired 
	StandService standService;
	
		private static final Logger log = LoggerFactory.getLogger(Formule1Application.class);	 
	    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM HH:mm:ss");
	    
	    public List<Team> getTeams() {

	    	log.info("getTeams started");
	    	List<Team> teams = Arrays.asList(StandService.arjan, StandService.bram, StandService.rick, StandService.thijs);
	    	
	    	return teams;
	    }
}

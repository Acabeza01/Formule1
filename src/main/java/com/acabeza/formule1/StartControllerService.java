package com.acabeza.formule1;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class StartControllerService {
	
	@Autowired
	StandService standService;
	
    public static Map<String, Integer> stand = new HashMap<String, Integer>();
	private static final Logger log = LoggerFactory.getLogger(Formule1Application.class);	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM HH:mm:ss");	
	
	@GetMapping({"/", "/start"})
	public String start (Model model) throws Exception {
		
		Map<String, Integer> start = inlezenStand();
		model.addAttribute("start", start);
		
		return "start";
	}

	  public Map<String, Integer> inlezenStand() throws Exception {
		  
		  	log.info("InlezenKansen started - the time is now {}", dateFormat.format(new Date()));
			
		  	String url = "https://www.onlinewedden.com/formule-1-gids/formule-1-stand";
		    
			    Document doc = Jsoup.connect(url).get();
			
		    Elements coureurs = doc.select(".column-2");
		    Elements punten = doc.select(".column-4");
		    
		    
		    for (int i=0; i<20; i++) {
		  	  log.info(i+1 + " " + coureurs.get(i+1).text() + " " + punten.get(i+1).text());
		  	  
		  	  String coureur =  coureurs.get(i+1).text();
		  	  int pnt = Integer.parseInt(punten.get(i+1).text());
		  	  
		//  	  1.	Hamilton	Mercedes	25
		//  	  2.	Verstappen	Red Bull	18
		//  	  3.	Bottas	Mercedes	16
		//  	  4.	Norris	McLaren	12
		//  	  5.	Perez	Red Bull	10
		//  	  6.	Leclerc	Ferrari	8
		//  	  7.	Ricciardo	McLaren	6
		//  	  8.	Sainz Jr.	Ferrari	4
		//  	  9.	Tsunoda	Alpha T	2
		//  	  10.	Stroll	Aston M	1	    	  
		  	  
		  	  switch (coureur) {
		  	  case "Hamilton"	:  pnt -= 25;
		  	  						break;
		  	  case "Verstappen"	:	pnt -= 18;
		  	  						break;
		  	  case "Bottas"		:	pnt -= 16;
										break;
		  	  case "Norris"		:	pnt -= 12;
										break;
		  	  case "Perez"		:	pnt -= 10;
										break;
		  	  case "Leclerc"	:	pnt -= 8;
										break;
		  	  case "Ricciardo"	:	pnt -= 6;
										break;
		  	  case "Sainz Jr."	:	pnt -= 4;
										break;
		  	  case "Tsunoda"	:	pnt -= 2;
										break;
		  	  case "Stroll"		:	pnt -= 1;
										break;
		  	  }
		  	  stand.put(coureur, pnt);
		
		    }	    	
		  	
		    stand = standService.sortByValue(stand);
		    
		    return stand;
	  }	
	
	
}

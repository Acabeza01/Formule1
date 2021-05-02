package com.acabeza.formule1;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LiveService {
	
	@Autowired
	StartService startService;
	
    	public static Map<String, Integer> livestand = new HashMap<String, Integer>();	
		private static final Logger log = LoggerFactory.getLogger(StartControllerService.class);	 
	    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM HH:mm:ss");
	    
	    public static Team arjan;
	    public static Team bram;
	    public static Team rick;
	    public static Team thijs;
	    
	    
	    public Map<String, Integer> live() throws Exception {
	    	log.info("live() started");
	    	
	    	inlezenLiveStand();
	      
	      List<String> coureursArjan = Arrays.asList("Verstappen", "Perez", "Ricciardo", "Schumacher");
	      Map<String,Integer> lijstArjan = new HashMap<String, Integer>(livestand);
	      lijstArjan.keySet().retainAll(coureursArjan);

	      List<String> coureursBram = Arrays.asList("Hamilton", "Norris", "Gasly", "Tsunoda");
	      Map<String,Integer> lijstBram = new HashMap<String, Integer>(livestand);
	      lijstBram.keySet().retainAll(coureursBram);
	      
	      List<String> coureursRick = Arrays.asList("Hamilton", "Perez", "Stroll", "Latifi");
	      Map<String,Integer> lijstRick = new HashMap<String, Integer>(livestand);
	      lijstRick.keySet().retainAll(coureursRick);	      
	      
	      List<String> coureursThijs = Arrays.asList("Hamilton", "Ricciardo", "Norris", "Schumacher");
	      Map<String,Integer> lijstThijs = new HashMap<String, Integer>(livestand);
	      lijstThijs.keySet().retainAll(coureursThijs);	      
	      
	      arjan = new Team("Arjan", sortByValue(lijstArjan));
	      bram = new Team("Bram", sortByValue(lijstBram));
	      rick = new Team("Rick", sortByValue(lijstRick));
	      thijs = new Team("Thijs", sortByValue(lijstThijs));	      

	      Map<String,Integer> totaal = new HashMap<>();	      
	      totaal.put("Arjan", lijstArjan.values().stream().reduce(0, Integer::sum));
	      totaal.put("Bram", lijstBram.values().stream().reduce(0, Integer::sum));
	      totaal.put("Rick", lijstRick.values().stream().reduce(0, Integer::sum));
	      totaal.put("Thijs", lijstThijs.values().stream().reduce(0, Integer::sum));

	      totaal = sortByValue(totaal);
	      
		  return totaal;
		  
	    }
	
		  public Map<String, Integer> inlezenLiveStand()  {
			  
			  	log.info("inlezenLiveStand started - the time is now {}", dateFormat.format(new Date()));
				
			  	String url = "https://teletekst-data.nos.nl/webplus?p=613-1";
			    try {
					Document doc = Jsoup.connect(url).get();
					
				    Elements coureurs = doc.select("span.yellow");
				    
				    final int start = 4;
				    for (int i=start; i<(10+start); i++) {
	
				  	  
				  	  String coureur = coureurs.get(i).text().split(" ")[1];
				  	  coureur = coureur.split(",")[0];
				  	  
				  	  switch (coureur) {
				  	  case "Pérez":			coureur = "Perez";
				  	  						break;
				  	  case "VERSTAPPEN": 	coureur = "Verstappen";
				  	  						break;
				  	  }
				  	  
//				  	  log.info(i + " " + coureur);
				  	  int pnt = 0;
				  	  
				  	  switch (i-start+1) {
				  	  case 1: 	pnt = 25;
				  	  			break;
				  	  case 2:	pnt = 18;
				  	  			break;
				  	  case 3:	pnt = 16;
				  	  			break;
				  	  case 4:	pnt = 12;
				  	  			break;
				  	  case 5: 	pnt = 10;
				  	  			break;
				  	  case 6:	pnt = 8;
				  	  			break;
				  	  case 7:	pnt = 6;
				  	  			break;
				  	  case 8:	pnt = 4;
				  	  			break;			  	  	
				  	  case 9:	pnt = 2;
				  	  			break;
				  	  case 10:	pnt = 1;
				  	  			break;
		  	  			}
//				  	  log.info(" " + coureur + " " + pnt);
				  	  livestand.put(coureur, pnt);
				
				    }	    	
				  	
				    return livestand;
			    }
			    catch (Exception e) {
			    	log.info("Geen input NOS 613");
			    	livestand.put("Test", 0);
			    	return livestand;
			    }
		  }

		    public <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
		        List<Entry<K, V>> list = new ArrayList<>(map.entrySet());
		        list.sort(Entry.comparingByValue(Comparator.reverseOrder()));

		        Map<K, V> result = new LinkedHashMap<>();
		        for (Entry<K, V> entry : list) {
		            result.put(entry.getKey(), entry.getValue());
		        }

		        return result;
		    }

}

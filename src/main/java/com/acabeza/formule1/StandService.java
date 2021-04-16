package com.acabeza.formule1;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class StandService {
	
		private static final Logger log = LoggerFactory.getLogger(StartControllerService.class);	 
	    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM HH:mm:ss");
	    
	    public static Team arjan;
	    public static Team bram;
	    public static Team rick;
	    public static Team thijs;
	    
	    
	    public Map<String, Integer> bepaalStand() throws Exception {
	    	log.info("bepaalStand() started");
	      
	      List<String> coureursArjan = Arrays.asList("Verstappen", "Perez", "Ricciardo", "Schumacher");
	      Map<String,Integer> lijstArjan = new HashMap<String, Integer>(StartControllerService.stand);
	      lijstArjan.keySet().retainAll(coureursArjan);

	      List<String> coureursBram = Arrays.asList("Hamilton", "Norris", "Gasly", "Tsunoda");
	      Map<String,Integer> lijstBram = new HashMap<String, Integer>(StartControllerService.stand);
	      lijstBram.keySet().retainAll(coureursBram);
	      
	      List<String> coureursRick = Arrays.asList("Hamilton", "Perez", "Stroll", "Latifi");
	      Map<String,Integer> lijstRick = new HashMap<String, Integer>(StartControllerService.stand);
	      lijstRick.keySet().retainAll(coureursRick);	      
	      
	      List<String> coureursThijs = Arrays.asList("Hamilton", "Ricciardo", "Norris", "Schumacher");
	      Map<String,Integer> lijstThijs = new HashMap<String, Integer>(StartControllerService.stand);
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

	      lijstArjan.entrySet()
	      .stream()
	      .sorted((Map.Entry.comparingByValue(Comparator.reverseOrder())));	      

	      totaal = sortByValue(totaal);
	      
		  return totaal;
		  
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

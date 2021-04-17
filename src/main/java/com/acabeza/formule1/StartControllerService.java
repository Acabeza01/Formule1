package com.acabeza.formule1;

import java.text.SimpleDateFormat;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StartControllerService {
	
	@Autowired
	StartService startService;

	private static final Logger log = LoggerFactory.getLogger(Formule1Application.class);	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM HH:mm:ss");	


	@GetMapping("/")
	public String start (Model model) throws Exception {
		
		Map<String, Integer> start = startService.inlezenBestand();
		model.addAttribute("start", start);
		
		return "start";
	}	

	@GetMapping("/start")
	public String inlezen (Model model) throws Exception {
		
		Map<String, Integer> start = startService.inlezenStand();
		model.addAttribute("start", start);
		
		return "start";
	}	
	
}

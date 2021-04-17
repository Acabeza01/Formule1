package com.acabeza.formule1;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LiveController {
	
	@Autowired
	private LiveService liveService;
	
	@GetMapping({"/live"})
	public String live (Model model) throws Exception {
		
		Map<String, Integer> stand = liveService.live();
		model.addAttribute("stand", stand);
		return "live";

	}

	
	
}

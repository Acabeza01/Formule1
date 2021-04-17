package com.acabeza.formule1;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class StandController {
	
	@Autowired
	private StandService standService;
	
	@GetMapping({"/stand"})
	public String stand (Model model) throws Exception {
		
		Map<String, Integer> stand = standService.bepaalStand();
		model.addAttribute("stand", stand);
		return "stand";

	}

	
	
}

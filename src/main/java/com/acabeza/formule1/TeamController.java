package com.acabeza.formule1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;


@Controller
public class TeamController {
	
	@Autowired
	private TeamService teamService;
	
	@GetMapping("/teams")
	public String teams (Model model) throws Exception {
		
		List<Team> teams = teamService.getTeams();
		model.addAttribute("teams", teams);
		return "teams";

	}

	
	
}

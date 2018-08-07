package com.dds.journal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.dds.journal.service.JournalService;
import com.dds.journal.service.UserService;

@Controller
public class HomeController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JournalService journalService;
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("users", userService.getUsers());
		model.addAttribute("journals", journalService.getJournals());
		return "index";
	}
	
	@GetMapping("/play")
	public String play(Model model) {
		
		return "play";
	}
}

package com.dds.journal.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.dds.journal.domain.Journal;
import com.dds.journal.service.JournalService;
import com.dds.journal.service.StorageService;
import com.dds.journal.service.UserService;

@Controller
public class JournalController {
	
	private JournalService journalService;
	private UserService userService;
	private StorageService storageService;
	
	@Autowired
	public JournalController(JournalService journalService, UserService userService, StorageService storageService) {
		super();
		this.journalService = journalService;
		this.userService = userService;
		this.storageService = storageService;
	}
	
	List<String> errors = new ArrayList<String>();
	List<String> messages = new ArrayList<String>();
	
	@GetMapping("/journals")
	public String journals(Model model) {
		model.addAttribute("journals", journalService.getJournals());
		return "journal/journals";
	}
	
	@GetMapping(value="/json/journals", produces= {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public @ResponseBody Iterable<Journal> journals() {
		return journalService.getJournals();
	}
	
	@GetMapping("/journals/new")
	public String newJournal(Model model) {
		model.addAttribute("journal", new Journal());
		return "journal/journal_new";
	}
	
	@PostMapping("journals/new")
	public String saveJournal(@Valid Journal journal , BindingResult results, @RequestParam("pics") MultipartFile[] photos,  Model model, Principal principal ) {
		
		if(results.hasErrors()) {
			for(ObjectError error : results.getAllErrors()) {
				errors.add(error.getDefaultMessage());
			}
			model.addAttribute("errors", errors);
			return "journal_new";
		}
		String[] imageNames = new String[photos.length];
		for(int i = 0; i < photos.length; i++) {
			imageNames[i] = photos[i].getOriginalFilename();			
		}
		journal.setPhotos(imageNames);
		journal.setUser(userService.findByUsername(principal.getName()));
		Journal newJournal = journalService.saveJournal(journal);
		storageService.store(newJournal, newJournal.getId().toString(), Arrays.asList(photos));
		return "redirect:/journals/" + newJournal.getId();
	}
	
	@GetMapping("journals/{id}")
	public String journal(@PathVariable("id") Long id, Model model, Principal principal) {
		Journal journal = journalService.getJournal(id);
		if(null == journal) {
			errors.add("Journal not found!");
			model.addAttribute("errors", errors);
			model.addAttribute("journals", journalService.getJournals());
			return "journal/journals";
		}
		if(null != principal) {
			if(journal.getUser().getUsername().equalsIgnoreCase(principal.getName())) {
				model.addAttribute("editable", true);
			}
		}		
		model.addAttribute("journal", journal);
		return "journal/journal";
	}
	
	@GetMapping("journals/edit/{id}")
	public String editJournal(@PathVariable("id") Long id, Model model) {
		Journal journal = journalService.getJournal(id);
		model.addAttribute("journal", journal);
		return "journal/journal_edit";
	}
}

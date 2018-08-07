package com.dds.journal.controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.dds.journal.domain.ChatMessage;
import com.dds.journal.service.UserService;

@Controller
public class ChatController {
	
	private SimpMessagingTemplate template;
	private UserService userService;

	@Autowired
	public ChatController(SimpMessagingTemplate template, UserService userService) {
		this.template = template;
		this.userService = userService;
	}

	@GetMapping("/chat")
	public String chat(Model model) {
		model.addAttribute("users", userService.getUsers());
		return "chat/chat";
	}
	
	@GetMapping("/chat/private")
	public String privateChat(Model model) {
		model.addAttribute("users", userService.getUsers());
		return "chat/private";
	}
	
	@MessageMapping("/send")
	public void processMessage(ChatMessage incomingMessage, Principal principal){
		incomingMessage.setAuthor(principal.getName());
		String reply = new SimpleDateFormat("hh:mm a z").format(new Date()) + " - @" + incomingMessage.getAuthor() + ": " + incomingMessage.getBody();
		template.convertAndSend("/chat", reply);
	}
	
	@MessageMapping("/private")
	public void processPrivateMessage(ChatMessage incomingMessage, Principal principal){
		incomingMessage.setAuthor(principal.getName());
		System.out.println("Message: " + incomingMessage);
		template.convertAndSendToUser(incomingMessage.getAuthor(), "/queue/notifications", incomingMessage);
		for(String recipient : incomingMessage.getRecipients()) {
			template.convertAndSendToUser(recipient, "/queue/notifications", incomingMessage);
		}	
	}
}

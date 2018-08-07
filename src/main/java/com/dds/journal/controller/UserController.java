package com.dds.journal.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.dds.journal.constants.Constants;
import com.dds.journal.domain.Role;
import com.dds.journal.domain.User;
import com.dds.journal.repository.RoleRepository;
import com.dds.journal.service.EmailService;
import com.dds.journal.service.StorageService;
import com.dds.journal.service.UserService;
import com.dds.journal.tools.AppTool;
import com.nulabinc.zxcvbn.Strength;
import com.nulabinc.zxcvbn.Zxcvbn;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private StorageService storageService;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	List<String> errors = new ArrayList<>();
	List<String> messages = new ArrayList<>();
	
	@GetMapping("/users/{id}")
	public String user(@PathVariable("id") Long id, Model model, Principal principal) {
		User user = userService.findByUsername(principal.getName());
		model.addAttribute("user", user);
		return "user/user";
	}
	
	@GetMapping("/registration")
	public String registration(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("states", Constants.US_STATES());
		return "register";
	}
	
	@PostMapping("/registration")
	public String registerUser(@Valid User user, BindingResult results, @RequestParam("profile_image") MultipartFile profileImage, 
								 Model model, HttpServletRequest request) {	
		try {			
				if(results.hasErrors()) {
					for(ObjectError error : results.getAllErrors()) {
						errors.add(error.getDefaultMessage());
					}
					model.addAttribute("errors", errors);
					model.addAttribute("states", Constants.US_STATES());
					return "register";
				}
				
				Zxcvbn passwordCheck = new Zxcvbn();			
				Strength strength = passwordCheck.measure(user.getPassword());			
				if (strength.getScore() < 3) {			
					errors.add("Your password is too weak!  Create a stronger one!");
					model.addAttribute("errors", errors);
					return "register";
				}
				
				Set<Role> defaultRole = new HashSet<>();
				defaultRole.add(roleRepository.findByRole("USER"));
				user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
				user.setRoles(defaultRole);
				user.setConfirmationToken(UUID.randomUUID().toString());
				user.setImage( (null == profileImage) ? Constants.DEFAULT_PROFILE_IMAGE : AppTool.buildUserProfileImagePath(user, profileImage.getOriginalFilename()));
				user.setEnabled(false);
				
				String appURL = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
				SimpleMailMessage registrationEmail = new SimpleMailMessage();
				registrationEmail.setTo(user.getEmail());
				registrationEmail.setSubject("Registration Confirmation");
				registrationEmail.setText("To Confirm you e-mail address, please click the link below: \n" 
											+ appURL + "/confirm?token=" 
											+ user.getConfirmationToken());
				registrationEmail.setFrom(Constants.APP_EMAIL);
				emailService.sendEmail(registrationEmail);		
				model.addAttribute("messages", "A confirmation e-mail has been sent to " + user.getEmail());			
				
				userService.saveUser(user);			
				storageService.store(user, user.getUsername(), Arrays.asList(profileImage));
		}
		catch(Exception e) {
			//TODO
			e.printStackTrace();
			errors.add(e.getMessage());
			model.addAttribute("errors", errors);
			model.addAttribute("states", Constants.US_STATES());
			return "register";
		}		
		return "login";
	}
	
	@GetMapping("/account")
	public String account(Model model, Principal principal) {
		User user = userService.findByUsername(principal.getName());
		model.addAttribute("user", user);
		return "user/account";
	}
	
	@GetMapping("/confirm")
	public String confirm(@RequestParam("token") String token, Model model) {
		User user = userService.findByConfirmationToken(token);		
		if(null == user) {
			errors.add("Oops! Invalid confirmation link!");
			model.addAttribute("errors", errors);
			return "confirm";
		}
		else {
			messages.add("Congrats! Your email has been confirmed, now you may login!");
			model.addAttribute("messages", messages);
			user.setEnabled(true);
			userService.saveUser(user);
			return "login";
		}		
	}
}

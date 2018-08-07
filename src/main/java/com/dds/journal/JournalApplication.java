package com.dds.journal;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.dds.journal.configuration.StorageProperties;
import com.dds.journal.constants.Constants;
import com.dds.journal.domain.Journal;
import com.dds.journal.domain.Role;
import com.dds.journal.domain.User;
import com.dds.journal.repository.RoleRepository;
import com.dds.journal.repository.UserRepository;
import com.dds.journal.service.StorageService;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class JournalApplication {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Bean
    CommandLineRunner init(StorageService storageService) {
        return (args) -> {
            storageService.deleteAll();
            storageService.init();
        };
    }
	
	@Bean
	InitializingBean saveData(){
		return()->{
						
			roleRepository.save(Arrays.asList(new Role("USER"), new Role("ADMIN"), new Role("OWNER")));
			
			Set<Role> denorroRoles = new HashSet<>();
			denorroRoles.add(roleRepository.findByRole("OWNER"));
			
			Journal workJournal = new Journal("Daily Work Journal", "Just detailing a few things that go on day to day...");
			Journal vacationJournal = new Journal("2017 Vacation Journal", "My adventures on vacation :) ");			
			
			Set<Journal> denorroJournals = new HashSet<>();
			denorroJournals.add(workJournal);
			denorroJournals.add(vacationJournal);
			
			User denorro = new User("Denorro", "Stallworth", "denorro", new BCryptPasswordEncoder().encode("abc123"));
			workJournal.setUser(denorro);
			vacationJournal.setUser(denorro);
			denorro.setRoles(denorroRoles);
			denorro.setJournals(denorroJournals);				
			denorro.setCity("Pine Apple");
			denorro.setEmail("denorrostallworth@gmail.com");
			denorro.setEnabled(true);
			denorro.setConfirmationToken(UUID.randomUUID().toString());
			denorro.setState("Alabama");
			denorro.setImage(Constants.DEFAULT_PROFILE_IMAGE);
			userRepository.save(denorro);
			
			User ace = new User("Ace", "Stallworth", "ace", new BCryptPasswordEncoder().encode("abc123"));
			ace.setRoles(denorroRoles);
			ace.setCity("Pine Apple");
			ace.setEmail("ace@gmail.com");
			ace.setEnabled(true);
			ace.setConfirmationToken(UUID.randomUUID().toString());
			ace.setState("Alabama");
			ace.setImage(Constants.DEFAULT_PROFILE_IMAGE);
			userRepository.save(ace);
			
			User blake = new User("Blake", "Stallworth", "blake", new BCryptPasswordEncoder().encode("abc123"));
			blake.setRoles(denorroRoles);
			blake.setCity("Pine Apple");
			blake.setEmail("blake@yahoo.com");
			blake.setEnabled(true);
			blake.setConfirmationToken(UUID.randomUUID().toString());
			blake.setState("Alabama");
			blake.setImage(Constants.DEFAULT_PROFILE_IMAGE);
			userRepository.save(blake);
			
			User mary = new User("Mary", "Stallworth", "muffie", new BCryptPasswordEncoder().encode("abc123"));
			mary.setRoles(denorroRoles);
			mary.setCity("Pine Apple");
			mary.setEmail("muffiee@gmail.com");
			mary.setEnabled(true);
			mary.setConfirmationToken(UUID.randomUUID().toString());
			mary.setState("Alabama");
			mary.setImage(Constants.DEFAULT_PROFILE_IMAGE);
			userRepository.save(mary);
			
			User santavia = new User("Santavia", "Johnson", "san", new BCryptPasswordEncoder().encode("abc123"));
			santavia.setRoles(denorroRoles);
			santavia.setCity("Pine Apple");
			santavia.setEmail("san@gmail.com");
			santavia.setEnabled(true);
			santavia.setConfirmationToken(UUID.randomUUID().toString());
			santavia.setState("Alabama");
			santavia.setImage(Constants.DEFAULT_PROFILE_IMAGE);
			userRepository.save(santavia);
			
			User meisha = new User("Ametria", "Johnson", "meisha", new BCryptPasswordEncoder().encode("abc123"));
			meisha.setRoles(denorroRoles);
			meisha.setCity("Pine Apple");
			meisha.setEmail("meisha@gmail.com");
			meisha.setEnabled(true);
			meisha.setConfirmationToken(UUID.randomUUID().toString());
			meisha.setState("Alabama");
			meisha.setImage(Constants.DEFAULT_PROFILE_IMAGE);
			userRepository.save(meisha);
			
			User jack = new User("Jack", "Stallworth", "jack", new BCryptPasswordEncoder().encode("abc123"));
			jack.setRoles(denorroRoles);
			jack.setCity("Pine Apple");
			jack.setEmail("jack@gmail.com");
			jack.setEnabled(true);
			jack.setConfirmationToken(UUID.randomUUID().toString());
			jack.setState("Alabama");
			jack.setImage(Constants.DEFAULT_PROFILE_IMAGE);
			userRepository.save(jack);
			
			User mike = new User("Michael", "Stallworth", "mike", new BCryptPasswordEncoder().encode("abc123"));
			mike.setRoles(denorroRoles);
			mike.setCity("Pine Apple");
			mike.setEmail("mike@gmail.com");
			mike.setEnabled(true);
			mike.setConfirmationToken(UUID.randomUUID().toString());
			mike.setState("Alabama");
			mike.setImage(Constants.DEFAULT_PROFILE_IMAGE);
			userRepository.save(mike);
			
			User birdie = new User("Birdie", "Stallworth", "birdie", new BCryptPasswordEncoder().encode("abc123"));
			birdie.setRoles(denorroRoles);
			birdie.setCity("Pine Apple");
			birdie.setEmail("birdiemae@gmail.com");
			birdie.setEnabled(true);
			birdie.setConfirmationToken(UUID.randomUUID().toString());
			birdie.setState("Alabama");
			birdie.setImage(Constants.DEFAULT_PROFILE_IMAGE);
			userRepository.save(birdie);
			
			User jam = new User("Jamichael", "Stallworth", "jam", new BCryptPasswordEncoder().encode("abc123"));
			jam.setRoles(denorroRoles);
			jam.setCity("Pine Apple");
			jam.setEmail("jam@gmail.com");
			jam.setEnabled(true);
			jam.setConfirmationToken(UUID.randomUUID().toString());
			jam.setState("Alabama");
			jam.setImage(Constants.DEFAULT_PROFILE_IMAGE);
			userRepository.save(jam);
			
			User dj = new User("Deshon", "Stallworth", "djdj", new BCryptPasswordEncoder().encode("abc123"));
			dj.setRoles(denorroRoles);
			dj.setCity("Pine Apple");
			dj.setEmail("dj@gmail.com");
			dj.setEnabled(true);
			dj.setConfirmationToken(UUID.randomUUID().toString());
			dj.setState("Alabama");
			dj.setImage(Constants.DEFAULT_PROFILE_IMAGE);
			userRepository.save(dj);
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(JournalApplication.class, args);
	}
}

package org.davo.security;

import org.davo.security.identity.Role;
import org.davo.security.identity.User;
import org.davo.security.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class SecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
	}
	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	@Bean
	CommandLineRunner run(UserService userService){
		return args -> {
			userService.saveRole(new Role(null,"CEO"));
			userService.saveRole(new Role(null, "Admin"));
			userService.saveRole(new Role(null,"Developer"));
			userService.saveRole(new Role(null,"Non_technical"));
			userService.saveRole(new Role(null,"DB_Select_Access"));

			userService.saveUser(new User(null, "Yolanda R. Thomas", "sherverty1999", "Ahthoophie8", new ArrayList<>()));
			userService.saveUser(new User(null, "Connor Foster", "Otigh1942", "nae3ieLeez5", new ArrayList<>()));
			userService.saveUser(new User(null, "Reece Todd", "Sonsiziefall", "Rah9upai8oh", new ArrayList<>()));
			userService.saveUser(new User(null, "Daisy Holloway", "Hinthe", "OP4pheev8r", new ArrayList<>()));

			userService.addRoleToUser("sherverty1999", "CEO");
			userService.addRoleToUser("Otigh1942", "Non_technical");
			userService.addRoleToUser("Otigh1942", "DB_Select_Access");
			userService.addRoleToUser("Sonsiziefall", "Non_technical");
			userService.addRoleToUser("Hinthe", "Developer");
			userService.addRoleToUser("Hinthe", "DB_Select_Access");
		};
	}
}

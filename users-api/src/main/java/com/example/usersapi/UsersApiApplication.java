package com.example.usersapi;

import com.example.usersapi.exception.DuplicateUserException;
import com.example.usersapi.model.User;
import com.example.usersapi.model.UserRole;
import com.example.usersapi.service.UserRoleService;
import com.example.usersapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class UsersApiApplication {

	@Autowired
	UserRoleService userRoleService;

	@Autowired
	UserService userService;

	@Bean
	public User createDba() throws DuplicateUserException {
		User user = new User();
		user.setUsername("DBAuser");
		user.setEmail("DBA@dba.com");
		user.setPassword("DBAuser");
		userService.createUser(user);

		UserRole userRole = new UserRole();
		userRole.setName("ROLE_DBA");
		userRoleService.createUserRole(userRole);

		user.addUserRole(userRole);
		return user;
	}

	@RequestMapping("/")
	public String home() {
		return "some users";
	}

	public static void main(String[] args) {
		SpringApplication.run(UsersApiApplication.class, args);
	}

}

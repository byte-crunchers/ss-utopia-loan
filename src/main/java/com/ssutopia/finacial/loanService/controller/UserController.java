package com.ssutopia.finacial.loanService.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssutopia.finacial.loanService.entity.User;
import com.ssutopia.finacial.loanService.service.UserServiceImpl;

/*
 * This controller is just for testing.
 * User data should be retrieved from the users microservice.
 */

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserServiceImpl userService;

	// get 1 user by id
	@GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public Optional<User> getUser(@PathVariable Long id) {
		return userService.findUserById(id);
	}

}

package com.fernando.email.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fernando.email.model.UserMail;
import com.fernando.email.service.EmailService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/userMain")
public class UserMailController {
	
	@Autowired
	private EmailService emailService;
	
	@Operation(summary = "Save UserMail in the Databse")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",description="Save UserMail successfully"),
			@ApiResponse(responseCode = "400", description="UserMail don't save",
			content=@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,examples=@ExampleObject(value="BAD_REQUEST"))),
			@ApiResponse(responseCode="500",description="An exception was generated",
			content=@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,examples=@ExampleObject(value="INTERNAL_SERVER_ERROR")))
	})
	@PostMapping("/save")
	public ResponseEntity<String> saveUser(@RequestBody UserMail usermail) {
		
		try {
			emailService.saveUserMail(usermail);
		} catch (Exception e) {
			return new ResponseEntity<String>("user don't save: "+e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<String>("user save",HttpStatus.OK);
	}
	@Operation(summary = "Delete UserMail in the Databse")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",description="Delete UserMail successfully"),
			@ApiResponse(responseCode = "400", description="UserMail don't delete",
			content=@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,examples=@ExampleObject(value="BAD_REQUEST"))),
			@ApiResponse(responseCode="500",description="An exception was generated",
			content=@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,examples=@ExampleObject(value="INTERNAL_SERVER_ERROR")))
	})
	@DeleteMapping("delete/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable(value = "id") Long id){
		try {
			emailService.deleteUserMail(id);
		} catch (Exception e) {
			return new ResponseEntity<String>("user don't delete: "+ e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("user delete",HttpStatus.OK);
	}
	
	
}

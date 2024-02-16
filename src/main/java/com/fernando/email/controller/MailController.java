package com.fernando.email.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fernando.email.model.MailStructure;
import com.fernando.email.model.UserMail;
import com.fernando.email.service.EmailService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/mail")
public class MailController {
	
	@Autowired
	private EmailService emailService;
	
	@Operation(summary = "Send email to one user")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",description="Email send successfully"),
			@ApiResponse(responseCode = "400", description="email don't send",
			content=@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,examples=@ExampleObject(value="BAD_REQUEST"))),
			@ApiResponse(responseCode="500",description="An exception was generated",
			content=@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,examples=@ExampleObject(value="INTERNAL_SERVER_ERROR")))
	})
	@PostMapping("/send/{email}")
	public ResponseEntity<String> sendEmail(@PathVariable(value = "email") String mail,@RequestBody MailStructure mailStructure) {
		try {
			emailService.sendEmail(mail, mailStructure);
		} catch (Exception e) {
			return new ResponseEntity<String>("email don't send:"+ e.getMessage()+ "motivo: "+ e.getCause(),HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<String>("email send",HttpStatus.OK);
	}
	@Operation(summary = "Send email to multiple users")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",description="Emails send successfully"),
			@ApiResponse(responseCode = "400", description="email don't send",
			content=@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,examples=@ExampleObject(value="BAD_REQUEST"))),
			@ApiResponse(responseCode="500",description="An exception was generated",
			content=@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,examples=@ExampleObject(value="INTERNAL_SERVER_ERROR")))
	})
	@PostMapping("/send/id/{groupId}")
	public ResponseEntity<String> sendEmailAll(@PathVariable(value = "groupId") Long groupId,@RequestBody MailStructure mailStructure) {
		try {
			emailService.sendEmailAll(groupId, mailStructure);
		} catch (Exception e) {
			return new ResponseEntity<String>("email don't send:"+ e.getMessage()+ "motivo: "+ e.getCause(),HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("emails send",HttpStatus.OK);
	}
	
	

}

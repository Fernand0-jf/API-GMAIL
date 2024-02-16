package com.fernando.email.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fernando.email.model.UserMail;
import com.fernando.email.service.EmailService;
@ExtendWith(MockitoExtension.class)
class UserMailControllerTest {
	
	@InjectMocks
	private UserMailController userMailController;
	@Mock
	private EmailService emailService;
	
	@Test
	void saveUserSucess() {
		UserMail userMail = userMain1();
		ResponseEntity<String> result = userMailController.saveUser(userMail);
		assertEquals(HttpStatus.OK, result.getStatusCode());
		assertTrue(result.getBody().contains("user save"));
	}
	@Test
	void saveUserFail() {
		UserMail userMail = userMain1();
		when(emailService.saveUserMail(userMail)).thenThrow(new RuntimeException());
		ResponseEntity<String> result = userMailController.saveUser(userMail);
		assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
		assertTrue(result.getBody().contains("user don't save:"));
	}
	@Test
	void deleteUserSucess() {
		UserMail userMail = userMain1();
		ResponseEntity<String> result = userMailController.deleteUser(1L);
		assertEquals(HttpStatus.OK, result.getStatusCode());
		assertTrue(result.getBody().contains("user delete"));
	}
	@Test
	void deleteUserFail() {
		UserMail userMail = userMain1();
		doThrow(new RuntimeException()).when(emailService).deleteUserMail(1L);
		ResponseEntity<String> result = userMailController.deleteUser(1L);
		assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
		assertTrue(result.getBody().contains("user don't delete"));
	}
	public UserMail userMain1() {
		UserMail userMail = new UserMail();
		userMail.setId(1L);
		userMail.setEmail("test@k.com");
		userMail.setGroupId(2L);
		userMail.setUsername("teste");
		
		return userMail;
	}
}

package com.fernando.email.controller;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fernando.email.model.MailStructure;
import com.fernando.email.model.UserMail;
import com.fernando.email.service.EmailService;
@ExtendWith(MockitoExtension.class)
class MailControllerTest {

	@Mock
	private EmailService emailService;
	@InjectMocks
	private MailController mailController;
	
	@Test
	void sendEmailSucess(){
		String mail = "test@k.com";
		MailStructure mailStructure = new MailStructure("title","Subject");
		ResponseEntity<String> result =  mailController.sendEmail(mail,mailStructure);
		assertEquals(HttpStatus.OK, result.getStatusCode());
		assertEquals("email send", result.getBody());
	}
	@Test
	void sendEmailFail(){
		String mail = "test@k.com";
		MailStructure mailStructure = new MailStructure("title","Subject");
		doThrow(new RuntimeException()).when(emailService).sendEmail(mail, mailStructure);
		ResponseEntity<String> result =  mailController.sendEmail(mail,mailStructure);
		assertEquals(HttpStatus.BAD_REQUEST, mailController.sendEmail(mail, mailStructure).getStatusCode());
		assertTrue(result.getBody().contains("email don't send:"));
	}
	
	@Test 
	void  sendEmailAllSucess() {
		MailStructure mailStructure = new MailStructure("title","Subject");
		 ResponseEntity<String> result = mailController.sendEmailAll(1L, mailStructure);
		assertEquals(HttpStatus.OK, result.getStatusCode());
		assertEquals("emails send", result.getBody());
		
	}
	@Test 
	void  sendEmailAllFail() {
		String mail = "test@k.com";
		MailStructure mailStructure = new MailStructure("title","Subject");
		doThrow(new RuntimeException()).when(emailService).sendEmailAll(1L, mailStructure);;
		ResponseEntity<String> result =  mailController.sendEmailAll(1L,mailStructure);
		assertEquals(HttpStatus.BAD_REQUEST, mailController.sendEmailAll(1L, mailStructure).getStatusCode());
		assertTrue(result.getBody().contains("email don't send:"));
		
	}
	public List<UserMail> userMainList() {
		List<UserMail> list = new ArrayList<>();
		UserMail userMail = new UserMail();
		userMail.setEmail("test@k.com");
		userMail.setGroupId(2L);
		userMail.setUsername("teste");
		list.add(userMail);
		UserMail userMail2= new UserMail();
		userMail2.setEmail("test2@k.com");
		userMail2.setGroupId(2L);
		userMail2.setUsername("teste2");
		list.add(userMail2);
		return list;
	}
}

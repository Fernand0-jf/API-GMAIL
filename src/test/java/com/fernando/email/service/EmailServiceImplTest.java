package com.fernando.email.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.fernando.email.model.MailStructure;
import com.fernando.email.model.UserMail;
import com.fernando.email.repository.UserMailRepository;

@ExtendWith(MockitoExtension.class)
class EmailServiceImplTest {
	
	@InjectMocks
	private EmailServiceImpl emailServiceImpl;
	@Mock
	private JavaMailSender mailSender;
	
	@Mock
	private UserMailRepository userMailRepository;
	
	
	@Test
	void sendEmailSucess( ){
		String mail = "test@k.com";
		MailStructure mailStructure = new MailStructure("title","subject");
		emailServiceImpl.sendEmail(mail, mailStructure);
		verify(mailSender, times(1)).send(any(SimpleMailMessage.class));
		
	}
	@Test
	void sendEmailFail( ){
		String mail = "test@k.com";
		MailStructure mailStructure = new MailStructure("title","subject");
		doThrow(new MailSendException("failure when sending the message")).when(mailSender).send(any(SimpleMailMessage.class));
		assertThrows(RuntimeException.class, ()-> emailServiceImpl.sendEmail(mail, mailStructure)); 
		
	}
	@Test
	void sendEmailAllSucess() {
		List<UserMail> list = userMainList();
		MailStructure mailStructure = new MailStructure("title","subject");
		when(userMailRepository.findAllGroupId(anyLong())).thenReturn(list);
		emailServiceImpl.sendEmailAll(anyLong(), mailStructure);
		verify(mailSender, times(2)).send(any(SimpleMailMessage.class));
	}
	@Test
	void sendEmailAllFail1() {
		List<UserMail> list = null;
		MailStructure mailStructure = new MailStructure("title","subject");
		when(userMailRepository.findAllGroupId(1L)).thenReturn(list);
		assertThrows(RuntimeException.class, ()->emailServiceImpl.sendEmailAll(1L, mailStructure));
		
	}
	@Test
	void sendEmailAllFail2() {
		List<UserMail> list = userMainList();;
		MailStructure mailStructure = new MailStructure("title","subject");
		doThrow(new RuntimeException()).when(mailSender).send(any(SimpleMailMessage.class));
		when(userMailRepository.findAllGroupId(1L)).thenReturn(list);
		assertThrows(RuntimeException.class, ()->emailServiceImpl.sendEmailAll(1L, mailStructure));
		
	}
	@Test
	void saveUserMailSucess() {
		UserMail userMail= userMain1();
		when(userMailRepository.save(userMail)).thenReturn(userMail);
		UserMail result = userMailRepository.save(userMail);
		assertEquals(userMail, result);
	}
	@Test
	void saveUserMailFail() {
		UserMail userMail= userMain1();
		UserMail result = userMailRepository.save(userMail);
		when(userMailRepository.save(null)).thenThrow(new IllegalArgumentException());
		assertThrows(RuntimeException.class,()->emailServiceImpl.saveUserMail(null));
		
	}
	@Test
	void deleteUserMailSucess( ){
		UserMail userMail= userMain1();
		when(userMailRepository.findById(1L)).thenReturn(Optional.of(userMail));
		emailServiceImpl.deleteUserMail(1L);
		verify(userMailRepository,times(1)).deleteById(1L);
	}
	@Test
	void deleteUserMailFail( ){
		UserMail userMail= userMain1();
		when(userMailRepository.findById(1L)).thenThrow(new RuntimeException());
		assertThrows(RuntimeException.class, ()->emailServiceImpl.deleteUserMail(1L));
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
	public UserMail userMain1() {
		UserMail userMail = new UserMail();
		userMail.setId(1L);
		userMail.setEmail("test@k.com");
		userMail.setGroupId(2L);
		userMail.setUsername("teste");
		
		return userMail;
	}
	
}

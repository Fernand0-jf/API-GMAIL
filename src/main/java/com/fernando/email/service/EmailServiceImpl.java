package com.fernando.email.service;

import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.fernando.email.model.MailStructure;
import com.fernando.email.model.UserMail;
import com.fernando.email.repository.UserMailRepository;

@Service
public class EmailServiceImpl implements EmailService{
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private UserMailRepository userMailRepository;
	
	@Value("$(noreply@gmail.com)")
	private String fromMail;
	
	
	@Override
	public void sendEmail(String mail, MailStructure mailStructure) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom(fromMail);
		simpleMailMessage.setSubject(mailStructure.getSubject());
		simpleMailMessage.setText(mailStructure.getMessage());
		simpleMailMessage.setTo(mail);
		try {
			mailSender.send(simpleMailMessage);
		} catch (MailException e) {
			throw new RuntimeException("Email don't send",e.getCause());
		}
		//caso nao der erro lanca Mail exception, dps tratar
	}
	@Override
	public void sendEmailAll( Long groupId,MailStructure mailStructure) {
		List<UserMail> usersMail = userMailRepository.findAllGroupId(groupId);
		
		if(usersMail.isEmpty()) {
			throw new RuntimeException("lista vazia para este groupId: " + groupId);
		}
		for(UserMail mail : usersMail) {
			try {
				this.sendEmail(mail.getEmail(),mailStructure);
			} catch (Exception e) {
				throw new RuntimeException("Email nao enviado:" + mail.getEmail(),e.getCause());
			}
		}
		
	}
	
	@Override
	public UserMail saveUserMail(UserMail userMail) {
		return userMailRepository.save(userMail);
	}
	
	@Override
	public void deleteUserMail(Long id) {
		
		userMailRepository.findById(id).orElseThrow(()-> new RuntimeException("usuario nao encontrado id: " + id));
		
		userMailRepository.deleteById(id);
		
	}
	
	
	
	
	
}

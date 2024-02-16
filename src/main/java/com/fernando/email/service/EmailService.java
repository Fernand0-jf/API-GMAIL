package com.fernando.email.service;



import org.springframework.stereotype.Service;

import com.fernando.email.model.MailStructure;
import com.fernando.email.model.UserMail;

public interface EmailService {
	 void sendEmail(String mail,MailStructure mailStructure);
	 
	 void sendEmailAll(Long groupId,MailStructure mailStructure);
	 
	 UserMail saveUserMail(UserMail userMail);
	 
	 void deleteUserMail(Long id);
}

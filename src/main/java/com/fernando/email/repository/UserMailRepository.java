package com.fernando.email.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fernando.email.model.UserMail;


public interface UserMailRepository extends JpaRepository<UserMail, Long>{
	
	@Query("SELECT u FROM UserMail u WHERE u.groupId = :groupId")
	List<UserMail> findAllGroupId(Long groupId);
	
}

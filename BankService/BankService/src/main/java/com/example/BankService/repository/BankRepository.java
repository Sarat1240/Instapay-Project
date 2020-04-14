package com.example.BankService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.BankService.entity.User;

@Repository
public interface BankRepository extends JpaRepository<User, Integer> {
	
	@Query(value = "select u from User u where u.phoneNum=?1")
	User findByPhoneNum(long phoneNum);

}

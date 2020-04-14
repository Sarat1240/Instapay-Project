package com.example.InstaPay.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.InstaPay.entity.Registration;

public interface InstaPayRepository extends JpaRepository<Registration, Integer> {

	Optional<Registration> findByPhoneNum(long phoneNum);

}

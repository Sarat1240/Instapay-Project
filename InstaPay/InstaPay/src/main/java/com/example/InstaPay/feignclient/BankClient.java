package com.example.InstaPay.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.InstaPay.entity.Account;
import com.example.InstaPay.exception.AccountNotFoundException;

@FeignClient(name="http://bank-service/banks")
public interface BankClient {
	
	@GetMapping("/info")
	public String getInfo();
	
	@GetMapping(value = "/{phoneNum}")
	public ResponseEntity<Account> checkIfAccountExists(@PathVariable long phoneNum) throws AccountNotFoundException;
	
	@PostMapping(value = "/{fromPhoneNum}/{toPhoneNum}/{amount}")
	public String transferFunds(@PathVariable long fromPhoneNum,@PathVariable long toPhoneNum,@PathVariable long amount);


}

package com.example.BankService.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.BankService.entity.Account;
import com.example.BankService.entity.User;
import com.example.BankService.exception.AccountNotFoundException;
import com.example.BankService.exception.InsufficientFundsException;
import com.example.BankService.service.BankService;

@RestController
@RequestMapping(value = "/banks")
public class BankController {
	
	@Autowired
	private BankService bankService;
	
	@Autowired
	Environment environment;
	
	@GetMapping("/info")
	public String getInfo() {
		String port = environment.getProperty("local.server.port");
		return "From server "+port;
	}
	

	@GetMapping(value = "/{phoneNum}")
	public ResponseEntity<Account> checkIfAccountExists(@PathVariable long phoneNum) throws AccountNotFoundException
	{
		User user =  bankService.getAccountByPhoneNum(phoneNum);
		System.out.println("user"+user);
		if(user==null)
		{
			throw new AccountNotFoundException("Bank Account doesn't exist for the given Phone number");
		}
		return new ResponseEntity<Account>(user.getAcc(),new HttpHeaders(),HttpStatus.OK);
	}
	
	@PostMapping(value = "/{fromPhoneNum}/{toPhoneNum}/{amount}")
	public String transferFunds(@PathVariable long fromPhoneNum,@PathVariable long toPhoneNum,@PathVariable long amount)throws InsufficientFundsException
	{
		User sourceUser = bankService.getAccountByPhoneNum(fromPhoneNum);
		User destinationUser = bankService.getAccountByPhoneNum(toPhoneNum);
		return bankService.performFundTransfer(sourceUser,destinationUser,amount);
		
		
	}

}

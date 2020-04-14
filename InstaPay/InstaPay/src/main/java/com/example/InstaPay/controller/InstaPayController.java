package com.example.InstaPay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.InstaPay.constant.InstaPayConstant;
import com.example.InstaPay.entity.Account;
import com.example.InstaPay.exception.AccountNotFoundException;
import com.example.InstaPay.feignclient.BankClient;
import com.example.InstaPay.service.InstaPayService;

@RestController
@RequestMapping(value = "instapay")
public class InstaPayController {
	
	@Autowired
	InstaPayService instapayService;
	
	@Autowired
	private BankClient bankClient;
	
	@GetMapping("/info")
	public String getInfo() {
		return bankClient.getInfo();
	}
	
	@GetMapping(value = "/{phoneNum}")
	public String registerPhoneNumber(@PathVariable long phoneNum)  throws AccountNotFoundException
	{
		  ResponseEntity<Account> acc = bankClient.checkIfAccountExists(phoneNum); 		  
		  long accountNumber = acc.getBody().getAccountNumber();
		  int statusCode =  acc.getStatusCodeValue();
		 
		 
		  if(statusCode==200)
			return instapayService.registerInInstaPay(phoneNum,accountNumber);
		  throw new AccountNotFoundException("Bank Account doesn't exist for the given Phone number");
	}
	
	@PostMapping(value = "/{fromPhoneNum}/{toPhoneNum}/{amount}")
	public String transferFunds(@PathVariable long fromPhoneNum,@PathVariable long toPhoneNum,@PathVariable long amount)  throws AccountNotFoundException
	{
		String status =  instapayService.findByPhoneNumber(fromPhoneNum,toPhoneNum,amount);
		if(InstaPayConstant.ACCOUNT_EXISTS_STATUS.equals(status))
		{
			status  = bankClient.transferFunds(fromPhoneNum,toPhoneNum,amount);
		}
		if(InstaPayConstant.FUNDS_TRANSFER_SUCCESS.equals(status))
		{
			instapayService.logTransactionDetails(fromPhoneNum,toPhoneNum,amount);
		}
		
		
	
		 return status;
	}

}

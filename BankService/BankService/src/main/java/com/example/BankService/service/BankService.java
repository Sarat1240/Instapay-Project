package com.example.BankService.service;

import com.example.BankService.entity.User;
import com.example.BankService.exception.InsufficientFundsException;

public interface BankService {

	User getAccountByPhoneNum(long phoneNum);

	String performFundTransfer(User sourceUser, User destinationUser,long amount) throws InsufficientFundsException;

	
	
}

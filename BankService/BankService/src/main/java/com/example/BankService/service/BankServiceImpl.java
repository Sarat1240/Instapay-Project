package com.example.BankService.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BankService.entity.Account;
import com.example.BankService.entity.Transaction;
import com.example.BankService.entity.User;
import com.example.BankService.exception.InsufficientFundsException;
import com.example.BankService.repository.AccountRepository;
import com.example.BankService.repository.BankRepository;
import com.example.BankService.repository.TransactionRepository;

@Service
public class BankServiceImpl implements BankService {

	@Autowired
	private BankRepository bankRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Override
	public User getAccountByPhoneNum(long phoneNum) {
		// TODO Auto-generated method stub
		return bankRepository.findByPhoneNum(phoneNum);
		
	}
	@Override
	public String performFundTransfer(User sourceUser, User destinationUser,long amount) throws InsufficientFundsException
	{
		Account srcAccount = sourceUser.getAcc();
		Account destAccount = destinationUser.getAcc();
		if(srcAccount.getCurrentBalance()>=amount)
		{
			long remainingBal = srcAccount.getCurrentBalance()-amount;
			srcAccount.setCurrentBalance(remainingBal);
			accountRepository.save(srcAccount);
			
			long newBal = destAccount.getCurrentBalance()+amount;
			destAccount.setCurrentBalance(newBal);
			accountRepository.save(destAccount);
			
			Transaction tx1 = new Transaction();
			tx1.setAccountNumber(srcAccount.getAccountNumber());
			tx1.setPhoneNum(sourceUser.getPhoneNum());
			tx1.setAmount(amount);
			tx1.setTransactionType("Debit");
			tx1.setDate(new Date().toString());
			
			Transaction tx2 = new Transaction();
			tx2.setAccountNumber(destAccount.getAccountNumber());
			tx2.setPhoneNum(destinationUser.getPhoneNum());
			tx2.setAmount(amount);
			tx2.setTransactionType("Credit");
			tx2.setDate(new Date().toString());
			
			transactionRepository.save(tx1);
			transactionRepository.save(tx2);
		}
		else
		{
			throw new InsufficientFundsException("There is no sufficient funds to tranfer from given source account. Please try with a lesser amount.");
		}
		return "Funds transferred successfully";
		
	}

}

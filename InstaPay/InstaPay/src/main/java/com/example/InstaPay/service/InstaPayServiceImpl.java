package com.example.InstaPay.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.InstaPay.entity.Registration;
import com.example.InstaPay.entity.Transaction;
import com.example.InstaPay.repository.InstaPayRepository;
import com.example.InstaPay.repository.TransactionRepository;

@Service
@Transactional
public class InstaPayServiceImpl implements InstaPayService {
	
	@Autowired
	private InstaPayRepository instapayRepository;
	
	@Autowired
	private TransactionRepository transactionRepository;

	@Override
	public String registerInInstaPay(long phoneNum,long accountNumber) {
		// TODO Auto-generated method stub
		Registration reg = new Registration();
		reg.setAccountNumber(accountNumber);
		reg.setPhoneNum(phoneNum);
		
		Optional<Registration> rr = instapayRepository.findByPhoneNum(phoneNum);
		if(rr.isPresent())
			return "Given Phone Number already Registered";
		else
		{
			instapayRepository.save(reg);
			return "Phone Number Successfully Registered";
		}
		
	}

	@Override
	public String findByPhoneNumber(long fromPhoneNum, long toPhoneNum, long amount) 
	{
		Optional<Registration> fromPh = instapayRepository.findByPhoneNum(fromPhoneNum);
		if(fromPh.isPresent())
		{
			Optional<Registration> toPh = instapayRepository.findByPhoneNum(toPhoneNum);
			if(toPh.isPresent())
			{
				return "accounts exists";
			}
			else
			{
				return "Source or Destination phone number haven't registered  to perform fund transfer ";
			}
			
		}
		else
		{
			return "Source or Destination phone number haven't registered to perform fund transfer ";
		}
		
	}

	@Override
	public void logTransactionDetails(long fromPhoneNum, long toPhoneNum, long amount) 
	{
		Transaction tx1 = new Transaction();
		tx1.setPhoneNumber(fromPhoneNum);
		tx1.setAmount(amount);
		tx1.setTransactionType("Debit");
		tx1.setDate(new Date().toString());
		
		Transaction tx2 = new Transaction();
		tx2.setPhoneNumber(toPhoneNum);
		tx2.setAmount(amount);
		tx2.setTransactionType("Credit");
		tx2.setDate(new Date().toString());
		
		transactionRepository.save(tx1);
		transactionRepository.save(tx2);
		
		
		
	}
	
	
	

}

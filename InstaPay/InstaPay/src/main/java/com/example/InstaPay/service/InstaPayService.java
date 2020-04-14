package com.example.InstaPay.service;

public interface InstaPayService {

	String registerInInstaPay(long phoneNum,long accountNumber);

	String findByPhoneNumber(long fromPhoneNum, long toPhoneNum, long amount);

	void logTransactionDetails(long fromPhoneNum, long toPhoneNum, long amount);
	
	

}

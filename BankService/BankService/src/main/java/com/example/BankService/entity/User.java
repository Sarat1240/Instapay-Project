package com.example.BankService.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
public class User implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5126549699402341494L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	private String location;
	private int age;
	private String gender;
	private String pan;
	private long phoneNum;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	@JoinColumn(name = "account_fk")
	private Account acc = new Account();

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the pan
	 */
	public String getPan() {
		return pan;
	}

	/**
	 * @param pan the pan to set
	 */
	public void setPan(String pan) {
		this.pan = pan;
	}

	/**
	 * @return the acc
	 */
	public Account getAcc() {
		return acc;
	}

	/**
	 * @param acc the acc to set
	 */
	public void setAcc(Account acc) {
		this.acc = acc;
	}

	/**
	 * @return the phoneNum
	 */
	public long getPhoneNum() {
		return phoneNum;
	}

	/**
	 * @param phoneNum the phoneNum to set
	 */
	public void setPhoneNum(long phoneNum) {
		this.phoneNum = phoneNum;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", location=" + location + ", age=" + age + ", gender=" + gender
				+ ", pan=" + pan + ", phoneNum=" + phoneNum + ", acc=" + acc + "]";
	}

	
	
	
	
	
	
}

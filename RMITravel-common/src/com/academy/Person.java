package com.academy;

import java.io.Serializable;

/**
 * @author phamt
 *
 */
public class Person implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private String name;
	private String passport;
	private String cmnd;
	private String address;
	private String phone;
	private String email;
	private String tour;
	private String hotel;
	private String service;
	private String numberroom;
	private String verhicle;
	private String numberchair;
	private String status;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
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
	 * @return the passport
	 */
	public String getPassport() {
		return passport;
	}

	/**
	 * @param passport the passport to set
	 */
	public void setPassport(String passport) {
		this.passport = passport;
	}

	/**
	 * @return the cmnd
	 */
	public String getCmnd() {
		return cmnd;
	}

	/**
	 * @param cmnd the cmnd to set
	 */
	public void setCmnd(String cmnd) {
		this.cmnd = cmnd;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the tour
	 */
	public String getTour() {
		return tour;
	}

	/**
	 * @param tour the tour to set
	 */
	public void setTour(String tour) {
		this.tour = tour;
	}

	/**
	 * @return the hotel
	 */
	public String getHotel() {
		return hotel;
	}

	/**
	 * @param hotel the hotel to set
	 */
	public void setHotel(String hotel) {
		this.hotel = hotel;
	}

	/**
	 * @return the service
	 */
	public String getService() {
		return service;
	}

	/**
	 * @param service the service to set
	 */
	public void setService(String service) {
		this.service = service;
	}

	/**
	 * @return the numberroom
	 */
	public String getNumberroom() {
		return numberroom;
	}

	/**
	 * @param numberroom the numberroom to set
	 */
	public void setNumberroom(String numberroom) {
		this.numberroom = numberroom;
	}

	/**
	 * @return the verhicle
	 */
	public String getVerhicle() {
		return verhicle;
	}

	/**
	 * @param verhicle the verhicle to set
	 */
	public void setVerhicle(String verhicle) {
		this.verhicle = verhicle;
	}

	/**
	 * @return the numberchair
	 */
	public String getNumberchair() {
		return numberchair;
	}

	/**
	 * @param numberchair the numberchair to set
	 */
	public void setNumberchair(String numberchair) {
		this.numberchair = numberchair;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set	
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	public Person() {
		super();
	}

	public Person(String name, String passport, String cmnd, String address, String phone, String email, String tour,
			String hotel, String service, String numberroom, String verhicle, String numberchair, String status) {
		super();
		this.name = name;
		this.passport = passport;
		this.cmnd = cmnd;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.tour = tour;
		this.hotel = hotel;
		this.service = service;
		this.numberroom = numberroom;
		this.verhicle = verhicle;
		this.numberchair = numberchair;
		this.status = status;
	}

	public Person(int id, String name, String passport, String cmnd, String address, String phone, String email,
			String tour, String hotel, String service, String numberroom, String verhicle, String numberchair,
			String status) {
		super();
		this.id = id;
		this.name = name;
		this.passport = passport;
		this.cmnd = cmnd;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.tour = tour;
		this.hotel = hotel;
		this.service = service;
		this.numberroom = numberroom;
		this.verhicle = verhicle;
		this.numberchair = numberchair;
		this.status = status;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", passport=" + passport + ", cmnd=" + cmnd + ", address="
				+ address + ", phone=" + phone + ", email=" + email + ", tour=" + tour + ", hotel=" + hotel
				+ ", service=" + service + ", numberroom=" + numberroom + ", verhicle=" + verhicle + ", numberchair="
				+ numberchair + ", status=" + status + "]";
	}
	
	

}

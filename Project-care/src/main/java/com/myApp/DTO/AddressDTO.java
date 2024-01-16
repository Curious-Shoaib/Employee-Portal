package com.myApp.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class AddressDTO {

	Integer houseNo;
	String sector;
	@NotBlank(message="{CITY_BLANK}")
	String city;
	@Pattern(regexp="\\d{6}",message="{INVALID_ZIP}")
	String zipCode;
	@NotBlank(message="{STATE_BLANK}")
	String state;
	@NotBlank(message="{CNT_BLANK}")
	String country;
	public Integer getHouseNo() {
		return houseNo;
	}
	public void setHouseNo(Integer houseNo) {
		this.houseNo = houseNo;
	}
	public String getSector() {
		return sector;
	}
	public void setSector(String sector) {
		this.sector = sector;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}

}

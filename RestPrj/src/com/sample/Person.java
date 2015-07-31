package com.sample;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "name", "place", "dob" })
public class Person {

	@JsonProperty("name")
	private String name;
	@JsonProperty("place")
	private String place;
	@JsonProperty("dob")
	private String dob;

	
	@JsonProperty("name")
	public String getName() {
		return name;
	}
	
	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}
	
	@JsonProperty("place")
	public String getPlace() {
		return place;
	}

	@JsonProperty("place")
	public void setPlace(String place) {
		this.place = place;
	}

	@JsonProperty("dob")
	public String getDob() {
		return dob;
	}

	@JsonProperty("dob")
	public void setDob(String dob) {
		this.dob = dob;
	}

	

}

package com.learning.springbootmongodb.model;

public class Address {

	
		String adrsLine;
		String city;
		String state;
		Integer zipCode;
		
		public Address() {
		
		}
		
		public Address(String adrsLine, String city, String state, Integer zipCode) {
			this.adrsLine = adrsLine;
			this.city = city;
			this.state = state;
			this.zipCode = zipCode;
		}


		public String getAdrsLine() {
			return adrsLine;
		}

		public void setAdrsLine(String adrsLine) {
			this.adrsLine = adrsLine;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		public Integer getZipCode() {
			return zipCode;
		}

		public void setZipCode(Integer zipCode) {
			this.zipCode = zipCode;
		}

		@Override
		public String toString() {
			return "Address [adrsLine=" + adrsLine + ", city=" + city + ", state=" + state + ", zipCode=" + zipCode
					+ "]";
		}
		
		
}

package io.alberttoshiro.model;

public class City {

	private String name;
	private double latitude;
	private double longitude;
	private String countryCode;
	private String admin1;
	
	public City() {
		super();
	}

	public City(String name, double latitude, double longitude, String countryCode, String admin1) {
		super();
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
		this.countryCode = countryCode;
		this.admin1 = admin1;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getAdmin1() {
		return admin1;
	}

	public void setAdmin1(String admin1) {
		this.admin1 = admin1;
	}
	
	
	
	
}

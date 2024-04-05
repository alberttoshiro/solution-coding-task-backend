package io.alberttoshiro.model;

public class Suggestion {

	private String name;
	private double latitude;
	private double longitude;
	private double score;
	
	public Suggestion() {
		super();
	}

	public Suggestion(String name, double latitude, double longitude, double score) {
		super();
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
		this.score = score;
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

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}
	
	
	
}

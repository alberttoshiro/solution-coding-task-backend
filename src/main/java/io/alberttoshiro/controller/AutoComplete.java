package io.alberttoshiro.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.alberttoshiro.data.CitiesDataService;
import io.alberttoshiro.model.City;
import io.alberttoshiro.model.Suggestion;
import io.alberttoshiro.model.Suggestions;

@RestController
public class AutoComplete {

	@Autowired
	private CitiesDataService citiesDataService;
	
	//to hit the endpoint use http://localhost:8081
	//example http://localhost:8081/suggestions?q=Londo&latitude=43.70011&longitude=-79.4163
	@RequestMapping("/suggestions")
	public Suggestions autoCompleteSuggestions(@RequestParam(value = "q", required = true) String name, @RequestParam(value = "latitude") Optional<Double> latitude, @RequestParam(value = "longitude") Optional<Double> longitude) {
		List<Suggestion> suggestions = new ArrayList<Suggestion>();
		for (City city : citiesDataService.getCities()) {
			if(city.getName().length() < name.length()) continue;
			if(city.getName().substring(0, name.length()).equalsIgnoreCase(name)) {
				suggestions.add(new Suggestion(city.getName() + ", " + city.getAdmin1() + ", " + city.getCountryCode(), city.getLatitude(), city.getLongitude(), computeScore(city, name, latitude, longitude)));
			}
		}
		// Sort the result based on score descending
		Collections.sort(suggestions, new Comparator<Suggestion>() {

			@Override
			public int compare(Suggestion o1, Suggestion o2) {
				if(o1.getScore() > o2.getScore()) return -1;
				if(o1.getScore() == o2.getScore()) return 0;
				return 1;
			}
			
		});
		// only select top 10 city with highest score
		if(suggestions.size() <= 10) {
			return new Suggestions(suggestions);
		} else {
			List<Suggestion> suggestions10 = new ArrayList<Suggestion>();
			for(int i = 0 ; i < 10 ; i++) {
				suggestions10.add(suggestions.get(i));
			}
			return new Suggestions(suggestions10);
		}
	}
	
	public double computeScore(City city, String searchName, Optional<Double> latitude, Optional<Double> longitude) {
		double searchNameLength = searchName.length();
		double cityNameLength = city.getName().length();
		double cityScore = searchNameLength / cityNameLength;
		double totalScore = cityScore;
		double divideScore = 1.0;
		if(latitude.isPresent()) {
			divideScore += 1.0;
			double latitudeDifference = city.getLatitude() - latitude.get();
			if(latitudeDifference < 0.0) latitudeDifference *= -1.0;
			double latitudeScore = 1.0 - latitudeDifference / 180;
			// divide by 180 because latitude range is from -90 to 90
			totalScore += latitudeScore;
		}
		if(longitude.isPresent()) {
			divideScore += 1.0;
			double longitudeDifference = city.getLongitude() - longitude.get();
			if(longitudeDifference < 0.0) longitudeDifference *= -1;
			double longitudeScore = 1.0 - longitudeDifference / 360;
			// divide by 360 because longitude range is from -180 to 180
			totalScore += longitudeScore;
		}
		return totalScore / divideScore;
	}
	
}

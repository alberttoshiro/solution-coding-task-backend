package io.alberttoshiro.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import io.alberttoshiro.model.City;
import jakarta.annotation.PostConstruct;

@Service
public class CitiesDataService {

	private List<City> cities;
	
	// load the cities data from the file
	@PostConstruct
	private void loadCitiesData() {
		String filePath = "data/cities_canada-usa.tsv";
		cities = new ArrayList<City>();
		try (BufferedReader TSVReader = new BufferedReader(new FileReader(filePath))){
			String line = null;
			boolean headerRead = false;
			while((line = TSVReader.readLine()) != null) {
				if(!headerRead) {
					headerRead = true;
					continue;
				}
				String[] cityData = line.split("\t");
				String name = cityData[1];
				double latitude = Double.parseDouble(cityData[4]);
				double longitude = Double.parseDouble(cityData[5]);
				String countryCode = cityData[8];
				String admin1 = cityData[10];
				City city = new City(name, latitude, longitude, countryCode, admin1);
				cities.add(city);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Failed reading file");
		}
	}

	public List<City> getCities() {
		return cities;
	}
	
}

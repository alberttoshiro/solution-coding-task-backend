package io.alberttoshiro.model;

import java.util.List;

public class Suggestions {

	List<Suggestion> suggestions;

	public Suggestions() {
		super();
	}

	public Suggestions(List<Suggestion> suggestions) {
		super();
		this.suggestions = suggestions;
	}

	public List<Suggestion> getSuggestions() {
		return suggestions;
	}

	public void setSuggestions(List<Suggestion> suggestions) {
		this.suggestions = suggestions;
	}
	
}

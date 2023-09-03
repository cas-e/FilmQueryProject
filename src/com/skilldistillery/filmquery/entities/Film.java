package com.skilldistillery.filmquery.entities;

import java.util.List;
import java.util.Objects;


public class Film {
	private int id;
	private String title;
	private String description;
	private String releaseYear;
	private int languageId;
	private int rentalDuration;
	private int rentalRate;
	private int length;
	private int replacementCost;
	
	private String rating;
	
	private String readableLanguage;
	
	private List<Actor> actors;
	
	
	public String getReadableLanguage() {
		return readableLanguage;
	}

	public void setReadableLanguage(String readableLanguage) {
		this.readableLanguage = readableLanguage;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getReleaseYear() {
		return releaseYear.split("-")[0];
	}

	public void setReleaseYear(String releaseYear) {
		this.releaseYear = releaseYear;
	}


	public int getLanguageId() {
		return languageId;
	}

	public void setLanguageId(int languageId) {
		this.languageId = languageId;
	}

	public int getRentalDuration() {
		return rentalDuration;
	}

	public void setRentalDuration(int rentalDuration) {
		this.rentalDuration = rentalDuration;
	}

	public int getRentalRate() {
		return rentalRate;
	}

	public void setRentalRate(int rentalRate) {
		this.rentalRate = rentalRate;
	}

	public int getReplacementCost() {
		return replacementCost;
	}

	public void setReplacementCost(int replacementCost) {
		this.replacementCost = replacementCost;
	}


	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}


	public List<Actor> getActors() {
		return actors;
	}

	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}

	@Override
	public int hashCode() {
		return Objects.hash(actors, description, id, languageId, length, releaseYear, rentalDuration, rentalRate,
				replacementCost, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film other = (Film) obj;
		return Objects.equals(actors, other.actors) && Objects.equals(description, other.description) && id == other.id
				&& languageId == other.languageId && length == other.length
				&& Objects.equals(releaseYear, other.releaseYear) && rentalDuration == other.rentalDuration
				&& rentalRate == other.rentalRate && replacementCost == other.replacementCost
				&& Objects.equals(title, other.title);
	}

	@Override 
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Title:       ").append(title).append("\n");
		sb.append("Year:        ").append(getReleaseYear()).append("\n");
		sb.append("Rating:      ").append(rating).append("\n");
		sb.append("Language:    ").append(readableLanguage).append("\n");
		sb.append("Description: ").append(description).append("\n");
		sb.append("Starring:    ");
		for (Actor actor : this.actors) {
			sb.append("* ").append(actor.getFirstName());
			sb.append(" ").append(actor.getLastName()).append(" ");
		}
		
		sb.append("\n");
		
		return sb.toString();
	}
}

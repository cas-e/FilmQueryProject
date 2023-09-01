package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {

	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Mountain";
	private static final String USERNAME = "student";
	private static final String PASSWORD = "student";
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		}
	}

	@Override
	public Film findFilmById(int filmId) throws SQLException {
		// this method should
		// return a Film object or null if the film id returns no data
		
		Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		
		String sql = "SELECT * FROM film WHERE id = ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, filmId);
		ResultSet filmResult = stmt.executeQuery();
		
		// we expect exactly one film per id...
		if (!filmResult.next()) {
			System.out.println("FAILED TO GET FILM BY ID");
			return null; // SIGNAL NO MATCH
		}
		
		Film film = new Film();
		film.setId(filmId);
		film.setTitle(filmResult.getString("title"));
		film.setDescription(filmResult.getString("description"));
		film.setReleaseYear(filmResult.getString("release_year"));
		film.setLanguageId(filmResult.getInt("language_id"));
		film.setRentalDuration(filmResult.getInt("rental_rate"));
		film.setRentalRate(filmResult.getInt("rental_rate"));
		film.setLength(filmResult.getInt("length"));
		film.setReplacementCost(filmResult.getInt("replacement_cost"));
		
		
		return film;
	}

	@Override

	public Actor findActorById(int actorId) throws SQLException {
		Actor actor = null;
		// open connection and close it for each methods
		Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		String sql = "SELECT * FROM actor WHERE id = ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.setInt(1, actorId);
		
		ResultSet actorResult = stmt.executeQuery();
		
		// iterate and display

		if (actorResult.next()) {
			actor = new Actor(); // Create the object
			// Here is our mapping of query columns to our object fields:
			
			// need to change to the string type column getters
			actor.setId(actorResult.getInt(1));
			actor.setFirstName(actorResult.getString(2));
			actor.setLastName(actorResult.getString(3));
		    actor.setFilms(findFilmsByActorId(actorId)); // An Actor has Films
		}
		// ...
		return actor;
	}

	public List<Film> findFilmsByActorId(int actorId) {
		  List<Film> films = new ArrayList<>();
		  try {
		    Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		    String sql = "SELECT id, title, description, release_year, language_id, rental_duration, ";
		                sql += " rental_rate, length, replacement_cost, rating, special_features "
		               +  " FROM film JOIN film_actor ON film.id = film_actor.film_id "
		               + " WHERE actor_id = ?";
		    PreparedStatement stmt = conn.prepareStatement(sql);
		    stmt.setInt(1, actorId);
		    ResultSet rs = stmt.executeQuery();
		    while (rs.next()) {
		      int filmId = rs.getInt(1);
		      String title = rs.getString(2);
		      String desc = rs.getString(3);
		      short releaseYear = rs.getShort(4);
		      int langId = rs.getInt(5);
		      int rentDur = rs.getInt(6);
		      double rate = rs.getDouble(7);
		      int length = rs.getInt(8);
		      double repCost = rs.getDouble(9);
		      String rating = rs.getString(10);
		      String features = rs.getString(11);
//		      Film film = new Film(filmId, title, desc, releaseYear, langId,
//		                           rentDur, rate, length, repCost, rating, features);
//		      films.add(film);
		    }
		    rs.close();
		    stmt.close();
		    conn.close();
		  } catch (SQLException e) {
		    e.printStackTrace();
		  }
		  return films;
		}
}

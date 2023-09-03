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

	// return null when results were empty
	@Override
	public Film findFilmById(int filmId)  {
		Film film = new Film();
		try {

			Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			
			String sql = "SELECT * FROM film JOIN language ON film.language_id = language.id "
					   + "WHERE film.id = ?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, filmId);
			ResultSet filmResult = stmt.executeQuery();
			
			if (!filmResult.next()) {
				return null; // SIGNAL NO MATCH
			}
			
			film = resultToFilm(filmResult, conn);
			
		    filmResult.close();
		    stmt.close();
		    conn.close();
		} catch(SQLException e) {
			System.out.println(e); // for debug
		}
		
		
		return film;

	}
	

	// the empty list signals no results match
	public List<Film> findFilmBySearchTerm(String searchTerm) {

		
		List<Film> films = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		
			String sql = "SELECT * FROM film JOIN language ON film.language_id = language.id "
			           + "WHERE film.title LIKE ? OR film.description LIKE ?";		
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%" + searchTerm + "%");
			stmt.setString(2, "%" + searchTerm + "%");
			
			ResultSet filmsResult = stmt.executeQuery();
			
			while (filmsResult.next()) {
				films.add(resultToFilm(filmsResult, conn));
			}
			
		    filmsResult.close();
		    stmt.close();
		    conn.close();
			
		} catch(SQLException e) {
			System.out.println(e);  // for debug
		}
		
		return films;
	}

	private Film resultToFilm(ResultSet filmResult, Connection conn) {
		Film film = new Film();
		try {
			film.setId(filmResult.getInt("id"));
			film.setTitle(filmResult.getString("title"));
			film.setDescription(filmResult.getString("description"));
			film.setReleaseYear(filmResult.getString("release_year"));
			film.setLanguageId(filmResult.getInt("language_id"));
			film.setRentalDuration(filmResult.getInt("rental_rate"));
			film.setRentalRate(filmResult.getInt("rental_rate"));
			film.setLength(filmResult.getInt("length"));
			film.setReplacementCost(filmResult.getInt("replacement_cost"));
			film.setRating(filmResult.getString("rating"));
			
			// get language as string
			film.setReadableLanguage(filmResult.getString("name"));
			

			// populate a list of actors within the film object based on the following query:
			String sql = "SELECT actor.first_name, actor.last_name "
			           + "FROM film_actor JOIN actor ON actor.id = film_actor.actor_id "
					   + "JOIN film ON film.id = film_actor.film_id WHERE film.id = ? ";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, filmResult.getString("id"));
			
			ResultSet actorsResult = stmt.executeQuery();
			
			List<Actor> actors = new ArrayList<>();
			
			while (actorsResult.next()) {
				Actor actor = new Actor();
				actor.setFirstName(actorsResult.getString("first_name"));
				actor.setLastName(actorsResult.getString("last_name"));
				
				actors.add(actor);
			}
			
			actorsResult.close();
			stmt.close();
			
			film.setActors(actors);

		} catch (SQLException e) {
			System.out.println(e);
		}
		return film;
	}
}

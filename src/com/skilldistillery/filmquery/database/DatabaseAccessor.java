package com.skilldistillery.filmquery.database;


import java.util.List;

import com.skilldistillery.filmquery.entities.Film;

public interface DatabaseAccessor {
  public Film findFilmById(int filmId);
  public List<Film> findFilmBySearchTerm(String searchTerm);
}

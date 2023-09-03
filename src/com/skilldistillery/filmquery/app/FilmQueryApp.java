package com.skilldistillery.filmquery.app;

import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {
  
  DatabaseAccessor db = new DatabaseAccessorObject();
  Scanner sc = new Scanner(System.in);

  public static void main(String[] args) {
    new FilmQueryApp().launch();
  }

  private void launch() {
	  System.out.println("~~ Welcome to Film Query App! ~~");
	
	  boolean userContinues = true;
	  while (userContinues) {
		  userContinues = dispatchOnChoice();
	  }
	  
	  System.out.println("Thank you for using Film Query App, goodbye!");
	  sc.close();
  }

  private boolean dispatchOnChoice() {
	  for (;;) {
		  System.out.println("Please select an option by hitting 1, 2, or 3. Then hit the enter key.");
		  System.out.println("1) Lookup film by ID number");
		  System.out.println("2) Lookup film by search keyword");
		  System.out.println("3) Exit application");
		  
		  switch (getUserChoice()) {
		  
		  case 1: doLookupId();      return true;
		  case 2: doLookupKeyword(); return true;
		  
		  case 3: return false; // exit caller's loop
		  
		  default: System.out.println("Oops! Couldn't parse that...");
		  }
	  }
  }
  
  // returns -1 whenever the input does not conform to expectations
  private int getUserChoice() {
	  String got = sc.nextLine();
	  try {
		  return Integer.parseInt(got); 
	  } catch (Exception e) {
		  return -1;
	  } 
  }
  
  // considers the empty string, full whitespace, and %'s to be invalid
  // loops until a valid string is obtained
  private String getUserSearchTerm() {
	  String get;	  
	  for (;;) {
		  get = sc.nextLine().trim().replace("%", "");
		  if (!get.equals("")) {
			  break;
		  }
		  System.out.println("Oops! That's not a valid search term... please enter a search term:");
	  }
	  return get;
  }
  
  private void doLookupId() {
	   System.out.println("Okay. Enter an id please: ");
	   
	   	int id = getUserChoice();
	   	Film film = db.findFilmById(id);
	   	
	   	if (film == null) {
	   		System.out.println("Looks like there's no film matching that id...\n");
	   	} else {
	   		System.out.println(film + "\n");
	   	}
  }
  
  private void doLookupKeyword() {
	  System.out.println("Okay, please enter a search term: ");
	  
	  String term = getUserSearchTerm();
	  
	  List<Film> films = db.findFilmBySearchTerm(term);
	  
	  if (films.size() == 0) {
		  System.out.println("We have no films matching " + term + " Sorry!\n");
	  } else {
		  for (Film film : films) {
			  System.out.println(film);  
		  }
		  System.out.println("Displaying all " + films.size() + " results matching " + term + "\n");
	  }
  }
}

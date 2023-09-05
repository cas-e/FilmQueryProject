# FilmQueryProject

## Description

A terminal based program that allows a user to make queries to a database. 

Example interaction:

~~~
~~ Welcome to Film Query App! ~~
Please select an option by hitting 1, 2, or 3. Then hit the enter key.
1) Lookup film by ID number
2) Lookup film by search keyword
3) Exit application
2                                 // user choice
Okay, please enter a search term: 
vampire                           // user choice
Title:       VAMPIRE WHALE
Year:        2005
Rating:      NC17
Language:    English
Description: A Epic Story of a Lumberjack And a Monkey who must Confront a Pioneer in A MySQL Convention
Starring:    * Johnny Cage * Meg Hawke * Richard Penn * Ed Mansfield * Alan Dreyfuss 

Displaying all 1 result(s) matching vampire


~~~

## Technologies Used

* Java
* Eclipse
* MySQL
* MAMP
* Maven
* XML

## Lessons Learned

There were two main lessons here for me. 

The first was getting a facility with using the declarative relational model that MySQL presents. In other words, to practice using queries to get at specific information. Making these queries boils down to making statements in a relational algebra, as was first described by Codd in [A Relational Model of Data for Large Shared Data Banks](https://dl.acm.org/doi/pdf/10.1145/362384.362685). Queries are declarative rather than operational, and I did find their use fairly intuitive so far. 

The second lesson was to gain experience navigating the intertwined technology stack that allows the use of of a database within programs. We brought in more outside technology in this project than any other before, which afforded a lot of power. The cost of that power is that it will some time to understand all the different tools at use here. There will be still many more tools to bring in as we go along learning more of the stack. I am still trying to find good strategies to learn all of these new things. 



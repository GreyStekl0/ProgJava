package oop.films;

import java.util.ArrayList;
import java.util.List;

public class FilmCatalogue {
  private final List<Movie> movies;

  public FilmCatalogue() {
    movies = new ArrayList<>();
  }

  public void addMovie(Movie movie) {
    movies.add(movie);
  }

  private Movie searchMovieByTitle(String title) {
    for (Movie movie : movies) {
      if (movie.getTitle().equalsIgnoreCase(title)) {
        return movie;
      }
    }
    return null;
  }

  public void playMovieByTitle(String title) {
    Movie movie = searchMovieByTitle(title);
    if (movie != null) {
      movie.play();
    } else {
      System.out.println("Фильм с названием '" + title + "' не найден.");
    }
  }
}

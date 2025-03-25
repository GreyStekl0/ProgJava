package oop.films;

public abstract class Movie {
  private final String title;
  private final String director;

  public Movie(String title, String director) {
    this.title = title;
    this.director = director;
  }

  public String getTitle() {
    return title;
  }

  public String getDirector() {
    return director;
  }

  public abstract void play();
}

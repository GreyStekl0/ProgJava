package oop.films;

public class Comedy extends Movie {
  public Comedy(String title, String director) {
    super(title, director);
  }

  @Override
  public void play() {
    System.out.println("Смотрим комедию '" + getTitle() + "' реж. '" + getDirector() + "'");
  }
}

package oop.films;

public class Action extends Movie {
  public Action(String title, String director) {
    super(title, director);
  }

  @Override
  public void play() {
    System.out.println("Смотрим боевик '" + getTitle() + "' реж. '" + getDirector() + "'");
  }
}

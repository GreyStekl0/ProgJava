package oop.films;

public class Horror extends Movie {
  public Horror(String title, String director) {
    super(title, director);
  }

  @Override
  public void play() {
    System.out.println("Смотрим ужастик '" + getTitle() + "' реж. '" + getDirector() + "'");
  }
}

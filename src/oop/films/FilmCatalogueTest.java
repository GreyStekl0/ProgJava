package oop.films;

public class FilmCatalogueTest {
  public static void main(String[] args) {
    FilmCatalogue catalogue = new FilmCatalogue();

    catalogue.addMovie(new Action("Крепкий орешек", "Джон Мактирнан"));
    catalogue.addMovie(new Comedy("Один дома", "Крис Коламбус"));
    catalogue.addMovie(new Horror("Сияние", "Стэнли Кубрик"));

    catalogue.playMovieByTitle("Крепкий орешек");
    catalogue.playMovieByTitle("Один дома");
    catalogue.playMovieByTitle("Сияние");

    catalogue.playMovieByTitle("Титаник 2");
  }
}

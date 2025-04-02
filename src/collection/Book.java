package collection;

import java.util.Objects;

/**
 * Класс, представляющий книгу в библиотеке.
 * Реализует Comparable для сортировки по году издания.
 */
public class Book implements Comparable<Book> {
  private final String title;
  private final String author;
  private final int year;
  private boolean available;
  private final String genre;
  private final String isbn; // International Standard Book Number

  /**
   * Конструктор книги.
   *
   * @param title  Название книги.
   * @param author Автор книги.
   * @param year   Год издания.
   * @param isbn   ISBN книги.
   * @param genre  Жанр книги (может быть null).
   */
  public Book(String title, String author, int year, String isbn, String genre) {
    // Простая валидация
    if (title == null || title.trim().isEmpty()
        || author == null || author.trim().isEmpty()
        || isbn == null || isbn.trim().isEmpty()
        || year <= 0) {
      throw new IllegalArgumentException("Некорректные данные для создания книги.");
    }
    this.title = title;
    this.author = author;
    this.year = year;
    this.isbn = isbn;
    this.genre = genre;
    this.available = true; // По умолчанию книга доступна при создании
  }

  // Геттеры
  public String getTitle() {
    return title;
  }

  public String getAuthor() {
    return author;
  }

  public int getYear() {
    return year;
  }

  public String getIsbn() {
    return isbn;
  }

  public String getGenre() {
    return genre;
  }

  public boolean isAvailable() {
    return available;
  }

  // Сеттер для доступности
  public void setAvailable(boolean available) {
    this.available = available;
  }

  /**
   * Сравнение книг в первую очередь по году издания (для TreeSet).
   * Если годы совпадают, сравниваем по названию для консистентности.
   */
  @Override
  public int compareTo(Book other) {
    if (this.year != other.year) {
      return Integer.compare(this.year, other.year);
    }
    // Если годы равны, сортируем по названию для уникальности в TreeSet
    return this.title.compareTo(other.title);
  }

  /**
   * Сравнение на равенство. Книги считаются равными, если у них совпадает ISBN.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Book book = (Book) o;
    return Objects.equals(isbn, book.isbn);
  }

  /**
   * Хэш-код на основе ISBN.
   */
  @Override
  public int hashCode() {
    return Objects.hash(isbn);
  }

  @Override
  public String toString() {
    return "Книга {"
        + "Название='" + title + '\''
        + ", Автор='" + author + '\''
        + ", Год=" + year
        + ", ISBN='" + isbn + '\''
        + ", Жанр='" + genre + '\''
        + ", Доступна=" + available
        + '}';
  }
}

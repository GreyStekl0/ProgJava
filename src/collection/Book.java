package collection;

/**
 * Record Book представляет книгу с названием, автором, годом издания и ISBN.
 * Реализует интерфейс Comparable для сортировки книг по автору, году издания, и названию.
 */
public record Book(
    String title,
    String author,
    int year,
    String isbn
) implements Comparable<Book> {

  /**
   * Сравнивает книги по авторам.
   * Если авторы одинаковые, сравнивает по году издания, затем по названию.
   */
  @Override
  public int compareTo(Book other) {
    int authorComparison = this.author.compareTo(other.author);
    if (authorComparison != 0) {
      return authorComparison;
    }
    int yearComparison = Integer.compare(this.year, other.year);
    if (yearComparison != 0) {
      return yearComparison;
    }
    return this.title.compareTo(other.title);
  }
}
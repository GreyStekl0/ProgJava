package collection;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Класс, представляющий читателя библиотеки.
 */
public class Reader {
  private final String name;
  private final String readerId; // Уникальный идентификатор читателя
  private final List<Book> borrowedBooks;
  private final int maxBooks; // Максимальное количество книг, которое читатель может взять

  // Статический счетчик для генерации ID
  private static int idCounter = 0;

  /**
   * Конструктор читателя.
   *
   * @param name Имя читателя.
   * @param maxBooks Максимальное количество книг, которое читатель может взять.
   */
  public Reader(String name, int maxBooks) {
    if (name == null || name.trim().isEmpty()) {
      throw new IllegalArgumentException("Имя читателя не может быть пустым.");
    }
    if (maxBooks <= 0) {
      throw new IllegalArgumentException("Максимальное количество книг должно быть положительным.");
    }
    this.name = name;
    this.maxBooks = maxBooks;
    this.borrowedBooks = new ArrayList<>(); // Инициализируем список
    this.readerId = "RDR-" + (++idCounter); // Генерируем ID
  }

  // Геттеры
  public String getName() {
    return name;
  }

  public String getReaderId() {
    return readerId;
  }

  public int getMaxBooks() {
    return maxBooks;
  }

  /**
   * Получает копию список взятых книг.
   *
   * @return Копия список книг.
   */
  public List<Book> getBorrowedBooks() {
    return new ArrayList<>(borrowedBooks);
  }

  /**
   * Проверяет, может ли читатель взять еще одну книгу.
   *
   * @return true, если количество взятых книг меньше максимального лимита.
   */
  public boolean canBorrowMore() {
    return borrowedBooks.size() < maxBooks;
  }

  /**
   * Добавляет книгу в список взятых читателем.
   * Внутренний метод, вызывается из Library.issueBook.
   *
   * @param book Книга для добавления.
   * @return true, если книга успешно добавлена, false в противном случае (например, если лимит достигнут).
   */
  boolean borrowBookInternal(Book book) {
    if (canBorrowMore()) {
      return borrowedBooks.add(book);
    }
    return false;
  }

  /**
   * Удаляет книгу из списка взятых читателем.
   * Внутренний метод, вызывается из Library.returnBook.
   *
   * @param book Книга для удаления.
   * @return true, если книга успешно удалена, false в противном случае.
   */
  boolean returnBookInternal(Book book) {
    return borrowedBooks.remove(book);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Reader reader = (Reader) o;
    return Objects.equals(readerId, reader.readerId); // Сравнение по уникальному ID
  }

  @Override
  public int hashCode() {
    return Objects.hash(readerId);
  }

  @Override
  public String toString() {
    return "Читатель {"
        + "ID='" + readerId + '\''
        + ", Имя='" + name + '\''
        + ", Лимит книг=" + maxBooks
        + ", Взято книг=" + borrowedBooks.size()
        + '}';
  }
}

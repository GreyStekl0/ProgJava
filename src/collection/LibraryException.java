package collection;

/**
 * Исключение для ошибок, связанных с операциями в библиотеке.
 */
public class LibraryException extends Exception {

  /**
   * Конструктор исключения.
   *
   * @param message Сообщение об ошибке.
   */
  public LibraryException(String message) {
    super(message);
  }
}

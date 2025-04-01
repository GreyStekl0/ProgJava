package collection;

/**
 * Record Reader представляет читателя с именем и уникальным идентификатором.
 * Уникальный идентификатор генерируется автоматически при создании нового читателя.
 */
public record Reader(String name, int readerId) {
  private static int idCounter = 0;

  /**
   * Компактный конструктор с генерацией ID.
   *
   * @param name Имя читателя
   */
  public Reader(String name) {
    this(name, ++idCounter);
  }
}
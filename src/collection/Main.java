package collection;

import java.util.List;

/**
 * Главный класс для демонстрации работы библиотеки.
 */
public class Main {

  /**
   * Главный метод для запуска программы.
   *
   * @throws LibraryException Исключение, связанное с библиотекой.
   */
  public static void main() throws LibraryException {
    Library library = new Library();

    // --- 1. Добавление книг (не менее 10) ---
    System.out.println("--- Добавление книг ---");
    try {
      library.addBook(new Book("Война и мир", "Лев Толстой", 1869, "978-5-699-49728-5", "Роман"));
      library.addBook(new Book("Преступление и наказание", "Федор Достоевский", 1866, "978-5-389-04907-8", "Роман"));
      library.addBook(new Book("Мастер и Маргарита", "Михаил Булгаков", 1967, "978-5-17-087888-5", "Роман"));
      library.addBook(new Book("1984", "Джордж Оруэлл", 1949, "978-0-452-28423-4", "Антиутопия"));
      library.addBook(new Book("Гордость и предубеждение", "Джейн Остин", 1813, "978-0-14-143951-8", "Роман"));
      library.addBook(new Book("Гарри Поттер и философский камень", "Джоан Роулинг", 1997, "978-0-7475-3269-9", "Фэнтези"));
      library.addBook(new Book("Властелин колец: Братство Кольца", "Дж. Р. Р. Толкин", 1954, "978-0-618-26027-9", "Фэнтези"));
      library.addBook(new Book("Маленький принц", "Антуан де Сент-Экзюпери", 1943, "978-0-15-601219-5", "Притча"));
      library.addBook(new Book("Алхимик", "Пауло Коэльо", 1988, "978-0-06-112241-5", "Притча"));
      library.addBook(new Book("Убить пересмешника", "Харпер Ли", 1960, "978-0-06-112008-4", "Роман"));
      library.addBook(new Book("Анна Каренина", "Лев Толстой", 1877, "978-5-04-108781-7", "Роман"));

      System.out.println("\nПопытка добавить дубликат по ISBN:");
      library.addBook(new Book("Еще одна 1984", "Джордж Оруэлл", 1949, "978-0-452-28423-4", "Антиутопия"));

    } catch (LibraryException | IllegalArgumentException e) {
      System.err.println("Ошибка при добавлении книги: " + e.getMessage());
    }

    // --- 2. Вывод каталога ---
    library.printCatalog();

    // --- 3. Добавление читателей ---
    System.out.println("\n--- Добавление читателей ---");
    Reader reader1 = null;
    Reader reader2 = null;
    try {
      reader1 = new Reader("Иван Петров", 5);
      reader2 = new Reader("Анна Сидорова", 3);
      library.addReader(reader1);
      library.addReader(reader2);
      System.out.println("Добавлены читатели:");
      System.out.println(library.getReaderById(reader1.getReaderId()));
      System.out.println(library.getReader(reader2.getName()));

    } catch (LibraryException | IllegalArgumentException e) {
      System.err.println("Ошибка при добавлении читателя: " + e.getMessage());
    }

    // --- 4. Поиск книг ---
    System.out.println("\n--- Поиск книг ---");
    System.out.println("Книги Льва Толстого:");
    List<Book> tolstoyBooks = library.findBooksByAuthor("Лев Толстой");
    for (Book book : tolstoyBooks) {
      System.out.println(book);
    }

    System.out.println("\nКниги 1949 года:");
    List<Book> books1949 = library.findBooksByYear(1949);
    for (Book book : books1949) {
      System.out.println(book);
    }

    System.out.println("\nПоиск несуществующего автора:");
    List<Book> nonExistentAuthorBooks = library.findBooksByAuthor("Неизвестный Автор");
    System.out.println("Найдено книг: " + nonExistentAuthorBooks.size());

    // --- 5. Выдача книг ---
    System.out.println("\n--- Выдача книг ---");
    try {
      Book book1984 = library.findBooksByYear(1949).getFirst();
      Book masterMargarita = library.findBooksByAuthor("Михаил Булгаков").getFirst();
      Book annaKarenina = library.findBooksByAuthor("Лев Толстой").get(1);

      library.issueBook(reader1, book1984);
      library.issueBook(reader1, masterMargarita);
      library.issueBook(reader2, annaKarenina);

      System.out.println("\nПопытка выдать уже выданную книгу:");
      library.issueBook(reader2, book1984);

    } catch (LibraryException | IndexOutOfBoundsException e) {
      System.err.println("Ошибка при выдаче книги: " + e.getMessage());
    }

    // --- 6. Статистика и доступные книги ---
    library.printIssuedBooksStats();
    library.printAvailableBooks();

    // --- 7. Возврат книги ---
    System.out.println("\n--- Возврат книги ---");
    try {
      Book book1984 = library.findBooksByYear(1949).getFirst();
      library.returnBook(book1984);

      System.out.println("\nПопытка вернуть невыданную книгу:");
      Book warAndPeace = library.findBooksByAuthor("Лев Толстой").getFirst();
      library.returnBook(warAndPeace);

    } catch (LibraryException | IndexOutOfBoundsException e) {
      System.err.println("Ошибка при возврате книги: " + e.getMessage());
    }

    library.printIssuedBooksStats();
    library.printAvailableBooks();

    // --- 8. Работа с очередью ---
    System.out.println("\n--- Работа с очередью ---");
    try {
      Book masterMargarita = library.findBooksByAuthor("Михаил Булгаков").getFirst();
      library.addToWaitingList(masterMargarita);
      library.addToPriorityWaitingList(masterMargarita);

      System.out.println("Книга 'Мастер и Маргарита' в обычной очереди? " + library.isBookInQueue(masterMargarita));
      System.out.println("Книга 'Мастер и Маргарита' в приоритетной очереди? " + library.isBookInPriorityQueue(masterMargarita));

      Book warAndPeace = library.findBooksByAuthor("Лев Толстой").getFirst();
      System.out.println("\nПопытка добавить доступную книгу в очередь:");
      library.addToWaitingList(warAndPeace);

      System.out.println("\nИзвлечение из приоритетной очереди: " + library.getNextBookFromPriorityQueue());
      System.out.println("Извлечение из обычной очереди: " + library.getNextBookFromQueue());
      library.getNextBookFromQueue();
      System.out.println("Извлечение из пустой обычной очереди: " + library.getNextBookFromQueue());

    } catch (LibraryException | IndexOutOfBoundsException e) {
      System.err.println("Ошибка при работе с очередью: " + e.getMessage());
    }


    // --- 9. Статистика по авторам ---
    library.printUniqueAuthors();

    // --- 10. Получение популярных книг ---
    System.out.println("\n--- Популярные книги (были в очередях) ---");
    List<Book> popular = library.getPopularBooks();
    if (popular.isEmpty()) {
      System.out.println("Нет книг в очередях ожидания.");
    } else {
      for (Book book : popular) {
        System.out.println(book);
      }
    }

    // --- 11. Сортировка ---
    System.out.println("\n--- Сортировка по автору ---");
    List<Book> sortedByAuthor = library.sortByAuthor();
    for (Book b : sortedByAuthor) {
      System.out.println(b.getAuthor() + " - " + b.getTitle());
    }

    // --- 12. Удаление книги ---
    System.out.println("\n--- Удаление книги ---");
    try {
      System.out.println("Попытка удалить выданную книгу:");
      Book masterMargarita = library.findBooksByAuthor("Михаил Булгаков").getFirst();
      library.removeBook(masterMargarita);
    } catch (LibraryException | IndexOutOfBoundsException e) {
      System.err.println("Ошибка при удалении: " + e.getMessage());
    }

    try {
      System.out.println("\nУдаление доступной книги:");
      Book warAndPeace = library.findBooksByAuthor("Лев Толстой").getFirst();
      library.removeBook(warAndPeace);
      library.printCatalog();

      System.out.println("\nПопытка удалить несуществующую книгу:");
      library.removeBook(new Book("Несуществующая книга", "Автор", 2000, "000-0", "Жанр"));

    } catch (LibraryException | IndexOutOfBoundsException e) {
      System.err.println("Ошибка при удалении: " + e.getMessage());
    }

    // --- 13. Обработка граничных случаев ---
    System.out.println("\n--- Обработка граничных случаев ---");
    System.out.println("Поиск книг по null автору:");
    library.findBooksByAuthor(null);
    try {
      System.out.println("Попытка выдать null книгу:");
      library.issueBook(reader1, null);
    } catch (LibraryException e) {
      System.err.println("Ошибка: " + e.getMessage());
    }
    Library emptyLibrary = new Library();
    System.out.println("\nРабота с пустой библиотекой:");
    emptyLibrary.printCatalog();
    System.out.println("Уникальные авторы в пустой библиотеке: " + emptyLibrary.getUniqueAuthors());
    System.out.println("Следующая книга из очереди в пустой библиотеке: " + emptyLibrary.getNextBookFromQueue());
    try {

      emptyLibrary.removeBook(new Book("Тест", "Тест", 2020, "111", "Тест"));
    } catch (LibraryException e) {
      System.err.println("Попытка удаления из пустой библиотеки: " + e.getMessage());
    }

    System.out.println("\n--- Демонстрация завершена ---");
  }
}

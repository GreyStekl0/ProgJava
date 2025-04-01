package collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Класс Library представляет библиотеку с каталогом книг, системой выдачи книг,
 * статистикой и очередями ожидания.
 *
 * <p>Реализует методы для работы с книгами, читателями и очередями.
 */
public class Library {

  // 1. Хранение каталога книг (отсортировано по автору по умолчанию)
  private final SortedSet<Book> catalog = new TreeSet<>();

  // 2. Система выдачи книг
  private final Map<Reader, List<Book>> issuedBooks = new HashMap<>();
  private final Queue<Book> waitingQueue = new LinkedList<>(); // Очередь на популярные книги (FIFO)
  private final PriorityQueue<Book> priorityWaitingQueue = new PriorityQueue<>();

  // 3. Статистика
  private final Set<String> uniqueAuthors = new HashSet<>();
  private final List<Book> newArrivals = new ArrayList<>(); // Временное хранение
  private final List<Book> issueHistory = new LinkedList<>(); // История всех выдач (FIFO)

  // Дополнительно: хранение читателей
  private final Set<Reader> readers = new HashSet<>();

  // --- 1. Базовые методы работы с книгами ---

  /**
   * Добавление книги в каталог.
   *
   * @param book Книга для добавления.
   * @throws NullPointerException     если book == null.
   * @throws IllegalArgumentException если книга с таким ISBN уже существует.
   */
  public void addBook(Book book) {
    Objects.requireNonNull(book, "Книга не может быть null");

    if (catalog.contains(book)) {
      // TreeSet сам не добавит дубликат (основываясь на compareTo/equals),
      // но для ясности можно добавить проверку или полагаться на возвращаемое значение add.
      System.out.println(
          "Предупреждение: Книга с ISBN "
              + book.isbn()
              + " уже существует в каталоге."
      );
      return; // Или выбросить исключение IllegalArgumentException
    }

    if (catalog.add(book)) { // Добавляем в каталог (TreeSet)
      uniqueAuthors.add(book.author()); // Добавляем автора в HashSet (дубликаты игнорируются)
      newArrivals.add(book); // Добавляем в список новых поступлений (ArrayList)
      System.out.println(
          "Книга добавлена: "
              + book.title()
      );
    }
  }

  /**
   * Удаление книги из каталога.
   *
   * @param book Книга для удаления.
   * @throws NullPointerException   если book == null.
   * @throws IllegalStateException  если книга выдана читателю.
   * @throws NoSuchElementException если книга не найдена в каталоге.
   */
  public void removeBook(Book book) {
    Objects.requireNonNull(book, "Книга не может быть null");

    if (!catalog.contains(book)) {
      throw new NoSuchElementException(
          "Книга "
              + book.title()
              + " не найдена в каталоге."
      );
    }

    if (!isBookAvailable(book)) {
      throw new IllegalStateException(
          "Книга "
              + book.title()
              + " выдана читателю и не может быть удалена."
      );
    }

    // Удаляем из каталога
    if (catalog.remove(book)) {
      System.out.println(
          "Книга удалена из каталога: "
              + book.title()
      );

      // Удаляем из новых поступлений, если она там была
      newArrivals.remove(book);

      // Удаляем из очередей ожидания
      waitingQueue.remove(book);
      priorityWaitingQueue.remove(book);

      // Обновляем список уникальных авторов (удаляем автора, если книг его больше нет)
      final String authorToRemove = book.author();
      boolean authorHasOtherBooks = false;
      for (Book value : catalog) {
        if (value.author().equals(authorToRemove)) {
          authorHasOtherBooks = true;
          break;
        }
      }

      if (!authorHasOtherBooks) {
        uniqueAuthors.remove(authorToRemove);
        System.out.println(
            "Автор "
                + authorToRemove
                + " удален из списка уникальных авторов."
        );
      }
    } else {
      // Эта ветка маловероятна из-за предварительной проверки contains, но для полноты
      throw new NoSuchElementException(
          "Не удалось удалить книгу "
              + book.title()
              + " (возможно, она была удалена параллельно)."
      );
    }
  }

  /**
   * Поиск книг по автору.
   *
   * @param author Имя автора.
   * @return Список книг данного автора.
   * @throws NullPointerException если author == null.
   */
  public List<Book> findBooksByAuthor(String author) {
    Objects.requireNonNull(author, "Имя автора не может быть null");
    List<Book> foundBooks = new ArrayList<>();
    for (Book currentBook : catalog) {
      if (currentBook.author().equalsIgnoreCase(author)) { // Поиск без учета регистра
        foundBooks.add(currentBook);
      }
    }
    return foundBooks;
  }

  /**
   * Поиск книг по году издания.
   *
   * @param year Год издания.
   * @return Список книг данного года.
   */
  public List<Book> findBooksByYear(int year) {
    if (year <= 0) {
      throw new IllegalArgumentException("Год должен быть положительным числом.");
    }
    List<Book> foundBooks = new ArrayList<>();
    for (Book book : catalog) {
      if (book.year() == year) {
        foundBooks.add(book);
      }
    }
    return foundBooks;
  }

  // --- 2. Методы работы с читателями ---

  /**
   * Добавление нового читателя.
   *
   * @param reader Читатель для добавления.
   * @return true, если читатель был добавлен, false, если он уже существует.
   * @throws NullPointerException если reader == null.
   */
  public boolean addReader(Reader reader) {
    Objects.requireNonNull(reader, "Читатель не может быть null");
    if (readers.add(reader)) {
      // Инициализируем список выданных книг для нового читателя
      issuedBooks.put(reader, new ArrayList<>());
      System.out.println("Читатель добавлен: " + reader.name());
      return true;
    } else {
      System.out.println(
          "Читатель "
              + reader.name()
              + " (ID: "
              + reader.readerId()
              + ") уже зарегистрирован."
      );
      return false;
    }
  }

  /**
   * Получение читателя по имени.
   *
   * @param name Имя читателя.
   * @return Объект Reader или null, если читатель не найден.
   * @throws NullPointerException если name == null.
   */
  public Reader getReader(String name) {
    Objects.requireNonNull(name, "Имя читателя не может быть null");
    for (Reader reader : readers) {
      if (reader.name().equalsIgnoreCase(name)) {
        return reader;
      }
    }
    return null; // Возвращаем null, если не найден
  }

  /**
   * Получение читателя по ID.
   *
   * @param readerId ID читателя.
   * @return Объект Reader или null, если читатель не найден.
   */
  public Reader getReaderById(int readerId) {
    if (readerId <= 0) {
      throw new IllegalArgumentException("ID читателя должен быть положительным.");
    }
    for (Reader reader : readers) {
      if (reader.readerId() == readerId) {
        return reader;
      }
    }
    return null; // Возвращаем null, если не найден
  }


  /**
   * Выдача книги читателю.
   *
   * @param reader Читатель.
   * @param book   Книга для выдачи.
   * @throws NullPointerException   если reader или book == null.
   * @throws NoSuchElementException если книга или читатель не зарегистрированы в системе.
   * @throws IllegalStateException  если книга уже выдана или недоступна.
   */
  public void issueBook(Reader reader, Book book) {
    Objects.requireNonNull(reader, "Читатель не может быть null");
    Objects.requireNonNull(book, "Книга не может быть null");

    if (!readers.contains(reader)) {
      throw new NoSuchElementException("Читатель " + reader.name() + " не зарегистрирован.");
    }
    if (!catalog.contains(book)) {
      throw new NoSuchElementException("Книга " + book.title() + " не найдена в каталоге.");
    }

    if (!isBookAvailable(book)) {
      throw new IllegalStateException(
          "Книга "
              + book.title()
              + " недоступна для выдачи (возможно, уже выдана)."
      );
    }

    // Выдаем книгу
    List<Book> readerBooks = issuedBooks.get(reader); // Получаем список книг читателя из HashMap
    if (readerBooks == null) {
      // Этого не должно произойти, если addReader работает правильно, но для надежности
      throw new IllegalStateException(
          "Внутренняя ошибка: отсутствует запись о книгах для читателя "
              + reader.name()
      );
    }
    readerBooks.add(book); // Добавляем книгу в список читателя (ArrayList)
    issueHistory.add(book); // Добавляем в общую историю выдач (LinkedList)

    // Удаляем из очередей, если книга была там
    waitingQueue.remove(book);
    priorityWaitingQueue.remove(book);

    System.out.println("Книга '" + book.title() + "' выдана читателю " + reader.name());
  }

  /**
   * Возврат книги читателем.
   *
   * @param book Книга для возврата.
   * @throws NullPointerException   если book == null.
   * @throws NoSuchElementException если книга не найдена в каталоге.
   * @throws IllegalStateException  если книга не числится как выданная.
   */
  public void returnBook(Book book) {
    Objects.requireNonNull(book, "Книга не может быть null");

    if (!catalog.contains(book)) {
      // Книга может быть удалена после выдачи? Спорный момент.
      // Если удаление возможно, то эта проверка не нужна.
      // Если удаление невозможно, пока книга выдана (как реализовано в removeBook), то эта проверка уместна.
      throw new NoSuchElementException("Книга " + book.title() + " не найдена в общем каталоге.");
    }

    boolean foundAndReturned = false;
    // Используем итератор для безопасного удаления во время итерации по записям Map
    for (Map.Entry<Reader, List<Book>> entry : issuedBooks.entrySet()) {
      List<Book> readerBooks = entry.getValue();

      // Используем итератор для списка книг читателя для безопасного удаления
      Iterator<Book> bookIterator = readerBooks.iterator();
      while (bookIterator.hasNext()) {
        Book issued = bookIterator.next();
        if (issued.equals(book)) { // Сравнение по equals (ISBN)
          bookIterator.remove(); // Удаляем книгу из списка читателя
          foundAndReturned = true;
          System.out.println(
              "Книга '"
                  + book.title()
                  + "' возвращена читателем "
                  + entry.getKey().name()
          );
          // Можно добавить логику уведомления следующего в очереди ожидания
          break; // Книга найдена и возвращена, выходим из внутреннего цикла
        }
      }
      if (foundAndReturned) {
        break; // Выходим из внешнего цикла
      }
    }

    if (!foundAndReturned) {
      throw new IllegalStateException("Книга '" + book.title() + "' не числится как выданная.");
    }
  }

  // --- 3. Методы работы с очередью ---

  /**
   * Добавление книги в обычную очередь ожидания.
   *
   * @param book Книга.
   * @throws NullPointerException   если book == null.
   * @throws NoSuchElementException если книги нет в каталоге.
   * @throws IllegalStateException  если книга доступна (нет смысла ставить в очередь).
   */
  public void addToWaitingList(Book book) {
    Objects.requireNonNull(book, "Книга не может быть null");
    if (!catalog.contains(book)) {
      throw new NoSuchElementException("Книга " + book.title() + " не найдена в каталоге.");
    }
    if (isBookAvailable(book)) {
      System.out.println(
          "Книга '"
              + book.title()
              + "' доступна и не требует добавления в очередь."
      );
      return; // Или выбросить IllegalStateException
    }
    if (waitingQueue.contains(book)) {
      System.out.println("Книга '" + book.title() + "' уже находится в очереди ожидания.");
      return;
    }

    waitingQueue.offer(book); // Добавляем в конец очереди (LinkedList как Queue)
    System.out.println("Книга '" + book.title() + "' добавлена в очередь ожидания.");
  }

  /**
   * Добавление книги в приоритетную очередь ожидания.
   *
   * @param book Книга.
   * @throws NullPointerException   если book == null.
   * @throws NoSuchElementException если книги нет в каталоге.
   */
  public void addToPriorityWaitingList(Book book) {
    Objects.requireNonNull(book, "Книга не может быть null");
    if (!catalog.contains(book)) {
      throw new NoSuchElementException("Книга " + book.title() + " не найдена в каталоге.");
    }
    // Логика проверки доступности и наличия в очереди аналогична обычной очереди
    // ...

    priorityWaitingQueue.offer(book); // Добавляем в PriorityQueue (сортировка по году)
    System.out.println("Книга '" + book.title() + "' добавлена в приоритетную очередь.");
  }

  /**
   * Получение следующей книги из обычной очереди ожидания (FIFO).
   * Книга удаляется из очереди.
   *
   * @return Следующая книга или null, если очередь пуста.
   */
  public Book getNextBookFromQueue() {
    Book nextBook = waitingQueue.poll(); // Извлекает и удаляет голову очереди
    if (nextBook != null) {
      System.out.println("Следующая книга из очереди: " + nextBook.title());
    } else {
      System.out.println("Обычная очередь ожидания пуста.");
    }
    return nextBook;
  }

  /**
   * Получение следующей книги из приоритетной очереди ожидания.
   * Книга удаляется из очереди.
   *
   * @return Следующая книга или null, если очередь пуста.
   */
  public Book getNextBookFromPriorityQueue() {
    // Извлекает и удаляет голову очереди (наименьший год)
    Book nextBook = priorityWaitingQueue.poll();
    if (nextBook != null) {
      System.out.println("Следующая книга из приоритетной очереди: " + nextBook.title());
    } else {
      System.out.println("Приоритетная очередь ожидания пуста.");
    }
    return nextBook;
  }


  /**
   * Проверка наличия книги в обычной очереди ожидания.
   *
   * @param book Книга для проверки.
   * @return true, если книга в очереди, иначе false.
   * @throws NullPointerException если book == null.
   */
  public boolean isBookInQueue(Book book) {
    Objects.requireNonNull(book, "Книга не может быть null");
    return waitingQueue.contains(book); // Проверка наличия в LinkedList (Queue)
  }

  /**
   * Проверка наличия книги в приоритетной очереди ожидания.
   *
   * @param book Книга для проверки.
   * @return true, если книга в очереди, иначе false.
   * @throws NullPointerException если book == null.
   */
  public boolean isBookInPriorityQueue(Book book) {
    Objects.requireNonNull(book, "Книга не может быть null");
    return priorityWaitingQueue.contains(book); // Проверка наличия в PriorityQueue
  }

  // --- 4. Методы статистики ---

  /**
   * Получение множества уникальных авторов в библиотеке.
   *
   * @return Неизменяемое множество имен авторов.
   */
  public Set<String> getUniqueAuthors() {
    // Возвращаем копию или неизменяемое представление для защиты внутреннего состояния
    return Collections.unmodifiableSet(uniqueAuthors);
    // или return new HashSet<>(uniqueAuthors);
  }

  /**
   * Получение "популярных" книг (в данном случае - книги в обычной очереди ожидания).
   *
   * @return Список книг в очереди ожидания.
   */
  public List<Book> getPopularBooks() {
    // Возвращаем копию, т.к. очередь может изменяться
    return new ArrayList<>(waitingQueue);
  }

  /**
   * Получение статистики по выданным книгам.
   *
   * @return Неизменяемое представление карты <Читатель, Список его книг>
   */
  public Map<Reader, List<Book>> getIssuedBooksStats() {
    // Возвращаем неизменяемое представление для защиты
    return Collections.unmodifiableMap(issuedBooks);
  }

  /**
   * Получение общего количества книг в каталоге библиотеки.
   *
   * @return Количество книг.
   */
  public int getTotalBooksCount() {
    return catalog.size();
  }

  // --- 5. Методы сортировки и фильтрации ---

  /**
   * Получение списка книг, отсортированных по году издания (естественный порядок TreeSet).
   *
   * @return Новый список книг, отсортированный по году.
   */
  public List<Book> sortByYear() {
    // TreeSet уже отсортирован, просто копируем в список
    return new ArrayList<>(catalog);
  }

  /**
   * Получение списка книг, отсортированных по автору (затем по названию).
   *
   * @return Новый список книг, отсортированный по автору.
   */
  public List<Book> sortByAuthor() {
    List<Book> sortedList = new ArrayList<>(catalog);
    // Используем Comparator для сортировки по автору, затем по названию
    sortedList.sort(Comparator.comparing(Book::author)
        .thenComparing(Book::title));
    return sortedList;
  }

  /**
   * Получение списка доступных для выдачи книг.
   *
   * @return Список доступных книг.
   */
  public List<Book> filterAvailableBooks() {
    List<Book> availableBooks = new ArrayList<>();
    // Итерация по каталогу
    for (Book book : catalog) {
      if (isBookAvailable(book)) {
        availableBooks.add(book);
      }
    }
    return availableBooks;
  }

  // --- 6. Вспомогательные методы ---

  /**
   * Проверка доступности книги для выдачи.
   *
   * @param book Книга для проверки.
   * @return true, если книга есть в каталоге и не выдана никому, иначе false.
   * @throws NullPointerException если book == null.
   */
  public boolean isBookAvailable(Book book) {
    Objects.requireNonNull(book, "Книга не может быть null");

    if (!catalog.contains(book)) {
      return false; // Книги нет в библиотеке
    }

    // Проверяем, не выдана ли книга кому-либо
    // Итерация по значениям (спискам книг) в карте issuedBooks
    for (List<Book> issuedList : issuedBooks.values()) {
      // Используем итератор для обхода списка выданных книг
      for (Book value : issuedList) {
        if (value.equals(book)) { // Сравнение по equals (ISBN)
          return false; // Найдена в списке выданных
        }
      }
    }

    return true; // Книга есть в каталоге и не найдена в выданных
  }

  /**
   * Получение списка новых поступлений (книг, добавленных с момента создания библиотеки или последнего вызова?).
   * В данной реализации возвращает все книги, добавленные через addBook.
   *
   * @return Копия списка новых поступлений.
   */
  public List<Book> getNewArrivals() {
    // Возвращаем копию, чтобы внешние изменения не влияли на внутренний список
    return new ArrayList<>(newArrivals);
  }

  /**
   * Получение истории всех выдач книг.
   *
   * @return Копия истории выдач (LinkedList).
   */
  public List<Book> getIssueHistory() {
    // Возвращаем копию
    return new LinkedList<>(issueHistory);
  }

  /**
   * Вывод каталога книг в консоль (отсортированного по году).
   */
  public void printCatalog() {
    System.out.println("\n--- Каталог Библиотеки (" + getTotalBooksCount() + " книг) ---");
    if (catalog.isEmpty()) {
      System.out.println("Каталог пуст.");
      return;
    }
    // Используем итератор для вывода
    Iterator<Book> iterator = catalog.iterator();
    int count = 1;
    while (iterator.hasNext()) {
      System.out.println(count++ + ". " + iterator.next());
    }
    System.out.println("------------------------------------");
  }

  /**
   * Вывод списка читателей.
   */
  public void printReaders() {
    System.out.println("\n--- Список Читателей (" + readers.size() + ") ---");
    if (readers.isEmpty()) {
      System.out.println("Читатели не зарегистрированы.");
      return;
    }
    int count = 1;
    // Используем итератор для HashSet
    for (Reader reader : readers) {
      System.out.println(count++ + ". " + reader);
    }
    System.out.println("-----------------------------");
  }

  /**
   * Вывод статистики по выданным книгам.
   */
  public void printIssuedBooksStats() {
    System.out.println("\n--- Статистика Выданных Книг ---");
    if (issuedBooks.isEmpty() || issuedBooks.values().stream().allMatch(List::isEmpty)) {
      System.out.println("Ни одна книга не выдана.");
      return;
    }
    // Итерация по записям карты
    for (Map.Entry<Reader, List<Book>> entry : issuedBooks.entrySet()) {
      Reader reader = entry.getKey();
      List<Book> books = entry.getValue();
      if (!books.isEmpty()) {
        System.out.println("Читатель: " + reader.name() + " (ID: " + reader.readerId() + ")");
        // Итерация по списку книг читателя
        Iterator<Book> bookIterator = books.iterator();
        int bookCount = 1;
        while (bookIterator.hasNext()) {
          System.out.println("  " + bookCount++ + ") " + bookIterator.next());
        }
      }
    }
    System.out.println("---------------------------------");
  }
}
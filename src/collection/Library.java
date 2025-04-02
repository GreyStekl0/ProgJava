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
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

/**
 * Основной класс библиотеки, управляющий каталогом, читателями и операциями.
 * Stream API не используется.
 */
public class Library {

  // --- Коллекции для хранения данных ---
  private final TreeSet<Book> catalog = new TreeSet<>();
  private final Map<Book, Reader> currentlyIssuedBooks = new HashMap<>();
  private final Queue<Book> waitingList = new LinkedList<>();
  private final PriorityQueue<Book> priorityWaitingList = new PriorityQueue<>(
      Comparator.comparingInt(Book::getYear).reversed()
  );
  private final HashSet<String> uniqueAuthors = new HashSet<>();
  private final ArrayList<Book> newArrivals = new ArrayList<>();
  private final LinkedList<Book> issueHistory = new LinkedList<>();
  private final Map<String, Reader> readers = new HashMap<>();


  // --- Базовые методы работы с книгами ---

  /**
   * Добавление книги в библиотеку.
   *
   * @param book Книга для добавления.
   * @throws LibraryException если книга null или книга с таким ISBN уже существует.
   */
  public void addBook(Book book) throws LibraryException {
    if (book == null) {
      throw new LibraryException("Нельзя добавить null как книгу.");
    }
    // Проверка на уникальность по ISBN
    boolean isbnExists = false;
    for (Book existingBook : catalog) {
      if (existingBook.getIsbn().equals(book.getIsbn())) {
        isbnExists = true;
        break;
      }
    }

    if (isbnExists) {
      throw new LibraryException("Книга с ISBN " + book.getIsbn() + " уже существует в каталоге.");
    }

    if (catalog.add(book)) {
      uniqueAuthors.add(book.getAuthor());
      newArrivals.add(book);
      book.setAvailable(true);
    } else {
      System.out.println("Предупреждение: Книга не добавлена, возможно дубликат по критерию сортировки TreeSet: " + book);
    }
  }

  /**
   * Удаление книги из библиотеки и обновление списка уникальных авторов (упрощенная проверка).
   *
   * @param book Книга для удаления.
   * @throws LibraryException если книга null, не найдена, или выдана читателю.
   */
  public void removeBook(Book book) throws LibraryException {
    if (book == null) {
      throw new LibraryException("Нельзя удалить null.");
    }
    if (currentlyIssuedBooks.containsKey(book)) {
      throw new LibraryException("Книга '" + book.getTitle() + "' (ISBN: " + book.getIsbn() + ") выдана читателю и не может быть удалена.");
    }

    // Запоминаем автора ДО попытки удаления
    String author = book.getAuthor();

    // Пытаемся удалить книгу из основного каталога
    boolean removed = catalog.remove(book);

    if (removed) {
      // Если удаление из каталога успешно, удаляем из других мест
      newArrivals.remove(book);
      waitingList.remove(book);
      priorityWaitingList.remove(book);

      // Теперь проверяем, нужно ли удалять автора из uniqueAuthors
      // Для этого ищем, есть ли ХОТЯ БЫ ОДНА другая книга этого автора в каталоге
      boolean authorHasOtherBooks = false;
      // Используем iterator для обхода оставшихся книг
      for (Book remainingBook : catalog) {
        if (remainingBook.getAuthor().equals(author)) {
          // Нашли другую книгу того же автора
          authorHasOtherBooks = true;
          break; // Дальше можно не искать
        }
      }

      // Если после удаления книги других книг автора не осталось, удаляем автора
      if (!authorHasOtherBooks) {
        uniqueAuthors.remove(author);
        System.out.println("Автор '" + author + "' удален из списка уникальных авторов (последняя книга удалена).");
      }
      System.out.println("Книга '" + book.getTitle() + "' успешно удалена из каталога.");

    } else {
      // Если удалить не удалось, значит книги не было в каталоге (по критерию TreeSet)
      throw new LibraryException("Книга '" + book.getTitle() + "' (ISBN: " + book.getIsbn() + ") не найдена в каталоге для удаления.");
    }
  }

  /**
   * Поиск книг по автору.
   *
   * @param author Имя автора.
   * @return Список книг данного автора. Пустой список, если автор не найден или не указан.
   */
  public List<Book> findBooksByAuthor(String author) {
    if (author == null || author.trim().isEmpty()) {
      System.out.println("Имя автора для поиска не указано.");
      return new ArrayList<>();
    }
    List<Book> foundBooks = new ArrayList<>();
    for (Book book : catalog) {
      if (book.getAuthor().equalsIgnoreCase(author.trim())) {
        foundBooks.add(book);
      }
    }
    return foundBooks;
  }

  /**
   * Поиск книг по году издания.
   *
   * @param year Год издания.
   * @return Список книг, изданных в указанном году.
   *
   * <p>Пустой список, если год некорректен или если нет книг этого года.
   */
  public List<Book> findBooksByYear(int year) {
    if (year <= 0) {
      System.out.println("Год издания должен быть положительным числом.");
      return new ArrayList<>();
    }
    List<Book> foundBooks = new ArrayList<>();
    for (Book book : catalog) {
      if (book.getYear() == year) {
        foundBooks.add(book);
      }
      // Оптимизация для сортированного по году TreeSet
      if (book.getYear() > year && catalog.comparator() == null) {
        break;
      }
    }
    return foundBooks;
  }

  // --- Методы работы с читателями ---

  /**
   * Добавление нового читателя в систему.
   *
   * @param reader Читатель для добавления.
   * @throws LibraryException если читатель null или читатель с таким ID уже существует.
   */
  public void addReader(Reader reader) throws LibraryException {
    if (reader == null) {
      throw new LibraryException("Нельзя добавить null как читателя.");
    }
    if (readers.containsKey(reader.getReaderId())) {
      throw new LibraryException("Читатель с ID " + reader.getReaderId() + " уже зарегистрирован.");
    }
    readers.put(reader.getReaderId(), reader);
  }

  /**
   * Получение читателя по его ID.
   *
   * @param readerId ID читателя.
   * @return Объект Reader или null, если читатель не найден.
   */
  public Reader getReaderById(String readerId) {
    if (readerId == null || readerId.trim().isEmpty()) {
      System.out.println("ID читателя не указан.");
      return null;
    }
    return readers.get(readerId);
  }

  /**
   * Получение читателя по имени (возвращает первого найденного).
   *
   * @param name Имя читателя.
   * @return Объект Reader или null, если читатель не найден.
   */
  public Reader getReader(String name) {
    if (name == null || name.trim().isEmpty()) {
      System.out.println("Имя читателя для поиска не указано.");
      return null;
    }
    // Используем for-each для итерации по значениям Map
    for (Reader reader : readers.values()) {
      if (reader.getName().equalsIgnoreCase(name.trim())) {
        return reader;
      }
    }
    return null;
  }


  /**
   * Выдача книги читателю.
   *
   * @param reader Читатель.
   * @param book   Книга для выдачи.
   * @throws LibraryException если читатель или книга null, не найдены, книга недоступна или у читателя лимит.
   */
  public void issueBook(Reader reader, Book book) throws LibraryException {
    if (reader == null || book == null) {
      throw new LibraryException("Читатель и книга не должны быть null.");
    }
    if (!readers.containsKey(reader.getReaderId())) {
      throw new LibraryException("Читатель " + reader.getName() + " не зарегистрирован в библиотеке.");
    }
    if (!catalog.contains(book)) {
      throw new LibraryException("Книга '" + book.getTitle() + "' отсутствует в каталоге.");
    }
    if (currentlyIssuedBooks.containsKey(book)) {
      throw new LibraryException("Книга '" + book.getTitle() + "' уже выдана читателю " + currentlyIssuedBooks.get(book).getName());
    }
    if (!reader.canBorrowMore()) {
      throw new LibraryException("Читатель " + reader.getName() + " достиг лимита книг (" + reader.getMaxBooks() + ").");
    }

    if (reader.borrowBookInternal(book)) {
      currentlyIssuedBooks.put(book, reader);
      book.setAvailable(false);
      issueHistory.add(book);
      waitingList.remove(book);
      priorityWaitingList.remove(book);
      System.out.println("Книга '" + book.getTitle() + "' выдана читателю " + reader.getName());
    } else {
      throw new LibraryException("Не удалось выдать книгу '" + book.getTitle() + "' читателю " + reader.getName() + ".");
    }
  }

  /**
   * Возврат книги в библиотеку.
   *
   * @param book Книга для возврата.
   * @throws LibraryException если книга null или не была выдана.
   */
  public void returnBook(Book book) throws LibraryException {
    if (book == null) {
      throw new LibraryException("Нельзя вернуть null как книгу.");
    }
    if (!currentlyIssuedBooks.containsKey(book)) {
      throw new LibraryException("Книга '" + book.getTitle() + "' не числится как выданная.");
    }

    Reader reader = currentlyIssuedBooks.get(book);
    if (reader.returnBookInternal(book)) {
      currentlyIssuedBooks.remove(book);
      book.setAvailable(true);
      System.out.println("Книга '" + book.getTitle() + "' возвращена читателем " + reader.getName());
      checkWaitingListAndNotify(book);
    } else {
      throw new LibraryException("Ошибка целостности данных: книга '" + book.getTitle() + "' числилась выданной, но не найдена у читателя " + reader.getName());
    }
  }

  // Вспомогательный метод для проверки очереди после возврата
  private void checkWaitingListAndNotify(Book returnedBook) {
    if (priorityWaitingList.contains(returnedBook)) {
      System.out.println("Книга '" + returnedBook.getTitle() + "' есть в приоритетной очереди ожидания.");
    } else if (waitingList.contains(returnedBook)) {
      System.out.println("Книга '" + returnedBook.getTitle() + "' есть в обычной очереди ожидания.");
    }
  }


  // --- Методы работы с очередью ---

  /**
   * Добавление книги в обычную очередь ожидания.
   *
   * @param book Книга для добавления в очередь.
   * @throws LibraryException если книга null, не существует или доступна.
   */
  public void addToWaitingList(Book book) throws LibraryException {
    if (book == null) {
      throw new LibraryException("Книга не должна быть null.");
    }
    if (!catalog.contains(book)) {
      throw new LibraryException("Книга не найдена в каталоге.");
    }
    if (!currentlyIssuedBooks.containsKey(book)) {
      System.out.println("Предупреждение: Книга '" + book.getTitle() + "' доступна, добавление в очередь ожидания не имеет смысла.");
    }
    waitingList.offer(book);
    System.out.println("Книга '" + book.getTitle() + "' добавлена в очередь ожидания.");
  }

  /**
   * Добавление книги в приоритетную очередь ожидания.
   *
   * @param book Книга для добавления в очередь.
   * @throws LibraryException если книга null, не существует или доступна.
   */
  public void addToPriorityWaitingList(Book book) throws LibraryException {
    if (book == null) {
      throw new LibraryException("Книга не должна быть null.");
    }
    if (!catalog.contains(book)) {
      throw new LibraryException("Книга не найдена в каталоге.");
    }
    if (!currentlyIssuedBooks.containsKey(book)) {
      System.out.println("Предупреждение: Книга '" + book.getTitle() + "' доступна, добавление в приоритетную очередь не имеет смысла.");
    }
    priorityWaitingList.offer(book);
    System.out.println("Книга '" + book.getTitle() + "' добавлена в приоритетную очередь ожидания.");
  }


  /**
   * Получение (и удаление) следующей книги из обычной очереди ожидания (FIFO).
   *
   * @return Следующая книга из очереди или null, если очередь пуста.
   */
  public Book getNextBookFromQueue() {
    if (waitingList.isEmpty()) {
      System.out.println("Обычная очередь ожидания пуста.");
      return null;
    }
    return waitingList.poll();
  }

  /**
   * Получение (и удаление) следующей книги из приоритетной очереди ожидания.
   *
   * @return Следующая книга из очереди (с наивысшим приоритетом) или null, если очередь пуста.
   */
  public Book getNextBookFromPriorityQueue() {
    if (priorityWaitingList.isEmpty()) {
      System.out.println("Приоритетная очередь ожидания пуста.");
      return null;
    }
    return priorityWaitingList.poll();
  }

  /**
   * Проверка наличия конкретной книги в обычной очереди ожидания.
   *
   * @param book Книга для проверки.
   * @return true, если книга есть в очереди.
   * @throws LibraryException если книга null.
   */
  public boolean isBookInQueue(Book book) throws LibraryException {
    if (book == null) {
      throw new LibraryException("Книга не должна быть null.");
    }
    return waitingList.contains(book);
  }

  /**
   * Проверка наличия конкретной книги в приоритетной очереди ожидания.
   *
   * @param book Книга для проверки.
   * @return true, если книга есть в очереди.
   * @throws LibraryException если книга null.
   */
  public boolean isBookInPriorityQueue(Book book) throws LibraryException {
    if (book == null) {
      throw new LibraryException("Книга не должна быть null.");
    }
    return priorityWaitingList.contains(book);
  }

  // --- Методы статистики ---

  /**
   * Получение множества уникальных авторов всех книг в каталоге.
   *
   * @return Копию множества уникальных имен авторов.
   */
  public Set<String> getUniqueAuthors() {
    return new HashSet<>(uniqueAuthors);
  }

  /**
   * Получение "популярных" книг (находящиеся в очередях ожидания).
   *
   * @return Список популярных книг (копия).
   */
  public List<Book> getPopularBooks() {
    Set<Book> popular = new HashSet<>(waitingList);
    popular.addAll(priorityWaitingList);
    return new ArrayList<>(popular);
  }

  /**
   * Статистика по выданным книгам: какой читатель какие книги взял.
   *
   * @return Копия карты, где ключ - читатель, значение - список взятых им книг.
   */
  public Map<Reader, List<Book>> getIssuedBooksStats() {
    Map<Reader, List<Book>> stats = new HashMap<>();
    // Итерация по карте выданных книг (книга -> читатель)
    for (Map.Entry<Book, Reader> entry : currentlyIssuedBooks.entrySet()) {
      Book book = entry.getKey();
      Reader reader = entry.getValue();
      // Получаем список книг для данного читателя или создаем новый, если его еще нет
      List<Book> readerBooks = stats.get(reader);
      if (readerBooks == null) {
        readerBooks = new ArrayList<>();
        stats.put(reader, readerBooks);
      }
      readerBooks.add(book);
    }
    return new HashMap<>(stats);
  }

  /**
   * Получение общего количества книг в каталоге библиотеки.
   *
   * @return Общее количество книг.
   */
  public int getTotalBooksCount() {
    return catalog.size();
  }

  // --- Методы сортировки и фильтрации ---

  /**
   * Получение списка всех книг, отсортированных по году издания (естественный порядок TreeSet).
   *
   * @return Новый список книг, отсортированный по году.
   */
  public List<Book> sortByYear() {
    return new ArrayList<>(catalog);
  }

  /**
   * Получение списка всех книг, отсортированных по автору (и затем по названию).
   *
   * @return Новый список книг, отсортированный по автору.
   */
  public List<Book> sortByAuthor() {
    List<Book> sortedList = new ArrayList<>(catalog);
    sortedList.sort(Comparator.comparing(Book::getAuthor)
        .thenComparing(Book::getTitle));
    return sortedList;
  }

  /**
   * Получение списка только доступных для выдачи книг.
   * Книги в списке отсортированы по году (естественный порядок каталога).
   *
   * @return Список доступных книг.
   */
  public List<Book> filterAvailableBooks() {
    List<Book> availableBooks = new ArrayList<>();
    for (Book book : catalog) {
      if (!currentlyIssuedBooks.containsKey(book)) { // Проверка доступности
        availableBooks.add(book);
      }
    }
    return availableBooks;
  }

  // --- Вспомогательные методы ---

  /**
   * Проверка доступности конкретной книги.
   *
   * @param book Книга для проверки.
   * @return true, если книга есть в каталоге и не выдана.
   * @throws LibraryException если книга null.
   */
  public boolean isBookAvailable(Book book) throws LibraryException {
    if (book == null) {
      throw new LibraryException("Книга для проверки доступности не должна быть null.");
    }
    return catalog.contains(book) && !currentlyIssuedBooks.containsKey(book);
  }

  /**
   * Получение списка новых поступлений.
   *
   * @return Неизменяемый список новых поступлений.
   */
  public List<Book> getNewArrivals() {
    return Collections.unmodifiableList(newArrivals);
  }

  /**
   * Получение истории выдач.
   *
   * @return Неизменяемый связный список истории выдач.
   */
  public List<Book> getIssueHistory() {
    return Collections.unmodifiableList(issueHistory);
  }

  /**
   * Вывод каталога книг в консоль (отсортировано по году).
   * Использует итератор.
   */
  public void printCatalog() {
    System.out.println("\n--- Каталог Библиотеки (" + getTotalBooksCount() + " книг, отсортировано по году) ---");
    if (catalog.isEmpty()) {
      System.out.println("Каталог пуст.");
      return;
    }
    Iterator<Book> iterator = catalog.iterator();
    int count = 1;
    while (iterator.hasNext()) {
      System.out.println(count++ + ". " + iterator.next());
    }
    System.out.println("--------------------------------------------------");
  }

  /**
   * Вывод доступных книг в консоль.
   */
  public void printAvailableBooks() {
    List<Book> available = filterAvailableBooks();
    System.out.println("\n--- Доступные книги (" + available.size() + ") ---");
    if (available.isEmpty()) {
      System.out.println("Нет доступных книг.");
      return;
    }
    int count = 1;
    for (Book book : available) {
      System.out.println(count++ + ". " + book);
    }
    System.out.println("-----------------------------");
  }

  /**
   * Вывод статистики по выданным книгам.
   */
  public void printIssuedBooksStats() {
    Map<Reader, List<Book>> stats = getIssuedBooksStats();
    System.out.println("\n--- Статистика по выданным книгам (" + currentlyIssuedBooks.size() + " книг выдано) ---");
    if (stats.isEmpty()) {
      System.out.println("Ни одна книга не выдана.");
      return;
    }
    for (Map.Entry<Reader, List<Book>> entry : stats.entrySet()) {
      Reader reader = entry.getKey();
      List<Book> books = entry.getValue();
      System.out.println("Читатель: " + reader.getName() + " (ID: " + reader.getReaderId() + ")");
      int count = 1;
      for (Book book : books) {
        System.out.println("  " + count++ + ") " + book.getTitle() + " (" + book.getAuthor() + ", " + book.getYear() + ")");
      }
    }
    System.out.println("---------------------------------------------");
  }

  /**
   * Вывод уникальных авторов.
   */
  public void printUniqueAuthors() {
    Set<String> authors = getUniqueAuthors();
    System.out.println("\n--- Уникальные авторы (" + authors.size() + ") ---");
    if (authors.isEmpty()) {
      System.out.println("Нет авторов в каталоге.");
      return;
    }
    int count = 1;
    for (String author : authors) {
      System.out.println(count++ + ". " + author);
    }
    System.out.println("------------------------------");
  }
}

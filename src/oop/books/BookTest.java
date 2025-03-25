package oop.books;

@SuppressWarnings("checkstyle:MissingJavadocType")
public class BookTest {
  @SuppressWarnings("checkstyle:MissingJavadocMethod")
  public static void main(String[] args) {
    Book book1 = new Book("Лев Толстой", "Война и мир", 1869);
    Book book2 = new Book("Федор Достоевский", "Преступление и наказание", 1866);
    Book book3 = new Book("Лев Толстой", "Война и мир", 1869);

    System.out.println("Book 1: " + book1);
    System.out.println("Book 2: " + book2);
    System.out.println("Book 3: " + book3);

    System.out.println("\nПолная информация о книге 1:");
    System.out.println(book1.getFullInfo());

    System.out.println("\nИзменение свойств книги 2:");
    book2.setTitle("Идиот");
    book2.setYear(1869);
    System.out.println(book2.getFullInfo());

    System.out.println("\nПроверка метода equals:");
    System.out.println("book1 equals book2: " + book1.equals(book2));
    System.out.println("book1 equals book3: " + book1.equals(book3));
    System.out.println("book1 equals null: " + book1.equals(null));

    System.out.println("\nПроверка метода hashCode:");
    System.out.println("book1 hashCode: " + book1.hashCode());
    System.out.println("book2 hashCode: " + book2.hashCode());
    System.out.println("book3 hashCode: " + book3.hashCode());
  }
}

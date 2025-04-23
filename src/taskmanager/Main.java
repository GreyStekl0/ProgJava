package taskmanager;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    TaskManager manager = new TaskManager("tasks.txt");

    while (true) {
      System.out.println("\n--- Меню Task Manager ---");
      System.out.println("1. Показать все задачи");
      System.out.println("2. Добавить задачу");
      System.out.println("3. Редактировать задачу");
      System.out.println("4. Удалить задачу");
      System.out.println("5. Выход");
      System.out.print("Выберите действие: ");

      String choice = scanner.nextLine().trim();
      switch (choice) {
        case "1":
          manager.listTasks();
          break;
        case "2":
          System.out.print("Название: ");
          String title = scanner.nextLine();
          System.out.print("Описание: ");
          String desc = scanner.nextLine();
          manager.addTask(title, desc);
          break;
        case "3":
          System.out.print("ID задачи для редактирования: ");
          int editId = Integer.parseInt(scanner.nextLine());
          System.out.print("Новое название (Enter чтобы пропустить): ");
          String newTitle = scanner.nextLine();
          System.out.print("Новое описание (Enter чтобы пропустить): ");
          String newDesc = scanner.nextLine();
          System.out.print("Статус (true=выполнена, false=не выполнена, Enter чтобы пропустить): ");
          String statusStr = scanner.nextLine();
          Boolean newStatus = statusStr.isEmpty() ? null : Boolean.parseBoolean(statusStr);
          manager.editTask(editId, newTitle, newDesc, newStatus);
          break;
        case "4":
          System.out.print("ID задачи для удаления: ");
          int removeId = Integer.parseInt(scanner.nextLine());
          manager.removeTask(removeId);
          break;
        case "5":
          System.out.println("Выход. До встречи!");
          scanner.close();
          return;
        default:
          System.out.println("Неверный выбор, попробуйте снова.");
      }
    }
  }
}


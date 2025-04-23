package taskmanager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TaskManager {
  private final Path filePath;
  private final List<Task> tasks = new ArrayList<>();

  public TaskManager(String filename) {
    this.filePath = Paths.get(filename);
    loadTasks();
  }

  /** Чтение всех задач из файла. */
  private void loadTasks() {
    tasks.clear();
    if (Files.exists(filePath)) {
      try (BufferedReader reader = Files.newBufferedReader(filePath)) {
        String line;
        while ((line = reader.readLine()) != null) {
          if (!line.trim().isEmpty()) {
            tasks.add(Task.fromFileString(line));
          }
        }
      } catch (IOException e) {
        System.err.println("Ошибка при чтении задач: " + e.getMessage());
      }
    }
  }

  /** Сохранение всех задач в файл. */
  private void saveTasks() {
    try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
      for (Task t : tasks) {
        writer.write(t.toFileString());
        writer.newLine();
      }
    } catch (IOException e) {
      System.err.println("Ошибка при сохранении задач: " + e.getMessage());
    }
  }

  /** Добавление новой задачи. */
  public void addTask(String title, String description) {
    int newId = tasks.stream()
        .mapToInt(Task::getId)
        .max()
        .orElse(0) + 1;
    Task task = new Task(newId, title, description, false);
    tasks.add(task);
    saveTasks();
    System.out.println("Задача добавлена: " + task);
  }

  /** Вывод всех задач. */
  public void listTasks() {
    if (tasks.isEmpty()) {
      System.out.println("Список задач пуст.");
    } else {
      System.out.println("Текущие задачи:");
      for (Task t : tasks) {
        System.out.println(t);
      }
    }
  }

  /** Редактирование задачи по id. */
  public void editTask(int id, String newTitle, String newDesc, Boolean newStatus) {
    for (Task t : tasks) {
      if (t.getId() == id) {
        if (newTitle != null && !newTitle.isEmpty()) {
          t.setTitle(newTitle);
        }
        if (newDesc != null && !newDesc.isEmpty()) {
          t.setDescription(newDesc);
        }
        if (newStatus != null) {
          t.setCompleted(newStatus);
        }
        saveTasks();
        System.out.println("Задача обновлена: " + t);
        return;
      }
    }
    System.out.println("Задача с id=" + id + " не найдена.");
  }

  /** Удаление задачи по id. */
  public void removeTask(int id) {
    Iterator<Task> it = tasks.iterator();
    while (it.hasNext()) {
      if (it.next().getId() == id) {
        it.remove();
        saveTasks();
        System.out.println("Задача с id=" + id + " удалена.");
        return;
      }
    }
    System.out.println("Задача с id=" + id + " не найдена.");
  }
}


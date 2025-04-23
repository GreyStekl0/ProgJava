package taskmanager;


public class Task {
  private final int id;
  private String title;
  private String description;
  private boolean completed;

  public Task(int id, String title, String description, boolean completed) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.completed = completed;
  }

  public int getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public boolean isCompleted() {
    return completed;
  }

  public void setCompleted(boolean completed) {
    this.completed = completed;
  }

  @Override
  public String toString() {
    return String.format("%d | %s | %s | %s",
        id,
        title,
        description,
        completed ? "выполнена" : "не выполнена");
  }

  /**
   * Для сохранения в файл: id|title|description|completed.
   */
  public String toFileString() {
    return String.join("|",
        String.valueOf(id),
        title.replace("|", " "),      // на случай, если в тексте есть |
        description.replace("|", " "),
        String.valueOf(completed));
  }

  /**
   * Парсер из строки файла в объект Task.
   */
  public static Task fromFileString(String line) {
    String[] parts = line.split("\\|", 4);
    int id = Integer.parseInt(parts[0]);
    String title = parts[1];
    String desc = parts[2];
    boolean completed = Boolean.parseBoolean(parts[3]);
    return new Task(id, title, desc, completed);
  }
}


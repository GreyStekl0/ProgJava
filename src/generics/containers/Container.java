package generics.containers;

import java.util.List;

public class Container<T> {
  private T item;

  public Container(T item) {
    this.item = item;
  }

  public void setItem(T item) {
    this.item = item;
  }

  public T getItem() {
    return item;
  }

  public static <E> int find(List<Container<E>> list, E itemToFind) {
    for (int i = 0; i < list.size(); i++) {
      if (itemToFind.equals(list.get(i).getItem())) {
        return i;
      }
    }
    return -1;
  }
}

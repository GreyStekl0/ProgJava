package generics.containers;

import java.util.ArrayList;
import java.util.List;

public class Application {
  public static void main(String[] args) {
    Container<Double> doubleContainer = new Container<>(3.14);
    Container<Integer> intContainer = new Container<>(42);

    List<Container<Integer>> intContainers = new ArrayList<>();
    intContainers.add(new Container<>(10));
    intContainers.add(new Container<>(20));
    intContainers.add(intContainer);  // 42
    intContainers.add(new Container<>(30));

    int index = Container.find(intContainers, 42);
    System.out.println("Container with value 42 found at index: " + index);

    index = Container.find(intContainers, 100);
    System.out.println("Container with value 100 found at index: " + index);

    Container<String> stringContainer = new Container<>("Hello");

    List<Container<String>> stringContainers = new ArrayList<>();
    stringContainers.add(new Container<>("Java"));
    stringContainers.add(stringContainer);  // "Hello"
    stringContainers.add(new Container<>("Generics"));

    index = Container.find(stringContainers, "Hello");
    System.out.println("Container with value \"Hello\" found at index: " + index);
  }
}
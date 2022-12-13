package org.example.secondTask;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;

public class MainSecondTask {
    public static void main(String[] args) throws NoSuchMethodException, NoSuchFieldException, IllegalAccessException, InstantiationException, IOException, InvocationTargetException, ClassNotFoundException {
    Item item = ClassUtility.loadFromProperties(Item.class, Path.of("files/secondTask/properties.txt"));
        System.out.println(item);
    }
}

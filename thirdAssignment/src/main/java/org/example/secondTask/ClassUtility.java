package org.example.secondTask;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.time.Instant;

public class ClassUtility {
    public static <T> T loadFromProperties(Class<T> cls, Path pathProperties) throws NoSuchMethodException,
            IllegalAccessException, InvocationTargetException, InstantiationException, ClassNotFoundException,
            IOException, NoSuchFieldException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(pathProperties.toFile()));
        T newInstance = cls.getConstructor().newInstance();
        String readline;
        String[] splitReadLine;
        String value;
        while ((readline = bufferedReader.readLine()) != null) {
            splitReadLine = readline.split("=");
            Field field = cls.getDeclaredField(splitReadLine[0].trim());
            value = splitReadLine[1].trim();
            if (field.getType() == String.class) {
                setValueToClass(newInstance, value, field);
            } else if (field.getType() == Integer.class || field.getType() == int.class) {
                setValueToClass(newInstance, Integer.valueOf(value), field);
            } else if (field.getType() == Instant.class) {
                setValueToClass(newInstance, Instant.parse(value), field);
            }

        }

        // Annotation annotation = cls.getAnnotation(Class.forName(newInstance.getClass().getName()));

        return newInstance;
    }

    private static <T> void setValueToClass(T instance, T value, Field field) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        instance.getClass().getMethod("set" + String.valueOf(field.getName().charAt(0)).toUpperCase() + field.getName().substring(1).trim(),
                field.getType()).invoke(instance, value);
    }
}

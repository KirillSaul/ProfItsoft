package org.example.secondTask;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class ClassUtility {
    public static <T> T loadFromProperties(Class<T> cls, Path pathProperties) {
        T newInstance;
        try {
            newInstance = cls.getDeclaredConstructor().newInstance();
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(pathProperties.toFile()))) {
                String readline;
                String[] splitReadLine;
                String value;
                while ((readline = bufferedReader.readLine()) != null) {
                    splitReadLine = readline.split("=");
                    Field field = null;
                    try {
                        field = cls.getDeclaredField(splitReadLine[0].trim());
                    } catch (NoSuchFieldException exception) {
                        Property property;
                        for (var fieldWithAnnotation : cls.getDeclaredFields()) {
                            if (nonNull(property = fieldWithAnnotation.getAnnotation(Property.class))
                                    && property.name().equals(splitReadLine[0].trim())) {
                                field = fieldWithAnnotation;
                            }
                        }
                        if (isNull(field)) {
                            throw new RuntimeException("can`t find field with name: \"" + splitReadLine[0].trim() + "\""
                                    , new NoSuchFieldException());
                        }
                    }
                    value = splitReadLine[1].trim();
                    if (field.getType().equals(String.class)) {
                        setValueToClass(newInstance, value, field);
                    } else if (field.getType().equals(Integer.class) || field.getType().equals(int.class)) {
                        setValueToClass(newInstance, Integer.valueOf(value), field);
                    } else if (field.getType().equals(Instant.class)) {
                        String format;
                        if (nonNull(field.getAnnotation(Property.class))
                                && !(format = field.getAnnotation(Property.class).format()).isBlank()) {
                            LocalDateTime localDateTime = LocalDateTime.parse(value, DateTimeFormatter.ofPattern(format));
                            ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());
                            setValueToClass(newInstance, zonedDateTime.toInstant(), field);
                        } else {
                            setValueToClass(newInstance, Instant.parse(value), field);
                        }

                    }

                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (NoSuchMethodException
                | IllegalAccessException
                | InvocationTargetException
                | InstantiationException e) {
            throw new RuntimeException(e);
        }
        return newInstance;
    }

    private static <T> void setValueToClass(T instance, T value, Field field) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        instance.getClass().getMethod("set" + String.valueOf(field.getName().charAt(0)).toUpperCase()
                + field.getName().substring(1).trim(), field.getType()).invoke(instance, value);
    }
}

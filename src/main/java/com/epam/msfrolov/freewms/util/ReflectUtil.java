package com.epam.msfrolov.freewms.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

//todo use this class?
public class ReflectUtil {
    private static final Logger log = LoggerFactory.getLogger(ReflectUtil.class);

    public static boolean checkField(String fieldName, Class clazz) {
        List<Field> allField = getAllFields(clazz);
        for (Field field : allField) {
            if (field.getName().equalsIgnoreCase(fieldName))
                return true;
        }
        return false;
    }

    public static Field getField(String fieldName, Class clazz) {
        for (Field field : getAllFields(clazz)) {
            if (field.getName().equalsIgnoreCase(fieldName))
                return field;
        }
        return null;
    }

    public static List<Field> getAllFields(Class clazz) {
        List<Field> fields = new ArrayList<>();
        List<Class> classes = getAllSuperClasses(clazz);
        for (Class currentClass : classes) {
            fields.addAll(Arrays.asList(currentClass.getDeclaredFields()));
        }
        return fields;
    }

    public static List<Class> getAllSuperClasses(Class clazz) {
        List<Class> classes = new ArrayList<>();
        classes.add(clazz);
        Class currentClass = clazz;
        while (Object.class != (currentClass = currentClass.getSuperclass())) {
            classes.add(currentClass);
        }
        return classes;
    }

    public static List<Object> getClasses() {
        List<Object> classes = new ArrayList<>();
        for (Iterator iterator = sun.misc.Service.providers(com.epam.msfrolov.freewms.model.BaseEntity.class);
             iterator.hasNext(); ) {
            try {
                classes.add(iterator.next());
            } catch (Throwable e) {
                log.error("Unknown error", e);
            }
        }
        return classes;
    }

    public static String getStringAsClassName(String nameIsInvalid) {
        return (nameIsInvalid.substring(0, 1).toUpperCase() + nameIsInvalid.substring(1));
    }

    public static Object createInstance(Class clazz) {
        Object instance = null;
        try {
            if (clazz == List.class) instance = new ArrayList<>();
            else instance = clazz.newInstance();
        } catch (InstantiationException e) {
            log.error("Constructor is not found!", e); //crush app
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            log.error("No access to create an instance!", e); //crush app
            e.printStackTrace();
        }
        return instance;
    }

    public static Class getGenericType(Class clazz) {
        Class foundClass = null;
        try {
            Field field = clazz.getDeclaredField("tracks");
            ParameterizedType genericType = (ParameterizedType) field.getGenericType();
            foundClass = (Class) genericType.getActualTypeArguments()[0];
        } catch (NoSuchFieldException e) {
            log.error("Can not return type of generic!", e);
            e.printStackTrace();
        }
        return foundClass;
    }
}

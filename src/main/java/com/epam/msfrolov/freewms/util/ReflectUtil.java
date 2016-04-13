package com.epam.msfrolov.freewms.util;

import com.epam.msfrolov.freewms.model.BaseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static com.epam.msfrolov.freewms.util.Common.firstUpperCase;

public class ReflectUtil {

    private static Logger log = LoggerFactory.getLogger(ReflectUtil.class);

    public static Method getSetter(String methodName, Class clazz) {
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method method : declaredMethods) {
            if ((method.getName().toUpperCase().contains(methodName.toUpperCase())) &&
                    method.getName().contains("set" + firstUpperCase(methodName))) {
                return method;
            }
        }
        if (clazz != BaseEntity.class) {
            clazz = clazz.getSuperclass();
            if (clazz != Object.class) return getSetter(methodName, clazz);
        }
        return null;
    }

    public static Method getGetter(String methodName, Class clazz) {
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method method : declaredMethods) {
            if ((method.getName().toUpperCase().contains(methodName.toUpperCase())) &&
                    (method.getName().contains("get" + firstUpperCase(methodName))
                            || method.getName().contains("is" + firstUpperCase(methodName)))) {
                return method;
            }
        }
        if (clazz != BaseEntity.class) {
            clazz = clazz.getSuperclass();
            if (clazz != Object.class) return getGetter(methodName, clazz);
        }
        return null;
    }

    public static boolean presenceField(String fieldName, Class clazz) {
        List<Class> classes = getAllSuperClasses(clazz);
        for (Class currentClass : classes) {
            Field[] fields = currentClass.getDeclaredFields();
            for (Field field : fields) {
                if (field.getName().equalsIgnoreCase(fieldName))
                    return true;
            }
        }
        return false;
    }

    public static Field getField(String fieldName, Class clazz) {
        List<Class> classes = getAllSuperClasses(clazz);
        for (Class currentClass : classes) {
            Field[] fields = currentClass.getDeclaredFields();
            for (Field field : fields) {
                if (field.getName().equalsIgnoreCase(fieldName))
                    return field;
            }
        }
        return null;
    }

    public static List<Field> getAllFields(Class clazz) {
        List<Field> fields = new ArrayList<>();
        List<Class> classes = getAllSuperClasses(clazz);
        for (Class currentClass : classes) fields.addAll(Arrays.asList(currentClass.getDeclaredFields()));
        StringBuilder sb = new StringBuilder();
        fields.forEach(s -> sb.append(s.getName()).append(' '));
        log.debug("all fields {} - {}", clazz.getSimpleName(), sb.toString());
        return fields;
    }

    public static List<Class> getAllSuperClasses(Class clazz) {
        List<Class> classes = new ArrayList<>();
        classes.add(clazz);
        do {
            clazz = clazz.getSuperclass();
            classes.add(clazz);
        } while (BaseEntity.class != clazz
                && Object.class != clazz);
        return classes;
    }

    public static List<Object> getClasses() {
        try {
            List<Object> classes = new ArrayList<>();
            for (Iterator iterator = sun.misc.Service.providers(com.epam.msfrolov.freewms.model.BaseEntity.class);
                 iterator.hasNext(); ) {
                classes.add(iterator.next());
            }
            return classes;
        } catch (Throwable e) {
            throw new AppException("Throwable", e);
        }
    }

    public static Object createInstance(Class clazz) {
        try {
            if (clazz == List.class)
                return new ArrayList<>();
            else
                return clazz.newInstance();
        } catch (InstantiationException e) {
            throw new AppException("Can not return type of generic!", e);
        } catch (IllegalAccessException e) {
            throw new AppException("No access to create an instance!", e);
        }
    }

    public static Class getGenericType(Field field) {
        try {
            ParameterizedType genericType = (ParameterizedType) field.getGenericType();
            return (Class) genericType.getActualTypeArguments()[0];
        } catch (ClassCastException e) {
            throw new AppException("Class cannot be cast!", e);
        }


    }

    public static List<Method> getMethods(Class clazz) {
        List<Method> accumulator = new ArrayList<>();
        return getMethods(clazz, accumulator);
    }

    private static List<Method> getMethods(Class clazz, List<Method> accumulator) {
        Method[] declaredMethods = clazz.getDeclaredMethods();
        accumulator.addAll(Arrays.asList(declaredMethods));
        if (clazz == BaseEntity.class) {
            return accumulator;
        }
        return getMethods(clazz.getSuperclass(), accumulator);
    }

    public static Object invokeMethod(Method method, Object o, Object[] objects) {
        try {
            return method.invoke(o, objects);
        } catch (IllegalAccessException e) {
            throw new AppException("IllegalAccessException", e);
        } catch (InvocationTargetException e) {
            throw new AppException("InvocationTargetException", e);

        }
    }

    public static Object invokeMethod(Method method, Object o) {
        try {
            return method.invoke(o);
        } catch (IllegalAccessException e) {
            throw new AppException("IllegalAccessException", e);
        } catch (InvocationTargetException e) {
            throw new AppException("InvocationTargetException", e);
        }
    }

    public static boolean checkIsSubclass(Class subClass, Class superClass) {
        if (subClass == superClass) return true;
        Class clazz = subClass.getSuperclass();
        if (clazz != Object.class)
            return checkIsSubclass(clazz, superClass);
        return false;
    }
}

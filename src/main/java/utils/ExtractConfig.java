package utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author yxf
 * @date 2021/11/26 15:17
 */
public class ExtractConfig {
    public static final Set<String> includeNames = new HashSet<>();
    public static final Set<String> includeTypes = new HashSet<>();
    public static final Set<String> excludeNames = new HashSet<>();

    public static final Set<Class<?>> excludeTypes = new HashSet<>() {
        {
            add(Void.class);
        }
    };

    private static final Set<Class<?>> primitiveTypes = new HashSet<Class<?>>() {
        {
            add(Boolean.class);
            add(Character.class);
            add(Byte.class);
            add(Short.class);
            add(Integer.class);
            add(Long.class);
            add(Float.class);
            add(Double.class);
            add(String.class);
        }
    };

    public static boolean isActualPrimitive(Class<?> type) {
        return type.isPrimitive() || primitiveTypes.contains(type);
    }

    public static boolean isPrimitiveType(Field field) {
        Class<?> type = field.getType();
        if (isActualPrimitive(type)) {
            return true;
        }

        if (type.isArray()) {
            Class<?> arrayType = type.getComponentType();
            return isActualPrimitive(arrayType);
        }

        if (Iterable.class.isAssignableFrom(type)) {
            if (field.getGenericType() instanceof ParameterizedType parameterizedType) {
                if (parameterizedType.getActualTypeArguments() != null && parameterizedType.getActualTypeArguments().length > 0) {
                    Class<?> actualTypeArgument = (Class<?>) parameterizedType.getActualTypeArguments()[0];
                    return isActualPrimitive(actualTypeArgument);
                }
            }
        }
        return false;
    }

    public static boolean isPrimitiveType(Method method) {
        Class<?> type = method.getReturnType();
        if (isActualPrimitive(type)) {
            return true;
        }

        if (type.isArray()) {
            Class<?> arrayType = type.getComponentType();
            return isActualPrimitive(arrayType);
        }

        if (Iterable.class.isAssignableFrom(type)) {
            if (method.getGenericReturnType() instanceof ParameterizedType parameterizedType) {
                if (parameterizedType.getActualTypeArguments() != null && parameterizedType.getActualTypeArguments().length > 0) {
                    Class<?> actualTypeArgument = (Class<?>) parameterizedType.getActualTypeArguments()[0];
                    return isActualPrimitive(actualTypeArgument);
                }
            }
        }
        return false;
    }

    public static void main(String[] args) throws NoSuchFieldException {
        Class<A> c = A.class;
        Field f1 = c.getDeclaredField("list");
        Field f2 = c.getDeclaredField("i");
        System.out.println(((ParameterizedType) f1.getGenericType()).getActualTypeArguments()[0]);
        System.out.println(f2.getGenericType() instanceof ParameterizedType);
    }


}

class A {
    List<String> list = new ArrayList<>();
    Integer i = 1;
}

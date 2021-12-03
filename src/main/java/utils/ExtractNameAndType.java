package utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 提取getXXX，attributes的name、type
 *
 * 提取其子类的xxx？
 *
 * @author yxf
 * @date 2021/11/26 15:20
 */
public class ExtractNameAndType {
    /**
     * @param typeName
     * @return list.size() == 2
     * list.get(0) 为可继续延申的objective type attributes
     * list.get(1) 为primitive type，字面量
     */
    public static List<List<NameAndType>> extractFromType(String typeName) {
        try {
            Class<?> type = Class.forName(typeName);
            return extractFromType(type);
        } catch (ClassNotFoundException e) {
            // log.println("指定类不存在");
        } catch (Exception e) {
            // log.println(e.getMessage());
        }
        return null;
    }

    public static List<List<NameAndType>> extractFromType(Class<?> type) {
        List<NameAndType> objectiveList = new LinkedList<>();
        List<NameAndType> primitiveList = new LinkedList<>();
        List<List<NameAndType>> res = Arrays.asList(objectiveList, primitiveList);

        List<Field> fields = new ArrayList<>(Arrays.asList(type.getFields()));


        objectiveList.addAll(extractObjectiveFromFields(fields));
        primitiveList.addAll(extractPrimitiveFromFields(fields));


        List<Method> methods = new ArrayList<>();


        objectiveList.addAll(extractObjectiveFromMethods(methods));
        primitiveList.addAll(extractPrimitiveFromMethods(methods));

        return res;
    }

    private static Collection<? extends NameAndType> extractPrimitiveFromFields(List<Field> fields) {
        return extractFromFields(fields, false);
    }

    private static Collection<? extends NameAndType> extractObjectiveFromFields(List<Field> fields) {
        return extractFromFields(fields, true);
    }

    private static Collection<? extends NameAndType> extractFromFields(List<Field> fields, boolean isObjective) {
        List<NameAndType> res = new LinkedList<>();
        for (Field f : fields) {
            if (isObjective != ExtractConfig.isPrimitiveType(f)) {
                res.add(new NameAndType(isObjective, f.getName(), f.getType().getSimpleName(), f.getType()));
            }
        }
        return res;
    }


    private static Collection<? extends NameAndType> extractPrimitiveFromMethods(List<Method> methods) {
        return null;

    }

    private static Collection<? extends NameAndType> extractObjectiveFromMethods(List<Method> methods) {
        return null;

    }

    private static Collection<? extends NameAndType> extractFromMethods(List<Method> methods, boolean isObjective) {
        //methods = methods.stream().filter(x -> x.getName().startsWith("get")).collect(Collectors.toList());
        List<NameAndType> res = new LinkedList<>();
        for (Method m : methods) {
            if (!m.getName().startsWith("get")) {
                continue;
            }
            res.add(new NameAndType(isObjective, m.getName().substring(3), getShowTypeName(m.getReturnType()), m.getReturnType()));
        }
        return res;
    }

    private static String getShowTypeName(Class<?> returnType) {
        return null;
    }

}

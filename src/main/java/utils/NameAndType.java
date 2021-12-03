package utils;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author yxf
 * @date 2021/11/26 15:23
 */
@Data
@AllArgsConstructor
public class NameAndType {
    private boolean isObjective;
    private String name;
    private String typeName;
    private Class<?> actualType;
}

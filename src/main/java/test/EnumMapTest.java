package test;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;

public class EnumMapTest {
    private enum Color {
        GREEN, RED, BLUE, YELLOW;
    }

    public static void main(String args[]) {
        List<Clothes> list = new ArrayList<>();
        list.add(new Clothes("C001", Color.BLUE));
        list.add(new Clothes("C002", Color.YELLOW));
        list.add(new Clothes("C003", Color.RED));
        list.add(new Clothes("C004", Color.GREEN));
        list.add(new Clothes("C005", Color.BLUE));
        list.add(new Clothes("C006", Color.BLUE));
        list.add(new Clothes("C007", Color.RED));
        list.add(new Clothes("C008", Color.YELLOW));
        list.add(new Clothes("C009", Color.YELLOW));
        list.add(new Clothes("C010", Color.GREEN));

        HashMap<String, Integer> hashMap = new HashMap<>();
        for (Clothes c : list) {
            hashMap.put(c.color.name(), hashMap.getOrDefault(c.color.name(), 0) + 1);
        }
        System.out.println(hashMap);

        Map<Color, Integer> enumMap = new EnumMap<>(Color.class);
        for (Clothes c : list) {
            enumMap.put(c.color, enumMap.getOrDefault(c.color, 0) + 1);
        }
        System.out.println(enumMap);
    }

    @Data
    @AllArgsConstructor
    private static class Clothes {
        String id;
        Color color;
    }
}

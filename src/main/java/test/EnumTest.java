package test;

import lombok.Data;

public class EnumTest {
    private enum E {
        A,
        B,
        C;
    }

    public static void main(String args[]) {
        System.out.println(get(E.B));
        System.out.println();
        String str = "1";
        System.out.println("2".compareTo("10"));
        System.out.println(Integer.compare(10, 1));
        T t = new T(1);
        System.out.println(t.hashCode());
        t.a = 2;
        System.out.println(t.hashCode());
        Object[] objects = new Object[10];
        objects[0] = null;
        System.out.println(objects);
    }

    private static int get(E e) {
        return switch (e) {
            case A -> 0;
            case B -> 1;
            case C -> 2;
        };
    }

    @Data
    private static class T{
        int a;
        T(int a){
            this.a = a;
        }

    }
}

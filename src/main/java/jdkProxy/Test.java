package jdkProxy;

import java.lang.reflect.Method;

public class Test {
    public static void main(String[] args) throws Exception {
        A a = new A();
        B b = new B();
        Method method = F.class.getMethod("f");
        /** A or B 的 A.class.getMethod("f") 都不行
        会报错，object is not an instance of declaring class */
        method.invoke(a);
        method.invoke(b);
    }
}

interface F{
    void f();
}

class A implements F{
    public void f(){
        System.out.println("A");
    }
}

class B implements F{
    public void f(){
        System.out.println("B");
    }
}

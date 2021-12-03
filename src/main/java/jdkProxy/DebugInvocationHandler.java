package jdkProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DebugInvocationHandler implements InvocationHandler {
    private final Object target; /** 真实对象 */

    public DebugInvocationHandler(Object target){
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before method: " + method.getName());
        System.out.println(method);
        Object result = method.invoke(target, args);
        System.out.println("after method: " + method.getName());
        return result;
    }
}
